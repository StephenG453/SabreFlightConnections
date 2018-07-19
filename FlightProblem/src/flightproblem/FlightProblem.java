package flightproblem;

public class FlightProblem 
{
    private static final String FILE_LOCATION = "/Users/cbrat62/Desktop/Flights.txt";   //Location of the file being read
    
    public static void main(String[] args) 
    {        
        Flight flight = new Flight(FILE_LOCATION);
        
        System.out.println(flight.getFlights());
    }
}
