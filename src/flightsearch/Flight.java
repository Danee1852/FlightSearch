package flightsearch;

import java.math.BigDecimal;

public class Flight {

    private String departure;
    private String arrival;
    private BigDecimal price;

    public Flight(String departure, String arrival, BigDecimal price){

        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
    }

    public void flightInformation(){
        System.out.println("Flight form "+departure+" to "+arrival+" costs "+price);
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Flight) {
            Flight flight = (Flight) o;
            return this.departure.equalsIgnoreCase(flight.getDeparture()) &&
                    this.arrival.equalsIgnoreCase(flight.getArrival()) && this.price.compareTo(flight.getPrice())==0;
        }
        return false;
    }
}
