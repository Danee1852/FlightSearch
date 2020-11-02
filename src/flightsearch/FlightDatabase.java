package flightsearch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FlightDatabase {
    private List<Flight> flightList= new ArrayList<>();

    public FlightDatabase(){
        flightList.add(new Flight("Warsaw","Berlin",new BigDecimal("19.99")));
        //flightList.add(new Flight("Warsaw","Paris",new BigDecimal("19.99")));
        flightList.add(new Flight("Warsaw","Madrid",new BigDecimal("199.99")));
        flightList.add(new Flight("Warsaw","London",new BigDecimal("200.99")));
        flightList.add(new Flight("Berlin","Warsaw",new BigDecimal("150.99")));
        flightList.add(new Flight("Madrid","Warsaw",new BigDecimal("120.99")));
        flightList.add(new Flight("Paris","Warsaw",new BigDecimal("300.99")));
        flightList.add(new Flight("London","Warsaw",new BigDecimal("250.99")));
        flightList.add(new Flight("London","Paris",new BigDecimal("1.99")));
        flightList.add(new Flight("Berlin","Paris",new BigDecimal("3.99")));

    }

    private Flight checkIfFlightExists(String start, String end){
        for (Flight flight:flightList) {
            if (flight.getDeparture().equalsIgnoreCase(start) && flight.getArrival().equalsIgnoreCase(end)){
                return flight;
            }
        }
        return null;
    }

    public  void displayFlightsFromCity(String city){
        displayFlights(getFlightsFromCity(city));
    }

    public  void displayFlightsToCity(String city){
        displayFlights(getFlightToCity(city));
    }

    public List<Flight> getFlightsFromCity(String city){
        return getFlightWithCity(Flight::getDeparture, city);
    }

    public List<Flight> getFlightToCity(String city){
       return getFlightWithCity(Flight::getArrival, city);
    }

    private List<Flight> getFlightWithCity(Function<Flight, String> getCity, String city){
        List<Flight> list = new ArrayList<>();

        for(Flight flight:flightList)
            if(getCity.apply(flight).equalsIgnoreCase(city))
                list.add(flight);

        return list;
    }

    private void displayFlights(List<Flight> list){
        if (list.isEmpty()){
            System.out.println("The flight is not exist.");
            return;
        }
        for (Flight flight:list) {
            flight.flightInformation();
        }
    }

    public List<String> getCities(){
        List<String> list = new ArrayList<>();
        for (Flight flight:flightList) {
            if (!list.contains(flight.getDeparture())){
                list.add(flight.getDeparture());
            }
            if (!list.contains(flight.getArrival())){
                list.add(flight.getArrival());
            }
        }
        return list;
    }

    public Flight getCheapestFlight(){
        return getCheapestFlight(this.flightList);
    }

    private Flight getCheapestFlight(List<Flight> list){
        Flight cheapestFlight = null;
        for (Flight flight:list) {
            if (cheapestFlight==null || cheapestFlight.getPrice().compareTo(flight.getPrice())>0){
                cheapestFlight=flight;
            }
        }
        return cheapestFlight;
    }

    public Flight getCheapestFlightFromCity(String city){
        List<Flight> list = getFlightsFromCity(city);
        return getCheapestFlight(list);
    }
    public Flight getCheapestFlightToCity(String city){
        List<Flight> list = getFlightToCity(city);
        return getCheapestFlight(list);
    }
    public void getFlights(String start,String end){
        Flight strictFlight = checkIfFlightExists(start,end);
        if (strictFlight!=null){
            strictFlight.flightInformation();
            return;
        }

        List<Journey> finalList = new ArrayList<>();
        List<Flight> startList = getFlightsFromCity(start);
        List<Flight> endList = getFlightToCity(end);
        for (Flight startFlight:startList) {
            for (Flight endFlight:endList) {
                if (startFlight.getArrival().equalsIgnoreCase(endFlight.getDeparture())){
                    finalList.add(new Journey(startFlight,endFlight));
                }
            }

        }

        Journey cheapestJourney = getCheapestJourney(finalList);
        for (Journey journey:finalList) {
            if(cheapestJourney.equals(journey)) System.out.print("(cheapest) ");
            System.out.println(journey);
        }
    }
    private Journey getCheapestJourney(List<Journey> list){
        Journey cheapestJourney=null;
        for (Journey journey:list) {
            if (cheapestJourney==null||journey.getPrice().compareTo(cheapestJourney.getPrice())<0){
                cheapestJourney=journey;
            }
        }
        return cheapestJourney;
    }

}
