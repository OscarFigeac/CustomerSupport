package utils;

import java.time.LocalDateTime;

public class Tickets {
    //Attributes
    private String ticketId;
    private String issueDesc;
    private int priorityLvl;
    private LocalDateTime creation;
    private String username;
    private String agentId;
    private String status;

    //Constructors
    public Tickets(String id, String desc, int priority, LocalDateTime createDate, String user, String agentID, String stat){
        this.ticketId = id;
        this.issueDesc = desc;
        this.priorityLvl = priority;
        this.creation = createDate;
        this.username = user;
        this.agentId = agentID;
        this.status = stat;
    }

    //Getters

    //Setters

    //equals()
}
