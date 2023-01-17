// --== CS400 Fall 2022 File Header Information ==--
// Name: <Soonho Chung>
// Email: <schung75@wisc.edu>
// Team: <CT>
// TA: <Ilay>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketFrontEnd implements ITicketFrontEnd{
	Scanner sc;
	ITicketBackend backend;

	/**
	 * The constructor. It takes the Scanner that will read user input as
     * a parameter as well as the backend.
     *
	 * @param userInputScanner  The scanner of user input
	 * @param backend  the backend object for access of backend method
	 */
	public TicketFrontEnd(Scanner userInputScanner, ITicketBackend backend) {
		//assignment
		this.sc = userInputScanner;
		this.backend = backend;
	}

	/**
	 * This method starts the command loop for the frontend, and will
     * terminate when the user exists the app.
	 */
	@Override
	public void runCommandLoop() {
		//welcome message
		System.out.println("Welcome to the Plane Ticket Application!\n"
							+ "x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n"
							+ " ");
		while(true) {
			displayMainMenu();
			String input = sc.nextLine();

			if(input != null && input.strip().equals("1")) {
				printAllLocations();
			}

			else if(input != null && input.strip().equals("2")) {
				printAllTickets();
			}

			else if(input != null && input.strip().equals("3")) {
				System.out.println("You are in the departure and arrival menu: ");
				System.out.println("          Please enter the departure location: ");
				String departure = sc.nextLine();
				if(backend.searchByDeparture(departure)) {
					System.out.println("          Please enter the arrival location: ");
					String destination = sc.nextLine();
					if(backend.searchByDestination(destination)) {
						if (backend.pathFound(departure,destination)) {
							System.out.println("Ticket Found!");
							System.out.println("Displaying cheapest path:");
							System.out.println(printCheapestPath(departure, destination));
							System.out.println("Displaying least transfer path:");
							System.out.println(printLeastTransfer(departure, destination));
						}
						else {
							System.out.println("No path available.");

						}
					}
					else System.out.println("Invalid Input! Location not found!\n");
				}
				else System.out.println("Invalid Input! Location not found!\n");
			}

			//terminate
			else if(input != null && input.strip().equals("4")) {
				System.out.print("Goodbye!");
				sc.close();
				return;
			}

			else {
				System.out.println("Invalid Input!");
			}
		}
	}

	/**
	 * prints command options to System.out
	 */
	@Override
	public void displayMainMenu() {
		String menu = "You are in the Main Menu:\n"
				+ "          1) List All Locations\n"
				+ "          2) List All Tickets\n"
				+ "          3) Choose Departure And Arrival Location\n"
				+ "          4) Exit Application";
		System.out.println(menu);
	}

	/**
	 * Prints out the ticket info
	 */
	@Override
	public String displayTicket(ITicket ticket) {
		if(ticket == null) {
			return null;
		}
		return ticket.getDeparture() + " to " + ticket.getDestination() + " - $" +
				ticket.getPrice();
	}

	/**
	 * Prints out all ticket info
	 */
	@Override
	public void printAllTickets() {
		System.out.println("List all tickets: ");
		ITicketLoader ticketLoader = new TicketLoader();
		List<ITicket> ticketList = null;
		try {
			ticketList = ticketLoader.loadTickets("Tickets.dot");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < ticketList.size(); i++) {
			System.out.println("          " + (i+1) + ") " + displayTicket(ticketList.get(i)));
		}
	}

	/**
	 * Prints out all locations
	 */
	@Override
	public void printAllLocations() {
		System.out.println("List all locations: ");
		ITicketLoader ticketLoader = new TicketLoader();
		List<ITicket> ticketList = null;
		try {
			ticketList = ticketLoader.loadTickets("Tickets.dot");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<String> locations = new ArrayList<>();
		for (int i = 0; i < ticketList.size(); i++) {
			if (!isAdded(locations, ticketList.get(i).getDeparture())) {
				locations.add(ticketList.get(i).getDeparture());
			}
			if (!isAdded(locations, ticketList.get(i).getDestination())) {
				locations.add(ticketList.get(i).getDestination());
			}
		}
		for (int i = 0; i < locations.size(); i++) {
			System.out.println("          " + (i+1) + ") " + locations.get(i));
		}
	}

	/**
	 * Helper method for printAllLocations()
	 */
	@Override
	public boolean isAdded(List<String> locations, String location) {
		for (int i = 0; i < locations.size(); i++) {
			if (location.equals(locations.get(i))) {
				// added already
				return true;
			}
		}
		// not added
		return false;
	}

	/**
	 * Returns string form of cheapest path's info
	 */
	public String printCheapestPath(String departure, String destination) {
		String toReturn = "";
		List<ITicket> path = backend.getCheapestPath(departure, destination);
		double totalPrice = 0.0;
		for (int i = 0; i < path.size(); i++) {
			toReturn += ("          " + displayTicket(path.get(i)) + "\n");
			totalPrice = totalPrice + path.get(i).getPrice();
		}
		toReturn += ("          Total Price: $" + totalPrice);

		return toReturn;
	}

	/**
	 * Returns string form of least transfer path's info
	 */
	public String printLeastTransfer(String departure, String destination) {
		String toReturn = "";
		List<ITicket> path = backend.getLeastTransfer(departure, destination);
		double totalPrice = 0.0;
		for (int i = 0; i < path.size(); i++) {
			toReturn += ("          " + displayTicket(path.get(i)) + "\n");
			totalPrice = totalPrice + path.get(i).getPrice();
		}
		toReturn += ("          Total Price: $" + totalPrice);

		return toReturn;
	}
}
