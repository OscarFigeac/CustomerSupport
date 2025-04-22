package business;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Tickets {
    //Attributes
    private String ticketId;
    private String issueDesc;
    private int priorityLvl;
    private final LocalDateTime creation;
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
    public String getTicketId() {
        return ticketId;
    }
    public String getIssueDesc() {
        return issueDesc;
    }
    public int getPriorityLvl() {
        return priorityLvl;
    }
    public LocalDateTime getCreation() {
        return creation;
    }
    public String getUsername() {
        return username;
    }
    public String getAgentId() {
        return agentId;
    }
    public String getStatus() {
        return status;
    }

    //Setters
    public void setTicketId(String ticketId) {
        validateString(ticketId);

        this.ticketId = ticketId;
    }
    public void setIssueDesc(String issueDesc) {
        validateString(issueDesc);

        this.issueDesc = issueDesc;
    }
    public void setPriorityLvl(int priorityLvl) {

        this.priorityLvl = validateInt(priorityLvl);
    }
    public void setUsername(String username) {
        validateString(username);

        this.username = username;
    }
    public void setAgentId(String agentId) {
        validateString(agentId);

        this.agentId = agentId;
    }
    public void setStatus(String status) {
        this.status = statusOptions(status);
    }

    //equals()
    @Override
    public boolean equals(Object ob) {
        if(!(ob instanceof Tickets t)){
            return false;
        }

        return this.ticketId.equals(t.ticketId);
    }

    //Validation Methods
    private static void validateString(String toBeValidated){
        if(toBeValidated == null){
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        if(toBeValidated.equals("")){
            throw new IllegalArgumentException("Parameter cannot be empty");
        }
    }
    private static int validateInt(int toBeValidated){
        Scanner kb = new Scanner(System.in);
        boolean check = false;

        while(!check){
            if(toBeValidated >= 1 && toBeValidated <= 5){
                check = true;
            }
            else{
                System.out.println("Enter a new integer between 1 and 5:");
                toBeValidated = kb.nextInt();
            }
        }

        return toBeValidated;
    }
    private static String statusOptions(String stat){
        Scanner kb = new Scanner(System.in);
        String option1 = "Pending";
        String option2 = "In Progress";
        String option3 = "Stalled";
        String option4 = "Solved";

        boolean check = false;

        while(!check){
            if(stat.equalsIgnoreCase(option1)){
                check = true;
            }
            else if(stat.equalsIgnoreCase(option2)){
                check = true;
            }
            else if(stat.equalsIgnoreCase(option3)){
                check = true;
            }
            else if(stat.equalsIgnoreCase(option4)){
                check = true;
            }
            else{
                System.out.println("Enter option was unsuitable, choices are: 'Pending', 'In Progress', 'Stalled' and 'Solved'");
                stat = kb.nextLine();
                kb.next();
            }
        }

        return stat;
    }
}
