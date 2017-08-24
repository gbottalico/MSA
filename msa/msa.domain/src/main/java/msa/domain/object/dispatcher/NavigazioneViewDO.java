package msa.domain.object.dispatcher;

import java.util.Map;

/**
 * Created by simon.calabrese on 23/08/2017.
 */
public class NavigazioneViewDO {
    private Integer numSinistro;
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
