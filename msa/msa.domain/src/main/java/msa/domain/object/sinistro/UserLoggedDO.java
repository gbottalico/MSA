package msa.domain.object.sinistro;

/**
 * Created by simon.calabrese on 09/08/2017.
 */
public class UserLoggedDO {
    private String idUser;
    private Boolean amministratore;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Boolean getAmministratore() {
        return amministratore;
    }

    public void setAmministratore(Boolean amministratore) {
        this.amministratore = amministratore;
    }
}
