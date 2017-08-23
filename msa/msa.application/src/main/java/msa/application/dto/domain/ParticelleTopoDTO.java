package msa.application.dto.domain;

import java.io.Serializable;

/**
 * Created by simon.calabrese on 23/08/2017.
 */
public class ParticelleTopoDTO implements Serializable{
    private static final long serialVersionUID = 4581242121272979926L;
    private Integer id;
    private String des;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
