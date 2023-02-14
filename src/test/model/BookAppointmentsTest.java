package model;

import exceptions.DateNotAvailable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// test for BookAppointments
public class BookAppointmentsTest {

    private BookAppointments bookedAppointments;
    private ArrayList<Services> listOfServices;
    private ArrayList<Workers> listOfWorkers;
    private ArrayList<Dates> listOfDates;
    private Services service1;
    private Services service2;
    private Workers worker1;
    private Workers worker2;
    private Dates date1;


    @BeforeEach
    public void runBefore() {
        service1 = new Services("LawnMowing&Trimming");
        service2 = new Services("YardCleanup");
        worker1 = new Workers("Harman");
        worker2 = new Workers("Sahil");
        date1 = new Dates("July", 27);

        listOfServices = new ArrayList<>();
        listOfWorkers = new ArrayList<>();
        listOfDates = new ArrayList<>();

        bookedAppointments = new BookAppointments(listOfServices, listOfWorkers, listOfDates);
    }

    @Test
    public void testConstructorOfBookAppointments() {
        assertEquals(bookedAppointments.getServiceList(), listOfServices);
        assertEquals(bookedAppointments.getWorkersList(), listOfWorkers);
        assertEquals(bookedAppointments.getDatesList(), listOfDates);
    }

    @Test
    public void testBookService() {
        bookedAppointments.bookService(service1);
        assertEquals(bookedAppointments.getServiceList().size(),1);
    }

    @Test
    public void testBookMultipleService() {
        bookedAppointments.bookService(service1);
        assertEquals(bookedAppointments.getServiceList().size(),1);
        assertTrue(bookedAppointments.getServiceList().contains(service1));
        bookedAppointments.bookService(service2);
        assertEquals(bookedAppointments.getServiceList().size(),2);
        assertTrue(bookedAppointments.getServiceList().contains(service2));
    }

    @Test
    public void testBookWorker() {
        bookedAppointments.bookWorker(worker1);
        assertEquals(bookedAppointments.getWorkersList().size(),1);
        assertTrue(bookedAppointments.getWorkersList().contains(worker1));
    }

    @Test
    public void testBookMultipleWorkers() {
        bookedAppointments.bookWorker(worker1);
        assertEquals(bookedAppointments.getWorkersList().size(),1);
        assertTrue(bookedAppointments.getWorkersList().contains(worker1));
        bookedAppointments.bookWorker(worker2);
        assertEquals(bookedAppointments.getWorkersList().size(),2);
        assertTrue(bookedAppointments.getWorkersList().contains(worker2));
    }

    @Test
    public void testBookDateNotScheduledYet() {
        bookedAppointments.bookWorker(worker1);
        try{
            bookedAppointments.bookDate(date1);
        } catch (DateNotAvailable e) {
            fail("Should not be thrown");
        }

    }

    @Test
    public void testBookDateAlreadyScheduledForSpecificDate() {
        bookedAppointments.bookWorker(worker1);
        try{
            bookedAppointments.bookDate(date1);
            bookedAppointments.bookDate(date1);
        } catch (DateNotAvailable e) {
           //should throw the exception
        }

    }

    @Test
    public void testCheckIfDateOpen() {
        bookedAppointments.bookWorker(worker1);
        assertEquals(bookedAppointments.checkIfDateOpen(),worker1);
    }

    @Test
    public void testRemoveLastAddedAppointment() throws DateNotAvailable {
        try{
            bookedAppointments.bookService(service1);
            bookedAppointments.bookWorker(worker1);
            bookedAppointments.bookDate(date1);
        } catch (DateNotAvailable e) {
            // no exception thrown
        }

        assertEquals(bookedAppointments.getServiceList().size(),1);
        assertEquals(bookedAppointments.getWorkersList().size(),1);
        assertEquals(bookedAppointments.getDatesList().size(),1);

        bookedAppointments.removeAppointment();

        assertEquals(bookedAppointments.getServiceList().size(),0);
        assertEquals(bookedAppointments.getWorkersList().size(),0);
        assertEquals(bookedAppointments.getDatesList().size(),0);




    }

}
