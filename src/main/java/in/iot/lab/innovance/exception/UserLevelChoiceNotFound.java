package in.iot.lab.innovance.exception;


public class UserLevelChoiceNotFound extends RuntimeException {
    public UserLevelChoiceNotFound(Integer id) {
        super("User choice of id " + id + " is not found !!");
    }

    public UserLevelChoiceNotFound(Integer userId, Integer levelId) {
        super("Choice from User id " + userId + " for level id " + levelId + " is not found !!");
    }
}