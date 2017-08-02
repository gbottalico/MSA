package msa.infrastructure.persistence.dispatcher;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by simon.calabrese on 01/08/2017.
 */
@Document(collection = "alberoInterfacce")
public class AlberInterfacceDBO {

    @Id
    private String id;

    @Field(value = "garanzia")
    private Integer garanzia;

    @Field(value = "nextTree")
    private List<FogliaDBO> nextTree;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getGaranzia() {
        return garanzia;
    }

    public void setGaranzia(Integer garanzia) {
        this.garanzia = garanzia;
    }

    public List<FogliaDBO> getNextTree() {
        return nextTree;
    }

    public void setNextTree(List<FogliaDBO> nextTree) {
        this.nextTree = nextTree;
    }
}
