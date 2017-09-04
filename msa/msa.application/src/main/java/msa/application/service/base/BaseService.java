package msa.application.service.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import msa.application.config.Message;
import msa.application.config.enumerator.MessageType;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.base.paramBuilder.AbstractHttpParamBuilder;
import msa.application.service.base.paramBuilder.FormDataParamBuilder;
import msa.application.service.base.paramBuilder.HttpPathParameterBuilder;
import msa.application.service.base.paramBuilder.HttpQueryParameterBuilder;
import msa.application.service.enumerator.Api;
import msa.domain.Converter.MsaConverter;
import msa.infrastructure.config.AbstractMsaPropertiesReader;
import msa.infrastructure.repository.ErroriRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseService {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected AbstractMsaPropertiesReader properties;

    @Autowired
    protected MsaConverter converter;

    @Autowired
    protected ErroriRepository erroriRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);

    public BaseService() {

    }

    public BaseService(ObjectMapper objectMapper, AbstractMsaPropertiesReader properties) {
        super();
        this.objectMapper = objectMapper;
        this.properties = properties;
    }

    private HttpURLConnection buildConnection(AbstractHttpParamBuilder param, Api api) throws IOException {
        URL url;
        if (param.getClass().isAssignableFrom(HttpPathParameterBuilder.class)) {

            url = new URL(param.build());


        } else {
            url = new URL("http://" + properties.getRestUrlMap().getApi().get(api.getValue()) + param.build());
        }

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        return connection;
    }

    protected <T> T doGetCall(final Class<T> clazz, final Api api, final AbstractHttpParamBuilder param) throws InternalMsaException {
        try {
            HttpURLConnection connection = buildConnection(param, api);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() == 200) {
                return objectMapper.readValue(copyInputStream(connection.getInputStream()), clazz);
            } else {
                throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA002"));

            }
        } catch (IOException e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA000"));

        }

    }

    protected <T> T doPostCall(Class<T> clazz, Api api, Object objParam) throws InternalMsaException {
        try {
            String json = objectMapper.writeValueAsString(objParam);
            String baseUrl = properties.getRestUrlMap().getApi().get(api.getValue());

            final URL url = new URL(properties.getBasePath() + baseUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.write(json.getBytes("UTF-8"));
            dataOutputStream.flush();
            dataOutputStream.close();
            if (connection.getResponseCode() == 200) {
                return objectMapper.readValue(copyInputStream(connection.getInputStream()), clazz);
            } else {
                throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA002"));
            }

        } catch (IOException e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA000"));
        }

    }

    protected String doPostCallFormData(Api api, FormDataParamBuilder builder) throws InternalMsaException {
        try {
            Map<String, String> build = builder.build(properties.getRestUrlMap().getApi().get(api.getValue()));
            String body = build.entrySet().stream().reduce(new StringBuilder(),
                    (a, b) -> {
                        if (a.length() != 0) {
                            a = a.append("&");
                        }
                        return a.append(b.getKey())
                                .append("=").append(b.getValue());

                    },
                    (a, b) -> a).toString();
            URL url = new URL("http://" + properties.getRestUrlMap().getApi().get(api.getValue()));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.write(body.getBytes());
            }
            if (conn.getResponseCode() == 200) {
                return new BufferedReader(new InputStreamReader(copyInputStream(conn.getInputStream()))).lines().collect(Collectors.joining());
            } else {
                throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA000"));
            }

        } catch (IOException e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA000"));
        }
    }

    protected String doGetCallXml(Api api, HttpQueryParameterBuilder builder) throws InternalMsaException {
        try {
            HttpURLConnection connection = buildConnection(builder, api);
            connection.setRequestMethod("GET");
            return new BufferedReader(new InputStreamReader(copyInputStream(connection.getInputStream()))).lines().collect(Collectors.joining());
        } catch (IOException e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA000"));
        }

    }


    protected AbstractHttpParamBuilder<HttpQueryParameterBuilder.HttpQueryParam> getGetParamsBuilder() {
        return new HttpQueryParameterBuilder();
    }

    protected AbstractHttpParamBuilder<HttpPathParameterBuilder.HttpPathParam> getGetParamsBuilder(final Api url) {
        return new HttpPathParameterBuilder(url);
    }

    protected AbstractHttpParamBuilder<FormDataParamBuilder.FormDataParam> getFormDataBuilder() {
        return new FormDataParamBuilder();
    }

    private Optional<String> getErrorMessageByCod(final String codErrore) {
        return Optional.ofNullable(erroriRepository.findByCodErrore(codErrore).getTesto());
    }

    protected List<Message> getErrorMessagesByCodErrore(MessageType type, final String cod) {
        return buildErrorMessageByText(type, Collections.singletonList(getErrorMessageByCod(cod).orElse(null)));

    }

    protected List<Message> getErrorMessagesByCodErrore(MessageType type, final String cod, Function<String, String> specMessage) {
        return buildErrorMessageByText(type,
                Stream.of(getErrorMessageByCod(cod)
                        .map(specMessage)
                        .orElse(null))
                        .collect(Collectors.toList()));

    }

    private List<Message> buildErrorMessageByText(MessageType type, final List<String> texts) {
        return texts.stream().map(e -> new Message(type, e)).collect(Collectors.toList());
    }

    private InputStream copyInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] barr = new byte[256];
        int lenn;
        while ((lenn = inputStream.read(barr)) > -1) {
            baos.write(barr, 0, lenn);

        }
        baos.flush();
        return new ByteArrayInputStream(baos.toByteArray());
    }

    protected List<Object> execInParallel(Callable<Object>... callables) throws InternalMsaException {
        ExecutorService executor = Executors.newWorkStealingPool();
        try {
            return executor.invokeAll(Arrays.stream(callables).collect(Collectors.toList())).stream().reduce(new ArrayList<>(), (a, b) -> {
                try {
                    a.add(b.get());
                } catch (InterruptedException | ExecutionException e) {
                    LOGGER.error(e.getMessage());
                }
                return a;
            }, (a, b) -> b);
        } catch (InterruptedException e) {
            throw new InternalMsaException();
        }
    }

    protected List<Message> addWarningMessageByCondition(Supplier<String> text, Boolean condition) {
        if (!condition) {
            return Collections.singletonList(new Message(MessageType.WARNING, text.get()));
        } else return null;
    }
}
