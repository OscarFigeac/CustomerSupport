package business;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Ticket {
    //Attributes
    private String ticketId;
    private String issueDesc;
    private int priorityLvl;
    private final LocalDateTime creation;
    private String username;
    private String agentId;
    private String status;

    //Constructors
    public Ticket(String id, String desc, int priority, LocalDateTime createDate, String user, String agentID, String stat){
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

    /**
     *Method to compare two Tickets and see if they are the same
     *<p>
     *This method takes in another Ticket and compares it to the Ticket the user has chosen. If they have the same Ticket ID then they are the same
     *
     * @param ob Object being taken in by the method to compared (In this case a Ticket object)
     *
     * @return True if they have the same Ticket ID, False otherwise
     *
     * @author Eoghan Carroll
     */
    @Override
    public boolean equals(Object ob) {
        if(!(ob instanceof Ticket t)){
            return false;
        }

        return this.ticketId.equals(t.ticketId);
    }

    //Validation Methods

    /**
     * Method to validate any Strings entered by the user
     * <p>
     * Throws appropriate exceptions when the entered string is null or empty
     *
     * @param toBeValidated String entered by the uesr to be validated
     *
     * @throws IllegalArgumentException If the entered String is null or empty
     *
     * @author Eoghan Carroll
     */
    private static void validateString(String toBeValidated){
        if(toBeValidated == null){
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        if(toBeValidated.equals("")){
            throw new IllegalArgumentException("Parameter cannot be empty");
        }
    }

    /**
     * Method to validate any integers entered by the user
     * <p>
     * Accepts an integer from the user and checks to see if it is between 1-5 and if not the method prompts the user to enter a new integer to fit the parameters
     *
     * @param toBeValidated Integer entered by the user to be validated
     *
     * @return The Integer that has newly been validated to fit the parameters of the class
     *
     * @author Eoghan Carroll
     */
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

    /**
     * Method to ensure status options are valid
     * <p>
     * Accepts a string from the user and ensures that the string is one of the four options specified by the Ticket class' specification and prompts the user to change the string if it doesn't match the options
     *
     * @param stat String entered by the user to indicate the status of tickets
     *
     * @return The ticket now validated by the method to fit the appropriate options
     *
     * @author Eoghan Carroll
     */
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
