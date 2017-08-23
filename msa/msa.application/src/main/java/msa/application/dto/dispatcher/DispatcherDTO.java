package msa.application.dto.dispatcher;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by simon.calabrese on 01/08/2017.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class DispatcherDTO implements Serializable{
    private static final long serialVersionUID = 7956107530079706346L;
    private Integer garanziaSelected;
    private Integer numSinistroProvv;

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
}
