package msa.infrastructure.persistence;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by simon.calabrese on 09/08/2017.
 */
public class UserLoggedDBO {
    @Field("idUser")
    private String idUser;
    @Field("amministratore")
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
