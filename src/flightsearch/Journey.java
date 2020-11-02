package flightsearch;

import java.math.BigDecimal;

public class Journey {
    private Flight first;
    private Flight second;

    public Journey(Flight first, Flight second){
        if (first.getArrival().equalsIgnoreCase(second.getDeparture())) {
            this.first = first;
            this.second = second;
        }
    }

    public Flight getFirst() {
        return first;
    }

    public Flight getSecond() {
        return second;
    }

    @Override
    public String toString(){
        return "Flight from "+first.getDeparture()+" to "+second.getArrival()+" through "+first.getArrival()+
                " costs "+(first.getPrice().add(second.getPrice()));
    }
    @Override
    public boolean equals(Object o){
       if(o instanceof Journey) {
           Journey journey = (Journey) o;
           return this.first.equals(journey.getFirst()) && this.second.equals(journey.getSecond());
       }
       return false;
    }
    public BigDecimal getPrice(){
        return (first.getPrice().add(second.getPrice()));
    }
}
