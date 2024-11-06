package in.iot.lab.innovance.exception;

public class InvalidLevel extends RuntimeException {
    public InvalidLevel(Integer userId, Integer domainId, Integer levelId) {
        super("User with id : " + userId + " choosing level with id : " + levelId + " is invalid since it does " +
                "not belong to domain with id : " + domainId);
    }
}