package msa.domain.object.dispatcher;

import java.util.List;

/**
 * Created by simon.calabrese on 23/08/2017.
 */
public class NavigazioneViewDO {
    private Integer numSinistro;
    private List<String> viewNavigate;

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
