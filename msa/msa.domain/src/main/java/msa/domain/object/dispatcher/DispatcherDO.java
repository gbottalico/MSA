package msa.domain.object.dispatcher;

import java.util.Map;

/**
 * Created by simon.calabrese on 01/08/2017.
 */
public class DispatcherDO {
    private Integer garanziaSelected;
    private Integer numSinistroProvv;
    private String paramCod;
    private String lastView;

    public Integer getGaranziaSelected() {
        return garanziaSelected;
    }

    public void setGaranziaSelected(Integer garanziaSelected) {
        this.garanziaSelected = garanziaSelected;
    }

    public Integer getNumSinistroProvv() {
        return numSinistroProvv;
    }

    public void setNumSinistroProvv(Integer numSinistroProvv) {
        this.numSinistroProvv = numSinistroProvv;
    }

    public String getParamCod() {
        return paramCod;
    }

    public void setParamCod(String paramCod) {
        this.paramCod = paramCod;
    }

    public String getLastView() {
        return lastView;
    }

    public void setLastView(String lastView) {
        this.lastView = lastView;
    }
}
