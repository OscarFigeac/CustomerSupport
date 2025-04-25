package app;

import business.Agent;
import business.Ticket;
import business.User;
import utils.FileOutput;
import utils.LinkedList;
import utils.PriorityQueue;
import utils.UserHashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Version1App {
    public static void main(String[] args) throws IOException {
        UserHashMap map = new UserHashMap();
        PriorityQueue tickets = new PriorityQueue();

        Scanner kb = new Scanner(System.in);

        //Reading all the Agent data
        File agents = new File("src/main/java/data/AgentData");
        Scanner fileInput = new Scanner(agents);
        int agentCount = 0;
        while(fileInput.hasNextLine()){
            String username = fileInput.nextLine();
            username = username.substring(username.indexOf("(" )+1, username.indexOf(")"));

            String password = fileInput.nextLine();
            password = password.substring(password.indexOf("(")+1, password.indexOf(")"));

            String agentID = fileInput.nextLine();
            agentID = agentID.substring(agentID.indexOf("(")+1, agentID.indexOf(")"));

            String agentName = fileInput.nextLine();
            agentName = agentName.substring(agentName.indexOf("(")+1, agentName.indexOf(")"));

            Agent inputAgent = new Agent(username, password, agentID, agentName);
            String number = agentID.substring(4);
            int tempNum = Integer.parseInt(number);
            if(tempNum > agentCount) {
                agentCount = tempNum;
            }

            map.put(inputAgent.getUsername(), inputAgent);
        }

        //Reading all the User data
        File users = new File("src/main/java/data/UserData");
        fileInput = new Scanner(users);
        while(fileInput.hasNextLine()){
            String username = fileInput.nextLine();
            username = username.substring(username.indexOf("(" )+1, username.indexOf(")"));

            String password = fileInput.nextLine();
            password = password.substring(password.indexOf("(")+1, password.indexOf(")"));

            User inputUser = new User(username, password);
            map.put(inputUser.getUsername(), inputUser);
        }

        //Reading all the Ticket data
        File ticketData = new File("src/main/java/data/TicketData");
        fileInput = new Scanner(ticketData);
        int ticketNum = 0;
        while(fileInput.hasNextLine()){
            String ticketID = fileInput.nextLine();
            ticketID = ticketID.substring(ticketID.indexOf("(" )+1, ticketID.indexOf(")"));

            String issueDesc = fileInput.nextLine();
            issueDesc = issueDesc.substring(issueDesc.indexOf("(")+1, issueDesc.indexOf(")"));

            String tempPriority = fileInput.nextLine();
            tempPriority = tempPriority.substring(tempPriority.indexOf("(")+1, tempPriority.indexOf(")"));
            int priority = Integer.parseInt(tempPriority);

            String tempcreation = fileInput.nextLine();
            tempcreation = tempcreation.substring(tempcreation.indexOf("(")+1, tempcreation.indexOf(")"));
            LocalDateTime creation = LocalDateTime.parse(tempcreation);

            String username = fileInput.nextLine();
            username = username.substring(username.indexOf("(")+1, username.indexOf(")"));

            String agentId = fileInput.nextLine();
            agentId = agentId.substring(agentId.indexOf("(")+1, agentId.indexOf(")"));

            String status = fileInput.nextLine();
            status = status.substring(status.indexOf("(")+1, status.indexOf(")"));

            Ticket inputTicket = new Ticket(ticketID, issueDesc, priority, creation, username, agentId, status);
            tickets.enqueue(inputTicket);

            String number = ticketID.substring(3);
            int tempNum = Integer.parseInt(number);
            if(tempNum > ticketNum){
                ticketNum = tempNum;
            }
        }

        //Welcome screen - Tomasz Januszkiewicz
        System.out.println("Welcome!");
        boolean checkIfExists = true;
        boolean existingAccount = false;
        while(checkIfExists) {
            System.out.println("Do you have an existing account?");
            System.out.println("Y/N");
            String answer = kb.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                existingAccount = true;
                checkIfExists = false;
            } else if(answer.equalsIgnoreCase("N")) {
                checkIfExists = false;
            }else{
                System.out.println("Please enter a valid answer");
            }
        }


        //Required authentication functionality
        //Creating an account - Tomasz

        if(!existingAccount){
            boolean end = false;
            while(!end) {
                System.out.println("Are you registering as a user or agent?");
                System.out.println("user/agent");
                String answer = kb.nextLine();
                if(answer.equalsIgnoreCase("user")){
                    boolean usernameExists = true;
                    String username = null;
                    while(usernameExists) {
                        System.out.println("Enter a username");
                        username = kb.nextLine();
                        if(username.length() < 5){
                            System.out.println("The username must contain at least 5 characters");
                        }else if(map.containsKey(username)){
                            System.out.println("Invalid username");
                        }else{
                            usernameExists = false;
                        }
                    }
                    System.out.println("Enter a password");
                    String password = kb.nextLine();
                    User user = new User(username, password);
                    map.put(username, user);

                    end = true;

                    //Outputting the new user into the text file
                    FileOutput f = new FileOutput();
                    f.writeUser(user);

                } else if (answer.equalsIgnoreCase("agent")) {
                    boolean usernameExists = true;
                    String username = null;
                    while(usernameExists) {
                        System.out.println("Enter a username");
                        username = kb.nextLine();
                        if(username.length() < 5){
                            System.out.println("The username must contain at least 5 characters");
                        }else if(map.containsKey(username)){
                            System.out.println("Invalid username");
                        }else{
                            usernameExists = false;
                        }
                    }
                    System.out.println("Enter a password");
                    String password = kb.nextLine();
                    System.out.println("Enter your name");
                    String name = kb.nextLine();
                    String id = name.substring(0,2).toUpperCase() + username.substring(0,2).toUpperCase();
                    if(agentCount==0){
                        id = id + map.generateAgentNum();
                    }else{
                        id = id + map.generateAgentNum() + agentCount;
                    }
                    Agent agent = new Agent(username, password, id, name);
                    map.put(username, agent);

                    end = true;
                }else{
                    System.out.println("Please enter a valid answer");
                }
            }
        }


        //User login - Tomasz
        final int MAX_ATTEMPTS = 5;
        int attempts = 0;
        //keeps track of who they are
        User currentUser = null;
        Agent currentAgent = null;
        char userType = 'e'; //a for agent - u for user - e for empty

        boolean retryLogin = true;
        while(retryLogin) {
            System.out.println("Please enter your username");
            String username = kb.nextLine();
            System.out.println("Please enter your password");
            String password = kb.nextLine();
            if(username.length() < 5){
                System.out.println("Username or password is incorrect, please try again");
            }
            else if (password.isEmpty()) {
                System.out.println("Username or password is incorrect, please try again");
            }
            else if(!map.containsKey(username)){
                System.out.println("Username or password is incorrect, please try again");
            }
            else if (!map.get(username).getPassword().equals(password)) {
                System.out.println("Username or password is incorrect, please try again");
            }
            else{
                if(map.get(username) instanceof Agent) {
                    currentAgent = new Agent(username, password, ((Agent) map.get(username)).getAgentID(), ((Agent) map.get(username)).getAgentName());
                    System.out.println("Welcome " + currentAgent.getAgentName());
                    retryLogin = false;
                    userType = 'a';
                }else {
                    currentUser = new User(username, password);
                    System.out.println("Welcome " + currentUser.getUsername());
                    retryLogin = false;
                    userType = 'u';
                }
            }

            if(attempts == MAX_ATTEMPTS){
                System.out.println("Too many failed attempts, please try again later");
                break;
            }else{
                attempts++;
            }
        }

        //Main app
            //Required standard user ticket-handling functionality:
                //TODO: view the list of their tickets (open and closed)
                //TODO: View the details of one of their tickets
        //user creates a ticket
        if (userType == 'u') {
            boolean end = false;

            while (!end) {
                System.out.println("What would you like to do?");
                System.out.println("(1) Create a ticket");
                System.out.println("(2) View your tickets");
                System.out.println("(3) View one of your tickets");
                System.out.println("(4) Logout");
                int option = kb.nextInt();
                switch (option){
                    case 1:
                        kb.nextLine();
                        System.out.println("Please write the issue");
                        String description = kb.nextLine();
                        boolean correctPriority = false;
                        int priority = -1;
                        while(!correctPriority) {
                            System.out.println("Please enter the priority of your ticket (Number between 1 and 5).");
                            System.out.println("1: Very low priority, the issue does not have an extreme effect on the program");
                            System.out.println("5: Extreme priority, the issue may be threatening the program");
                            priority = kb.nextInt();

                            if(priority < 1 || priority > 5){
                                System.out.println("ERROR:The priority must be between 1 and 5");
                                System.out.println("You entered " + priority);
                            } else {
                                correctPriority = true;
                            }
                        }
                        String ticketID = currentUser.getUsername().substring(0,3) + ticketNum;
                        ticketNum++;
                        Ticket newTicket = new Ticket(ticketID, description, priority, LocalDateTime.now(), currentUser.getUsername(), "Unassigned", "Pending");
                        tickets.enqueue(newTicket);



                        System.out.println("Your ticket has been recorded");
                        break;
                    case 2:
                        break;
                    case 3:
                        kb.nextLine();
                        break;
                    case 4:
                        kb.nextLine();
                        System.out.println("Are you sure you want to log out?");
                        System.out.println("Y/N");
                        String logout = kb.nextLine();
                        if(logout.equalsIgnoreCase("Y")){
                            end = true;
                        }
                }
            }
        }
        if (userType == 'a') { //Oscar Figeac
            boolean end = false;

            while (!end) {
                System.out.println("What would you like to do?");
                System.out.println("(1) View all unassigned open tickets");
                System.out.println("(2) View tickets assigned to you");
                System.out.println("(3) Assign a new ticket to yourself");
                System.out.println("(4) View the details of one of your tickets");
                System.out.println("(5) Change the status of a ticket");
                System.out.println("(6) Logout");
                int option = kb.nextInt();
                switch (option){
                    case 1:
                        System.out.println("Looking for unassigned open tickets...");
                        boolean found = false;
                        PriorityQueue tempQueue = new PriorityQueue(); //Created to iterate without risk of losing order

                        while(!tickets.isEmpty()){
                            Ticket ticket = tickets.dequeue();
                            if (ticket.getAgentId() == null && !ticket.getStatus().equalsIgnoreCase("Solved")){ // it only finds unsolved tickets
                                System.out.println(ticket);
                                found = true;
                            }
                            tempQueue.enqueue(ticket);
                        }
                        //Add everything back on to keep the order
                        while(!tempQueue.isEmpty()){
                            tickets.enqueue(tempQueue.dequeue());
                        }
                        if (!found){
                            System.out.println("No tickets were found");
                        }
                        break;
                    case 2:
                        System.out.println("Looking for tickets assigned to you...");
                        boolean foundAgent = false;
                        PriorityQueue tempQueue1 = new PriorityQueue(); //Created to iterate without risk of losing order
                        if (currentAgent != null) {
                            while (!tickets.isEmpty()) {
                                Ticket ticket = tickets.dequeue();
                                if (currentAgent.getAgentID() == null && ticket.getAgentId() != null
                                && ticket.getAgentId().equalsIgnoreCase(currentAgent.getAgentID()) &&
                                !ticket.getStatus().equalsIgnoreCase("Solved")) { //it only finds unsolved tickets
                                    System.out.println(ticket);
                                    foundAgent = true;
                                }
                                tempQueue1.enqueue(ticket);
                            }
                        } else {
                            System.out.println("Unauthorised access");
                        }
                        //Add everything back on to keep the order
                        while(!tempQueue1.isEmpty()){
                            tickets.enqueue(tempQueue1.dequeue());
                        }
                        if (!foundAgent){
                            System.out.println("No tickets were found");
                        }
                        break;
                    case 3:
                        System.out.println("Assigning a new ticket...");
                        if (currentAgent != null){
                            Ticket highestPriority = tickets.peek();
                            if (highestPriority != null && highestPriority.getAgentId() == null
                            && !highestPriority.getStatus().equalsIgnoreCase("Solved")){
                                Ticket assigned = tickets.dequeue();
                                assigned.setAgentId(currentAgent.getAgentID());
                                System.out.println("Ticket number " + assigned.getTicketId() + " has been assigned to you");
                            } else {
                                System.out.println("No tickets could be assigned to you");
                            }
                        } else {
                            System.out.println("Unauthorised access");
                        }
                        break;
                    case 4:
                        System.out.println("Ticket details:");
                        if (currentAgent != null && currentAgent.getAgentID() != null){
                            PriorityQueue tempQueue2 = new PriorityQueue();
                            boolean foundTickets = false;

                            while (!tickets.isEmpty()){
                                Ticket ticket = tickets.dequeue();
                                if (ticket.getAgentId() != null && ticket.getAgentId().equalsIgnoreCase(currentAgent.getAgentID())){
                                    System.out.println(ticket);
                                    foundTickets = true;
                                }
                                tempQueue2.enqueue(ticket);
                            }
                            while (!tempQueue2.isEmpty()){
                                tickets.enqueue(tempQueue2.dequeue());
                            }
                            if (!foundTickets){
                                System.out.println("No tickets are assigned to you");
                            }
                        } else {
                            System.out.println("Unauthorised access");
                        }
                        break;
                    case 5:
                        System.out.println("Changing ticket status...");
                        if (currentAgent != null && currentAgent.getAgentID() != null){
                            System.out.println("Enter the new status (Pending, In Progress, Stalled, Solved):");
                            System.out.println("Enter the ID of your assigned ticket to update:");
                            String toBeUpdated = kb.nextLine();
                            System.out.println("Enter the new status (Pending, In Progress, Stalled, Solved):");
                            String newStatusStr = kb.nextLine();
                            String newStatus = Ticket.statusOptions(newStatusStr); //using the statusOption method from the Ticket class

                            if (newStatus == null){
                                System.out.println("Invalid status entered");
                                break;
                            }

                            PriorityQueue tempQueue3 = new PriorityQueue();
                            boolean updated = false;
                            while(!tickets.isEmpty()){
                                Ticket ticket = tickets.dequeue();
                                if (ticket.getAgentId() != null && ticket.getAgentId().equals(currentAgent.getAgentID()) &&
                                        ticket.getTicketId().equals(toBeUpdated)){
                                    ticket.setStatus(newStatus);
                                    System.out.println("Ticket " + toBeUpdated + " status has been updated to: " +
                                            newStatus);
                                    updated = true;
                                }
                            }

                            while(!tempQueue3.isEmpty()){
                                tickets.enqueue(tempQueue3.dequeue());
                            }
                            if (!updated){
                                System.out.println("Ticket " + toBeUpdated + " could not be found");
                            }
                        } else{
                            System.out.println("Unauthorised access");
                        }
                        break;
                    case 6:
                        kb.nextLine();
                        System.out.println("Are you sure you want to log out?");
                        System.out.println("Y/N");
                        String logout = kb.nextLine();
                        if(logout.equalsIgnoreCase("Y")){
                            end = true;
                        }
                }
            }
        }

    }
}
