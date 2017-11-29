package dk.stork.requestHandling.requestObjects;

import java.io.Serializable;

/**
 * @author Johannes Ernstsen
 */
public class Login implements Serializable {
    private boolean success;
    private String sessionId;

    public Login() {
    }

    public Login(boolean success, String sessionId) {
        this.success = success;
        this.sessionId = sessionId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
