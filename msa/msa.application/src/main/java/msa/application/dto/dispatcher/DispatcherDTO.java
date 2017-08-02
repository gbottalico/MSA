package msa.application.dto.dispatcher;

import java.io.Serializable;

/**
 * Created by simon.calabrese on 01/08/2017.
 */
public class DispatcherDTO implements Serializable{
    private static final long serialVersionUID = 7956107530079706346L;
    private Integer garanziaSelected;
    private String thisView;
    private String param;
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

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
