package utils;

import business.Agent;
import business.Ticket;
import business.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutput {
    /**
     * Writes the user provided into the UserData text file
     * @param user User object being entered
     * @throws IOException If the code encounters an exception
     * @author Tomasz Januszkewicz
     */
    public void writeUser(User user) throws IOException {
        try(FileWriter fileWrite = new FileWriter("src/main/java/data/UserData", true);
            BufferedWriter bufferedWrite = new BufferedWriter(fileWrite)){

            bufferedWrite.newLine();
            bufferedWrite.write("Username: (" + user.getUsername() + ")");
            bufferedWrite.newLine();
            bufferedWrite.write("Password: (" + user.getPassword() + ")");
        }
    }

    /**
     * Writes the agent provided into the AgentData text file
     * @param agent Agent object being entered
     * @throws IOException If the code encounters an exception
     * @author Toamsz Januszkewicz
     */
    public void writeAgent(Agent agent) throws IOException{
        try(FileWriter fileWrite = new FileWriter("src/main/java/data/AgentData", true);
            BufferedWriter bufferedWrite = new BufferedWriter(fileWrite)){

            bufferedWrite.newLine();
            bufferedWrite.write("Username: (" + agent.getUsername() + ")");
            bufferedWrite.newLine();
            bufferedWrite.write("Password: (" + agent.getPassword() + ")");
            bufferedWrite.newLine();
            bufferedWrite.write("AgentID: (" + agent.getAgentID() + ")");
            bufferedWrite.newLine();
            bufferedWrite.write("AgentName: (" + agent.getAgentName() + ")");
        }
    }

    /**
     * Writes the ticket provided into the TicketData tet file
     * @param ticket Ticket object being entered
     * @throws IOException If the code encounters an exception
     * @author Tomasz Januszkiewkicz
     */
    public void writeTicket(Ticket ticket) throws IOException{
        try(FileWriter fileWrite = new FileWriter("src/main/java/data/TicketData", true);
            BufferedWriter bufferedWrite = new BufferedWriter(fileWrite)){

            bufferedWrite.newLine();
            bufferedWrite.write("TicketID: (" + ticket.getTicketId() + ")");
            bufferedWrite.newLine();
            bufferedWrite.write("Description: (" + ticket.getIssueDesc() + ")");
            bufferedWrite.newLine();
            bufferedWrite.write("Priority: (" + ticket.getPriorityLvl() + ")");
            bufferedWrite.newLine();
            bufferedWrite.write("DateCreated: (" + ticket.getCreation() + ")");
            bufferedWrite.newLine();
            bufferedWrite.write("UserUsername: (" + ticket.getUsername() + ")");
            bufferedWrite.newLine();
            bufferedWrite.write("AgentID: (" + ticket.getAgentId() + ")");
            bufferedWrite.newLine();
            bufferedWrite.write("Status: (" + ticket.getStatus() + ")");
        }
    }
}
