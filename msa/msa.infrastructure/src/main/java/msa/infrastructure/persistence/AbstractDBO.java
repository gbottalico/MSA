package msa.infrastructure.persistence;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by simon.calabrese on 03/08/2017.
 */
public abstract class AbstractDBO {
    @Field(value = "user")
    private UserLoggedDBO userLogged;

    public UserLoggedDBO getUserLogged() {
        return userLogged;
    }

    public void setUserLogged(UserLoggedDBO userLogged) {
        this.userLogged = userLogged;
    }
}
