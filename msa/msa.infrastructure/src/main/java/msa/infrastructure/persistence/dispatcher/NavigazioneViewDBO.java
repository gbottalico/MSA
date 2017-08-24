package msa.infrastructure.persistence.dispatcher;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

/**
 * Created by simon.calabrese on 23/08/2017.
 */
@Document(collection = "sinistriNavigazione")
public class NavigazioneViewDBO {
    @Id
    private Integer numSinistro;

    @Field("viewNavigate")
    private Map<Integer,String> viewNavigate;

    public Integer getNumSinistro() {
        return numSinistro;
    }

    public void setNumSinistro(Integer numSinistro) {
        this.numSinistro = numSinistro;
    }

    public Map<Integer,String> getViewNavigate() {
        return viewNavigate;
    }

    public void setViewNavigate(Map<Integer,String> viewNavigate) {
        this.viewNavigate = viewNavigate;
    }
}
