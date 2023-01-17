// --== CS400 Fall 2022 File Header Information ==--
// Name: <Soonho Chung>
// Email: <schung75@wisc.edu>
// Team: <CT>
// TA: <Ilay>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>


public class TicketFD implements ITicket{
    Double price;
    String destination;
    String departure;
    String genre;

    public TicketFD(Double price, String departure, String destination) {
        this.price = price;
        this.destination = destination;
        this.departure = departure;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public String getDestination() {
        return this.destination;
    }

    @Override
    public String getDeparture() {
        return this.departure;
    }
}
