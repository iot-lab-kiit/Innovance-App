package in.iot.lab.innovance.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(Integer id) {
        super("User with id : " + id + " is not found");
    }
}