package app;

import java.util.Scanner;

public class Version1App {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        //Welcome screen - Tomasz Januszkiewicz
        System.out.println("Welcome!");
        boolean checkIfExists = true;
        boolean existingAccount;
        while(checkIfExists) {
            System.out.println("Do you have an existing account?");
            System.out.println("Y/N");
            if (kb.nextLine().equalsIgnoreCase("Y")) {
                existingAccount = true;
                checkIfExists = false;
            } else if(kb.nextLine().equalsIgnoreCase("N")) {
                existingAccount = false;
                checkIfExists = false;
            }else{
                System.out.println("Please enter a valid answer");
            }
        }

        //Required authentication functionality
        //TODO: Users can register for this system as either a User or an Agent.
        /*TODO: Users can log in if they provide valid credentials.
              - They should not need to state if they are logging in as a User or an Agent.
         */

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
