package msa.domain.object.sinistro;

/**
 * Created by simon.calabrese on 09/08/2017.
 */
public abstract class BaseDO {
    private UserLoggedDO userLogged;

    public UserLoggedDO getUserLogged() {
        return userLogged;
    }

    public void setUserLogged(UserLoggedDO userLogged) {
        this.userLogged = userLogged;
    }
}
