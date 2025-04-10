package utils;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        validateUsername(username);
        validateUsername(password);

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        validateUsername(username);
        this.username = username;
    }

    public void setPassword(String password) {
        validatePassword(password);
        this.password = password;
    }

    /**
     * Validates username input from user
     * @param username The value to be validated
     * @throws IllegalArgumentException If the parameter entered is null
     * @author Tomasz Januszkiewicz
     */
    public void validateUsername(String username){
        if(username == null){
            throw new IllegalArgumentException("Parameter cannot be null");
        }
    }

    /**
     * Validates password input from user
     * @param password The value to be validated
     * @throws IllegalArgumentException If the parameter entered is null
     * @author Tomasz Januszkiewicz
     */
    public void validatePassword(String password){
        if(password == null){
            throw new IllegalArgumentException("Parameter cannot be null");
        }
    }
}
