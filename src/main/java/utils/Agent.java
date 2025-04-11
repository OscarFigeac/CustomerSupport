package utils;

public class Agent extends User{
    String agentId;
    String agentName;

    public Agent(String username, String password, String agentId, String agentName) {
        super(username, password);
        this.agentId = agentId;
        this.agentName = agentName;
    }
    public String getAgentID() {
        return agentId;
    }

    public void setAgentID(String agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
}
