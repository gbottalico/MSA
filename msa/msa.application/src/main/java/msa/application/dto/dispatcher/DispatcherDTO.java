package msa.application.dto.dispatcher;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by simon.calabrese on 01/08/2017.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class DispatcherDTO implements Serializable{
    private static final long serialVersionUID = 7956107530079706346L;
    private String garanziaSelected;
    private Integer numSinistroProvv;

    public String getGaranziaSelected() {
        return garanziaSelected;
    }

    public void setGaranziaSelected(String garanziaSelected) {
        this.garanziaSelected = garanziaSelected;
    }

    public Integer getNumSinistroProvv() {
        return numSinistroProvv;
    }

    public void setNumSinistroProvv(Integer numSinistroProvv) {
        this.numSinistroProvv = numSinistroProvv;
    }
}
