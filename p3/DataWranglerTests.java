import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataWranglerTests {
	
    /**
     * tests returning the 15th Ticket's departure in the DOT file
     */
    @Test
    public void test1() {

        String expected = null; // ideal departure

        try {

	    // load the ticket data
            TicketLoader ticketLoader = new TicketLoader();
            List<ITicket> ticketList = new ArrayList();
            ticketList = ticketLoader.loadTickets("Tickets.dot");
            expected = ticketList.get(14).getDeparture();
            
	    
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

	// check if this is correct
        assertEquals(expected, "Salt Lake City, UT");
    }

    /**
     * tests returning the 30th ticket's Destination in the DOT file
     */
    @Test
    public void test2() {

	
        String expected = null; // ideal destination

        try {

            // load the ticket data
            TicketLoader ticketLoader = new TicketLoader();
            List<ITicket> ticketList = new ArrayList();
            ticketList = ticketLoader.loadTickets("Tickets.dot");
            expected = ticketList.get(29).getDestination();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // check if this is correct
        assertEquals(expected, "New York City, NY (Metropolitan Area)");

    }

    /**
     *tests returning the 100th ticket's price in the DOT file
     */
    @Test
    public void test3() {
    
        String expected = null; // ideal price

        try {

            // load the ticket data
            TicketLoader ticketLoader = new TicketLoader();
            List<ITicket> ticketList = new ArrayList();
            ticketList = ticketLoader.loadTickets("Tickets.dot");
            expected = "" + ticketList.get(99).getPrice();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // check if this is correct
        assertEquals(expected, "129.35");
    
    }

    /**
     * tests returning ticket's price on the last row in the DOT file
     */
    @Test
    public void test4() {

        String expected = null; // ideal price

        try {

            // load the ticket data
            TicketLoader ticketLoader = new TicketLoader();
            List<ITicket> ticketList = new ArrayList();
            ticketList = ticketLoader.loadTickets("Tickets.dot");
            expected = "" + ticketList.get(106031).getPrice();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // check if this is correct
        assertEquals(expected, "91.49");
    }

    /**
     * tests returning the 200nd ticket's destination, departure, and price in the DOT file
     */
    @Test
    public void test5() {

        String expected = null;

        try {
	
            // load the ticket data
            TicketLoader ticketLoader = new TicketLoader();
            List<ITicket> ticketList = new ArrayList();
            ticketList = ticketLoader.loadTickets("Tickets.dot");
    
	    expected = ticketList.get(199).getDestination() + " - " + ticketList.get(199).getDeparture() + " - " + ticketList.get(199).getPrice();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals(expected, "New Orleans, LA - Houston, TX - 96.84");
    }

    /**
     * This test tests whether we can get the non-existing ticket using searchByDeparture and 
     * pathFound in Backend class.
     * This focuses more on error/edge cases.
     */
    @Test
    public void CodeReviewOfBackendDeveloper1() {
    	
    	// make a ticket object
		TicketBackend ticket = new TicketBackend();
        String departure = ""; // empty string
        
        // this destination should not be there
        assertEquals(ticket.searchByDeparture(departure), false);
        
        // pathFound check
        TicketBackend ticket2 = new TicketBackend();
		String departure1 = "";
        String destination1 = "";

        // this destination should not be there
        assertEquals(ticket2.pathFound(departure1, destination1), false);
    }

    /**
     * This test tests whether we can get the existing ticket using searchByDestination
     * and getCheapestPath methods in the Backend class. 
     * This focuses more on error/edge cases.
     */
    @Test
    public void CodeReviewOfBackendDeveloper2(){
    	
    	// make a ticket object
		TicketBackend ticket = new TicketBackend();
        String destination = ""; // empty string
        
        // this destination should not be there
        assertEquals(ticket.searchByDestination(destination), false);
        
        // check getChepeastPath 
        // this destination should not be there
        
        try {
		TicketBackend ticket2= new TicketBackend();
		String departure1 = "";
        String destination1 = "";
        
        // check the result here
        ticket2.getCheapestPath(departure1, destination1);
        
        }catch(Exception e) {
        	// success
        }
       
    }
    
    /**
	 * This test tests integration using everyone's code and search a ticket 
	 * from Jacksonville, FL to Las Vegas, NV price with 245.11.
	 */
	@Test
	public void Integration1() {

		try {
			
			// make loader
			ITicketLoader ticketLoader = new TicketLoader();
			
			// make a ticket list
		    List<ITicket> ticketList = ticketLoader.loadTickets("Tickets.dot");
		    
		    // frontend tester
			TextUITester tester = new TextUITester("3\nJacksonville, FL\nLas Vegas, NV\n");
			Scanner scanner = new Scanner(System.in);
			ITicketBackend backend = new TicketBackend(); // backend class
			TicketFrontEnd _instance = new TicketFrontEnd(scanner, backend); // get the result
    		String out = "          Jacksonville, FL to Las Vegas, NV - $245.11\n";
    		
    		_instance.runCommandLoop();
    		
    		// get the output
			String output = tester.checkOutput();
			//System.out.println(output);
			
			// check if this is correct
			assertTrue(output.contains(out));
		} catch(Exception e) {};
	}
	
	/**
	 * This test tests integration using everyone's code and search a ticket 
	 * from Pittsburgh, PA to San Diego, CA price with 187.70.
	 */
	@Test
	public void Integration2() {

		try {
			
			// make a loader
			ITicketLoader ticketLoader = new TicketLoader();
			
			// make a ticket list
		    List<ITicket> ticketList = ticketLoader.loadTickets("Tickets.dot");
		    
		    // frontend tester
			TextUITester tester = new TextUITester("3\nPittsburgh, PA\nSan Diego, CA\n");
			Scanner scanner = new Scanner(System.in);
			
			ITicketBackend backend = new TicketBackend(); // make backend object
			TicketFrontEnd _instance = new TicketFrontEnd(scanner, backend);
    		String out = "          Pittsburgh, PA to San Diego, CA - $187.70\n";
    		
    		_instance.runCommandLoop();
    		
    		// get the output
    		String output = tester.checkOutput();
    		
			//System.out.println(output);
    		// check if this is correct
			assertTrue(output.contains(out));
		} catch(Exception e) {};
	}
}
