package msa.application.service.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import msa.application.config.Message;
import msa.application.config.enumerator.MessageType;
import msa.application.service.enumerator.Api;
import msa.domain.Converter.MsaConverter;
import msa.infrastructure.config.AbstractMsaPropertiesReader;
import msa.infrastructure.repository.ErroriRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

    private String getErrorMessageByCod(final String codErrore) {
        return erroriRepository.findByCodErrore(codErrore).getTesto();
    }

    protected List<Message> getErrorMessagesByCodErrore(MessageType type, final String... cods) {
        return buildErrorMessageByText(type, Arrays.stream(cods).map(this::getErrorMessageByCod).collect(Collectors.toList()));

    }


    protected <T> T doGetCall(Class<T> clazz, Api api, Map<String, Object> param) {
        return null;
    }

    protected <T> T doPostCall(Class<T> clazz, Api api, Object objParam) {
        return null;
    }

    private List<Message> buildErrorMessageByText(MessageType type, final List<String> texts) {
        return texts.stream().map(e -> new Message(type, e)).collect(Collectors.toList());
    }
}
