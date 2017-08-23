package msa.infrastructure.persistence.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by simon.calabrese on 23/08/2017.
 */
@Document(collection = "particelleToponomastiche")
public class ParticelleTopoDBO {
    @Id
    private Integer id;

    @Field("des")
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
