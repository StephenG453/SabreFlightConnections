/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightproblem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cbrat62
 */
public class FlightTest {
    
    public FlightTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getFlights method, of class Flight.
     */
    @Test(expected = NullPointerException.class)
    public void testGetFlights()  {
        System.out.println("getFlights");
        
        String fileLocation = "/Users/cbrat62/Desktop/Flights.txt";
        
        Flight instance = new Flight();
        
        StringBuilder expResult = new StringBuilder("ORD-MSP Flight AA3333\n" +
                                                    "ATL-HOU Flights AA2222, AA1111\n" +
                                                    "DAL-HOU Flight AA1111\n" +
                                                    "TPA-ORD Flight AA4444");
        StringBuilder result = instance.getFlights();
        assertEquals(expResult, result);
    }

    /**
     * Test of openAndReadFile method, of class Flight.
     */
    @Test
    public void testOpenAndReadFile() {
        System.out.println("openAndReadFile");
        //Flight instance = null;
        //instance.openAndReadFile();
    }

    /**
     * Test of checkAllFlightLists method, of class Flight.
     */
    @Test
    public void testCheckAllFlightLists() {
                
        System.out.println("checkAllFlightLists");
        String originAirport = "";
        String destinationAirport = "";
        //Flight instance = new Flight();
        //instance.checkAllFlightLists(originAirport, destinationAirport);        
    }

    /**
     * Test of printAvailableFlights method, of class Flight.
     */
    @Test
    public void testPrintAvailableFlights() {
        System.out.println("printAvailableFlights");
        String originAirport = "";
        String destinationAirport = "";
        //Flight instance = null;
        //instance.printAvailableFlights(originAirport, destinationAirport);        
    }

    /**
     * Test of resetValuesForNextFlight method, of class Flight.
     */
    @Test
    public void testResetValuesForNextFlight() {
        System.out.println("resetValuesForNextFlight");
        //Flight instance = null;
        //instance.resetValuesForNextFlight();
    }
    
}
