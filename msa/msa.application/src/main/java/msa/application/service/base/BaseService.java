package msa.application.service.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.internal.org.objectweb.asm.TypeReference;
import msa.application.config.Message;
import msa.application.config.enumerator.MessageType;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.base.paramBuilder.AbstractHttpParamBuilder;
import msa.application.service.base.paramBuilder.HttpPathParameterBuilder;
import msa.application.service.base.paramBuilder.HttpQueryParameterBuilder;
import msa.application.service.enumerator.Api;
import msa.domain.Converter.MsaConverter;
import msa.infrastructure.config.AbstractMsaPropertiesReader;
import msa.infrastructure.repository.ErroriRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BaseService {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected AbstractMsaPropertiesReader properties;

    @Autowired
    protected MsaConverter converter;

    @Autowired
    protected ErroriRepository erroriRepository;

    public BaseService() {

    }

    public BaseService(ObjectMapper objectMapper, AbstractMsaPropertiesReader properties) {
        super();
        this.objectMapper = objectMapper;
        this.properties = properties;
    }

    protected <T> T doGetCall(final Class<T> clazz, final Api api, final AbstractHttpParamBuilder param) throws InternalMsaException {
        HttpURLConnection connection;
        final URL url;
        try {

            if (param.getClass().isAssignableFrom(HttpPathParameterBuilder.class)) {

                url = new URL(param.build());


            } else {
                url = new URL(properties.getRestUrlMap().getApi().get(api.getValue()) + param.build());
            }

            connection = (HttpURLConnection) url.openConnection();
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

    protected AbstractHttpParamBuilder<HttpQueryParameterBuilder.HttpQueryParam> getGetParamsBuilder() {
        return new HttpQueryParameterBuilder();
    }

    protected AbstractHttpParamBuilder<HttpPathParameterBuilder.HttpPathParam> getGetParamsBuilder(final Api url) {
        return new HttpPathParameterBuilder(url);
    }

    private String getErrorMessageByCod(final String codErrore) {
        return erroriRepository.findByCodErrore(codErrore).getTesto();
    }

    protected List<Message> getErrorMessagesByCodErrore(MessageType type, final String... cods) {
        return buildErrorMessageByText(type, Arrays.stream(cods).map(this::getErrorMessageByCod).collect(Collectors.toList()));

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
}
