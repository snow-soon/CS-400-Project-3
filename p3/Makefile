run: TicketMapper.class TicketLoader.class ITicketLoader.class ITicketBackend.class TicketBackend.class FlightTicketGraph.class ITicket.class FlightTicketADT.class TextUITester.class ITicketFrontEnd.class TicketFrontEnd.class Ticket.class
        java TicketMapper

TicketMapper.class:
        javac TicketMapper.java

FrontendDeveloperTests.class: TextUITester.class Ticket.class TicketBackend.class TicketFrontEnd.class TicketLoader.class
        javac -cp .:junit5.jar FrontendDeveloperTests.java

DataWranglerTests.class: DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java FlightTicketGraph.class
        javac -cp .:junit5.jar AlgorithmEngineerTests.java

BackendDeveloperTests.class: BackendDeveloperTests.java
        javac -cp .:junit5.jar BackendDeveloperTests.java

runDataWranglerTests: DataWranglerTests.class Ticket.class TicketLoader.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=DataWranglerTests


runTests: AlgorithmEngineerTests.class DataWranglerTests.class BackendEngineerTests.class FrontendDeveloperTests.class
        java -jar junit5.jar --class-path=. --include-classname=.* --select-class=BackendEngineerTests
        java -jar junit5.jar --class-path=. --include-classname=.* --select-class=FrontendDeveloperTests
        java -jar junit5.jar --class-path=. --include-classname=.* --select-class=AlgorithmEngineerTests

TextUITester.class: TextUITester.java
        javac TextUITester.java
        
Ticket.class: Ticket.java ITicket.class        
        javac Ticket.java

ITicket.class: ITicket.java
        javac ITicket.java
        
TicketBackend.class: TicketBackend.java ITicketBackend.class
        javac TicketBackend.java
        
ITicketBackend.class: ITicketBackend.java
        javac ITicketBackend.java
        
TicketFrontEnd.class: TicketFrontEnd.java ITicketFrontEnd.class
        javac TicketFrontEnd.java
        
ITicketFrontEnd.class: ITicketFrontEnd.java
        javac ITicketFrontEnd.java
        
TicketLoader.class: TicketLoader.java ITicketLoader.class
        javac TicketLoader.java
        
ITicketLoader.class: ITicketLoader.java
        javac ITicketLoader.java


FlightTicketGraphBD.class: FlightTicketGraphBD.java FlightTicketADT.class
        javac FlightTicketGraphBD.java

FlightTicketADT.class: FlightTicketADT.java
        javac FlightTicketADT.java

clean:
	rm *.class
