package msa.application.config;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseDTO<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1479480688346891374L;


    private HttpStatus status;

    public HttpStatus getStatus() {
        return status != null ? status : HttpStatus.OK;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    private T result;
    private List<Message> messaggi = new ArrayList<>();


    public List<Message> getMessaggi() {
        return messaggi;
    }

    public void setMessaggi(List<Message> messaggi) {
        this.messaggi = messaggi;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
