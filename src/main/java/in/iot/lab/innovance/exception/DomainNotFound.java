package in.iot.lab.innovance.exception;

public class DomainNotFound extends RuntimeException {
    public DomainNotFound(Integer id) {
        super("Domain with id " + id + " is not found !!");
    }

    public DomainNotFound(String name) {
        super("Domain with name " + name + " is not found !!");
    }
}