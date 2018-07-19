package flightproblem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

class Flight 
{
    private String fileLocation;
    private BufferedReader bufferedReader = null;
    private FileReader fileReader = null;
    
    private String currentLineInFile = null; //string that contains all the characters in the current line of the file
        
    short numberOfFlightListsInFile = 0;
    
    private TreeMap<Short, String[]> numOfFlightListsMap = new TreeMap<>();
        
    private TreeMap<String, Byte> flightNumAndConnectionsMap_ = new TreeMap<>(Collections.reverseOrder());   //Map that is filled with acceptable flight numbers and their connection count
    
    private byte fileLineNumber = 1; //iterator to keep track of the current line in the file
    
    private boolean isOriginInList = false;  //Flag to check if the origin airport is even in the list
    private byte numOfConnections = 0;   //Number of connections from origin to destination, including the origin airport
    private byte availableFlights = 0;   //Number of flights that are available to be flown
    
    private boolean isAFlightFound = false;  //Flag to check if there was ever an acceptable flight found
    private boolean areAirportsPrinted = false;  //Flag for printing of output - make sure the airports are not repeated
    
    private final byte MAX_CONNECTION_NUMBER = 2;    //Constant - maximum allowed connections, including the origin airport
        
    private StringBuilder outputToConsole = new StringBuilder();    //String that is slowly added to as the program runs, then displayed to the customer
    
    public Flight()
    {
    }
    
    public Flight(String fileLocationString) 
    {
        this.fileLocation = fileLocationString; //Get the file location specified in the main method
    }
    
    StringBuilder getFlights()
    {
        openAndReadFile();
        
        return outputToConsole;
    }
    
    void openAndReadFile()
    {
        try 
        {
            //Create new file and buffered reader
            fileReader = new FileReader(fileLocation);
            bufferedReader = new BufferedReader(fileReader);
            
            //while there is still lines in the file...
            while ((currentLineInFile = bufferedReader.readLine()) != null)
            {             
                String[] splittedLine = currentLineInFile.split("[\\/-]");  //Split up the current line in the file into an array. Regex separates by / and -
                
                if (currentLineInFile.contains("/"))
                {                    
                    numberOfFlightListsInFile++;
                    numOfFlightListsMap.put(numberOfFlightListsInFile, splittedLine);
                }   
                else if (currentLineInFile.contains("-"))
                {
                    checkAllFlightLists(splittedLine[0], splittedLine[1]);
                    resetValuesForNextFlight();
                }
                fileLineNumber++;
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error due to Exception " + e);
        }
        finally
        {
            //Close out of the buffered and file readers... print any exceptions that arise
            try 
            {
                bufferedReader.close();
                fileReader.close();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Flight.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    void checkAllFlightLists(String originAirport, String destinationAirport)
    {        
        //Iterate through every flight list
        for (short list = 1; list <= numOfFlightListsMap.lastKey(); list++)
        {
            //Reset these values when we start to search through another flight list
            numOfConnections = 0;
            isOriginInList = false;
                        
            //Iterate through the strings of the current flight list
            for (String airport : numOfFlightListsMap.get(list))
            {               
                //If an airport in the flight list is found that is the same as the origin airport inputed...
                if (airport.equals(originAirport))
                {
                    isOriginInList = true;
                }
                //If we have found the origin airport in the current flight list...
                if (isOriginInList)
                {
                    //If we have also found the destination in the flight list AND we have not exceeded the maximum amount of connections in the flight list...
                    if (airport.equals(destinationAirport) && numOfConnections <= MAX_CONNECTION_NUMBER)
                    {
                        isAFlightFound = true;
                        availableFlights++;
                        
                        //Iterate through the current flight list and assign the number of connections for the current flight #
                        for (String stringInCurrentList : numOfFlightListsMap.get(list))
                        {                            
                            if (stringInCurrentList.contains("AA"))
                            {                                
                                flightNumAndConnectionsMap_.put(stringInCurrentList, numOfConnections);                                
                            }
                        }
                        //Print the origin and destination airport to be compared if we haven't done so already
                        if (!areAirportsPrinted)
                        {
                            outputToConsole.append(originAirport).append("-").append(destinationAirport);
                            areAirportsPrinted = true;
                        }                        
                    }
                    //After we have found the origin airport in the flight list and as we continue to iterate through the current flight list... increase the connection count
                    numOfConnections++;
                }
            }
        }
        printAvailableFlights(originAirport, destinationAirport);
    }
    
    void printAvailableFlights(String originAirport, String destinationAirport)
    {        
        //Adjust output depending on the amount of flights that are available
        if (availableFlights > 1)
        {
            outputToConsole.append(" Flights ");
        }
        
        for (String key : flightNumAndConnectionsMap_.keySet())
        {            
            if (availableFlights == 1)
            {
                outputToConsole.append(" Flight ").append(key);
            }
            else if (availableFlights > 1)
            {                
                if (key.equals(flightNumAndConnectionsMap_.lastKey()))
                {
                    outputToConsole.append(key).append("");
                }
                else
                {
                    outputToConsole.append(key).append(", ");
                }                                
            }
        }
                
        //If no acceptable flight is found in any list... print the following
        if (!isAFlightFound)
        {
            ///System.out.println(originAirport + "-" + destinationAirport + " No Flights Found");
            outputToConsole.append(originAirport).append("-").append(destinationAirport).append(" No Flights Found");
        }
        
        ///System.out.println();
        outputToConsole.append("\n");
    }
    
    void resetValuesForNextFlight()
    {
        flightNumAndConnectionsMap_.clear();
        numOfConnections = 0;
        availableFlights = 0;
        isOriginInList = false;
        areAirportsPrinted = false;
        isAFlightFound = false;
    }
}
