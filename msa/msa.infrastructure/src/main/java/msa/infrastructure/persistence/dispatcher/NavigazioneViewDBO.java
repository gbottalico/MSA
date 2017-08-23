package msa.infrastructure.persistence.dispatcher;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by simon.calabrese on 23/08/2017.
 */
@Document(collection = "sinistriNavigazione")
public class NavigazioneViewDBO {
    @Id
    private String id;

    @Field("numSinistro")
    private Integer numSinistro;

    @Field("viewNavigate")
    private List<String> viewNavigate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumSinistro() {
        return numSinistro;
    }

    public void setNumSinistro(Integer numSinistro) {
        this.numSinistro = numSinistro;
    }

    public List<String> getViewNavigate() {
        return viewNavigate;
    }

    public void setViewNavigate(List<String> viewNavigate) {
        this.viewNavigate = viewNavigate;
    }
}
