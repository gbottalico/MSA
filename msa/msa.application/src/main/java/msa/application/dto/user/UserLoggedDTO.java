package msa.application.dto.user;

import java.io.Serializable;

public class UserLoggedDTO implements Serializable {

    private static final long serialVersionUID = 6368146793985495290L;
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