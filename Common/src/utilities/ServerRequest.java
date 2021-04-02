package utilities;

import java.io.Serializable;
import java.util.Objects;

public class ServerRequest<T extends Serializable>  implements Serializable {
    String command;
    String sessionId;
    T data;

    /**------------------------------------------------------------------------------ */

    public static final String USER_LOGIN_COMMAND = "User-Login";
    public static final String USER_LOGOUT_COMMAND = "User-Logout";
    public static final String USER_LOAD_COMMAND = "User-Login";
    public static final String USER_LOAD_MANY_COMMAND = "User-Logout";
    public static final String USER_REGISTER_COMMAND = "User-Register";
    public static final String USER_UPDATE_COMMAND = "User-Update";
    public static final String USER_FORGET_PASSWORD_COMMAND = "User-Forget-Password";
    public static final String USER_GET_LOGGED_IN_COMMAND = "User-get-Logged-in-user";
    public static final String USER_LIVE_CHAT_COMMAND = "Initiate Live Chat";
    public static final boolean USER_END_CHAT_COMMAND = false;

    public ServerRequest() {
    }

    public ServerRequest(String command, T data) {
        this.command = command;
        this.data = data;
    }

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ServerRequest<T> command(String command) {
        setCommand(command);
        return this;
    }

    public ServerRequest<T> data(T data) {
        setData(data);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ServerRequest)) {
            return false;
        }
        ServerRequest<T> serverRequest = (ServerRequest) o;
        return Objects.equals(command, serverRequest.command) && Objects.equals(data, serverRequest.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, data);
    }

    @Override
    public String toString() {
        return "{" +
            " command='" + getCommand() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }
}