package msa.infrastructure.persistence.dispatcher;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by simon.calabrese on 02/08/2017.
 */
public class FogliaDBO {

    @Field(value = "thisView")
    private String thisView;
    @Field(value = "parameter")
    private String parameter;
    @Field(value = "nextView")
    private String nextView;

    private Integer percentuale;

    public String getThisView() {
        return thisView;
    }

    public void setThisView(String thisView) {
        this.thisView = thisView;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getNextView() {
        return nextView;
    }

    public void setNextView(String nextView) {
        this.nextView = nextView;
    }

    public Integer getPercentuale() {
        return percentuale;
    }

    public void setPercentuale(Integer percentuale) {
        this.percentuale = percentuale;
    }
}
