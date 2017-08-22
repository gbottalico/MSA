package msa.application.dto.sinistro;

import com.fasterxml.jackson.databind.ObjectMapper;
import msa.application.dto.user.UserLoggedDTO;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by simon.calabrese on 02/08/2017.
 */
public abstract class AbstractDTO implements Serializable {
    private static final long serialVersionUID = -4723717242690952155L;

    private UserLoggedDTO userLogged;

    public UserLoggedDTO getUserLogged() {
        return userLogged;
    }

    public void setUserLogged(UserLoggedDTO userLogged) {
        this.userLogged = userLogged;
    }
    public void parseUserLogged(String userLogged) {
        try {
            this.userLogged = new ObjectMapper().readValue(userLogged,UserLoggedDTO.class);
        } catch (IOException e) {
            //Do nothing
        }
    }
}
