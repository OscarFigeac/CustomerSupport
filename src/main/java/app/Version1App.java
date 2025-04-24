package app;

import business.Agent;
import business.User;
import utils.UserHashMap;

import java.util.Scanner;

public class Version1App {
    public static void main(String[] args) {
        UserHashMap map = new UserHashMap();
        Scanner kb = new Scanner(System.in);

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
        //Tomasz
        User user = new User(null, null);
        User agent = new Agent(null, null, null, null);

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
                        if(map.containsKey(username)){
                            System.out.println("Username is taken");
                        }else if(username.length() < 5){
                            System.out.println("The username must contain at least 5 characters");
                        }else{
                            usernameExists = false;
                        }
                    }
                    System.out.println("Enter a password");
                    String password = kb.nextLine();
                    user = new User(username, password);
                    map.put(username, user);

                    end = true;

                } else if (answer.equalsIgnoreCase("agent")) {
                    boolean usernameExists = true;
                    String username = null;
                    while(usernameExists) {
                        System.out.println("Enter a username");
                        username = kb.nextLine();
                        if(map.containsKey(username)){
                            System.out.println("Username is taken");
                        }else if(username.length() < 5){
                            System.out.println("The username must contain at least 5 characters");
                        }else{
                            usernameExists = false;
                        }
                    }
                    System.out.println("Enter a password");
                    String password = kb.nextLine();
                    System.out.println("Enter your name");
                    String name = kb.nextLine();
                    String id = name.substring(0,2) + name.substring(username.length()/2, (username.length()/2)+2) + map.generateAgentNum();
                    agent = new Agent(username, password, id, name);
                    map.put(username, user);

                    end = true;
                }else{
                    System.out.println("Please enter a valid answer");
                }
            }
        }

            /*TODO: Users can log in if they provide valid credentials.
                - They should not need to state if they are logging in as a User or an Agent.
            */

        //Main app
            //Required standard user ticket-handling functionality:
                //TODO: Create a new ticket.
                //TODO: view the list of their tickets (open and closed)
                //TODO: View the details of one of their tickets
            //Required Agent ticket-handling functionality (in addition to the standard user functionality listed above):
                //TODO: View all unassigned open tickets
                //TODO: View their currently assigned tickets
                //TODO: Assign themselves a new ticket – this should take the unassigned ticket with the highest priority
                //TODO: View the details of one of their assigned tickets
                /*TODO: Change the status on one of their assigned tickets.
                - Tickets with a solved/closed status should be removed from the Agent’s collection of open tickets
                    and moved to their collection of closed tickets.
                */

    }
}
