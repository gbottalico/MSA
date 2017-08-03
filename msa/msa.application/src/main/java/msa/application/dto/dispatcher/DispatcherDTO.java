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
    private String thisView;
    private Map<String,String> paramMap;
    private String nextView;


    public Integer getGaranziaSelected() {
        return garanziaSelected;
    }

    public void setGaranziaSelected(Integer garanziaSelected) {
        this.garanziaSelected = garanziaSelected;
    }

    public String getThisView() {
        return thisView;
    }

    public void setThisView(String thisView) {
        this.thisView = thisView;
    }

    public String getNextView() {
        return nextView;
    }

    public void setNextView(String nextView) {
        this.nextView = nextView;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }
}
