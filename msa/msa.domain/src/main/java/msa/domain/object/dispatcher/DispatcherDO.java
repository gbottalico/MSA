package msa.domain.object.dispatcher;

/**
 * Created by simon.calabrese on 01/08/2017.
 */
public class DispatcherDO {
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

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getNextView() {
        return nextView;
    }

    public void setNextView(String nextView) {
        this.nextView = nextView;
    }
}
