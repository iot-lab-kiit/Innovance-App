package in.iot.lab.innovance.exception;

public class LevelNotFound extends RuntimeException {
    public LevelNotFound(Integer id) {
        super("Level with id : " + id + " is not found !!");
    }
}