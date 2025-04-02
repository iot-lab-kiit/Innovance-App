package in.iot.lab.innovance.constants;

public class UrlConstants {

    // User URLs
    public final static String CREATE_USER = "/users";
    public final static String FIND_ALL_USERS = "/users";
    public final static String FIND_USER_BY_ID = "/users/{id}";
    public final static String DELETE_USER = "/users/{id}";

    // Domain URLs
    public final static String CREATE_DOMAIN = "/domains";
    public final static String FIND_ALL_DOMAIN = "/domains";
    public final static String FIND_DOMAIN_BY_ID = "/domains/{id}";
    public final static String FIND_DOMAIN_BY_NAME = "/domains/name/{name}";
    public final static String DELETE_DOMAIN = "/domains/{id}";

    // User Level Choice URLs
    public final static String CREATE_USER_LEVEL_CHOICE = "/user/choices";
    public final static String FIND_ALL_USER_LEVEL_CHOICE = "/user/choices";
    public final static String FIND_USER_LEVEL_CHOICE_BY_ID = "/user/choices/{id}";
    public final static String FIND_USER_LEVEL_CHOICE_BY_USER_ID = "/user/{id}/choices";
    public final static String FIND_USER_LEVEL_CHOICE_BY_USER_AND_LEVEL_ID = "/user/{userId}/choices/level/{levelId}";
    public final static String DELETE_USER_LEVEL_CHOICE = "/user/choices/{id}";
    
    //Gemini URL
    public final static String GET_RESPONSE_BY_GEMINI_AI = "/api/v1/gemini-response/{id}";
}