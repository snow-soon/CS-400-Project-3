import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * @author 
 *
 */
public class BackendDeveloperTests {

	/**
	 * test searchByDeparture method
	 */
	@Test
	public void test1() {
        ITicketBackend bd = null;
		try {
			ITicketLoader ticketLoader = new TicketLoader();
        	// load the flight tickets from the data file
        	List<ITicket> ticketList = ticketLoader.loadTickets("Tickets.dot");

        	// instantiate the back-end
        	bd = new TicketBackend();

        	// add all the tickets into the back-end
        	for (ITicket ticket: ticketList) {
            	bd.addTicket(ticket);
        	}
			String departure = "Miami, FL (Metropolitan Area)";
        	assertTrue(bd.searchByDeparture(departure));
		}catch(Exception E) {}
	}

    /**
	 * test searchByDestination method
	 */
	@Test
	public void test2() {
		ITicketBackend bd = null;
		try {
			ITicketLoader ticketLoader = new TicketLoader();
        	// load the flight tickets from the data file
        	List<ITicket> ticketList = ticketLoader.loadTickets("Tickets.dot");

        	// instantiate the back-end
        	bd = new TicketBackend();

        	// add all the tickets into the back-end
        	for (ITicket ticket: ticketList) {
            	bd.addTicket(ticket);
        	}
			String departure = "Miami, FL (Metropolitan Area)";
        	assertTrue(bd.searchByDestination(departure));
		}catch(Exception E) {} 
	}

    /**
	 * test pathFound Method
	 */
	@Test
	public void test3() {
        ITicketBackend bd = null;
		try {
			ITicketLoader ticketLoader = new TicketLoader();
        	// load the flight tickets from the data file
        	List<ITicket> ticketList = ticketLoader.loadTickets("Tickets.dot");

        	// instantiate the back-end
        	bd = new TicketBackend();

        	// add all the tickets into the back-end
        	for (ITicket ticket: ticketList) {
            	bd.addTicket(ticket);
        	}
			String departure = "Miami, FL (Metropolitan Area)";
			String destination = "Chicago, IL" ;
        	assertTrue(bd.pathFound(departure, destination));
		}catch(Exception E) {} 
	}

    /**
	 * test getCheapestPath method
	 */
	@Test
	public void test4() {
		ITicketBackend bd = null;
		try {
			ITicketLoader ticketLoader = new TicketLoader();
        	// load the flight tickets from the data file
        	List<ITicket> ticketList = ticketLoader.loadTickets("Tickets.dot");

        	// instantiate the back-end
        	bd = new TicketBackend();

        	// add all the tickets into the back-end
        	for (ITicket ticket: ticketList) {
            	bd.addTicket(ticket);
        	}
			
			String departure = "Miami, FL (Metropolitan Area)";
			String destination = "Atlanta, GA (Metropolitan Area)";
			List<ITicket> pathList = bd.getCheapestPath(departure, destination);
			ITicket ticket = pathList.get(0);
        	assertEquals(ticket.getDeparture(), "Miami, FL (Metropolitan Area)");
		}catch(Exception E) {} 
        
	}

    /**
	 * test getLeastTransfer method
	 */
	@Test
	public void test5() {
		ITicketBackend bd = null;
		try {
			ITicketLoader ticketLoader = new TicketLoader();
        	// load the flight tickets from the data file
        	List<ITicket> ticketList = ticketLoader.loadTickets("Tickets.dot");

        	// instantiate the back-end
        	bd = new TicketBackend();

        	// add all the tickets into the back-end
        	for (ITicket ticket: ticketList) {
            	bd.addTicket(ticket);
        	}
			
			String departure = "Los Angeles, CA (Metropolitan Area)" ;
			String destination = "Austin, TX";
			List<ITicket> pathList = bd.getLeastTransfer(departure, destination);
			ITicket ticket = pathList.get(0);
        	assertEquals(ticket.getDeparture(), "Los Angeles, CA (Metropolitan Area)" );
		}catch(Exception E) {} 
		
	}

	/**
     * tests returning the 20th Ticket's departure in the DOT file
     */
    @Test
    public void CodeReviewOfDataWranger1() {
        String expected = null; // ideal departure
        try {
            // load the ticket data
            TicketLoader ticketLoader = new TicketLoader();
            List<ITicket> ticketList = new ArrayList();
            ticketList = ticketLoader.loadTickets("Tickets.dot");
            expected = ticketList.get(19).getDeparture();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // check if this is correct
        assertEquals(expected, "Las Vegas, NV");
    }

 

    /**
     * tests returning the 21th ticket's Destination in the DOT file
     */

    @Test
    public void CodeReviewOfDataWranger2() {

        String expected = null; // ideal destination
        try {
            // load the ticket data
            TicketLoader ticketLoader = new TicketLoader();
            List<ITicket> ticketList = new ArrayList();
            ticketList = ticketLoader.loadTickets("Tickets.dot");
            expected = ticketList.get(20).getDestination();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // check if this is correct
       assertEquals(expected, "Las Vegas, NV");
    }

	/**
	 * Integration
	 */
	@Test
	public void Integration1() {

		try {
			ITicketLoader ticketLoader = new TicketLoader();
		     List<ITicket> ticketList = ticketLoader.loadTickets("Tickets.dot");
			TextUITester tester = new TextUITester("3\nChicago, IL\nDayton, OH\n");
			Scanner scanner = new Scanner(System.in);
			ITicketBackend backend = new TicketBackend();
			TicketFrontEnd _instance = new TicketFrontEnd(scanner, backend);
    		String out = "          Chicago, IL to Dayton, OH - $120.86\n";
    		
    		_instance.runCommandLoop();
			String output = tester.checkOutput();
			System.out.println(output);
			assertTrue(output.contains(out));
		} catch(Exception e) {};
	}
	
	/**
	 * Integration
	 */
	@Test
	public void Integration2() {

		try {
			 ITicketLoader ticketLoader = new TicketLoader();
		     List<ITicket> ticketList = ticketLoader.loadTickets("Tickets.dot");
			TextUITester tester = new TextUITester("3\nBoston, MA (Metropolitan Area)\nSt. Louis, MO\n");
			Scanner scanner = new Scanner(System.in);
			ITicketBackend backend = new TicketBackend();
			TicketFrontEnd _instance = new TicketFrontEnd(scanner, backend);
    		String out = "          Boston, MA (Metropolitan Area) to St. Louis, MO - $248.00\n";
    		
    		_instance.runCommandLoop();
			String output = tester.checkOutput();
			System.out.println(output);
			assertTrue(output.contains(out));
		} catch(Exception e) {};
	}
}
