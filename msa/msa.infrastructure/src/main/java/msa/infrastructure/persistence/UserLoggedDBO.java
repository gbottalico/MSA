package msa.infrastructure.persistence;

/**
 * Created by simon.calabrese on 09/08/2017.
 */
public class UserLoggedDBO {
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
