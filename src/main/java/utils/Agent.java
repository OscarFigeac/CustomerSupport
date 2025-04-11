package utils;

public class Agent extends User{
    String agentId;
    String agentName;

    public Agent(String username, String password, String agentId, String agentName) {
        super(username, password);
        this.agentId = agentId;
        this.agentName = agentName;
    }

    /**
     * Validates a passed parameter of the type String
     * @param toBeValidated the parameter to be validated
     * @throws IllegalArgumentException if the parameter is null
     * @author Oscar Figeac
     */
    public void validateString(String toBeValidated){
        if (toBeValidated == null){
            throw new IllegalArgumentException("Parameter cannot be null");
        }
    }

    public String getAgentID() {
        return agentId;
    }

    public void setAgentID(String agentId) {
        validateString(agentId);
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        validateString(agentName);
        this.agentName = agentName;
    }
}
