package flightsearch;

public class Main {
    public static void main(String[] args){

     FlightDatabase flight = new FlightDatabase();
     /*flight.getCheapestFlight().flightInformation();
     flight.getCheapestFlightFromCity("Warsaw").flightInformation();
     System.out.println();
     flight.displayFlightsFromCity("warsaw");
     System.out.println();
     flight.displayFlightsFromCity("rome");
     System.out.println();
     flight.displayFlightsToCity("warsaw");
     System.out.println();
     flight.displayFlightsToCity("rome");*/

     flight.getFlights("warsaw","paris");


    }
}

