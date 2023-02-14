package persistence;

import exceptions.DateNotAvailable;
import model.BookAppointments;
import model.Dates;
import model.Services;
import model.Workers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


// Based on JsonSerializationDemo; link below
// <https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git>

public class JsonReaderTest extends JsonTest {
    private ArrayList<Services> listOfServices;
    private ArrayList<Workers> listOfWorkers;
    private ArrayList<Dates> listOfDate;
    private Services lawnMowing;
    private Dates aug1;
    private Workers worker1;
    private ArrayList<Dates> scheduledDatesForWorker1;

    @BeforeEach
    void runBefore() {
        listOfServices = new ArrayList<Services>();
        listOfWorkers = new ArrayList<Workers>();
        listOfDate = new ArrayList<Dates>();
        lawnMowing = new Services("LawnMowing&Trimming");
        scheduledDatesForWorker1 = new ArrayList<Dates>();
        worker1 = new Workers("Harman");
        aug1 = new Dates("August", 1);
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BookAppointments ba = reader.read();
            fail("IOException expected");
        } catch (IOException | DateNotAvailable e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBookingAppointments() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
        try {
            BookAppointments ba = reader.read();
        } catch (IOException | DateNotAvailable e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBookAppointment() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
        try {
            BookAppointments ba = reader.read();
            ba.getServiceList().add(lawnMowing);
            ba.getWorkersList().add(worker1);
            ba.getDatesList().add(aug1);
            checkServices(ba.getServiceList().get(0).getServiceDescription(), ba.getServiceList().get(0));
            checkWorkers(ba.getWorkersList().get(0).getName(), ba.getWorkersList().get(0));
            checkDate(ba.getDatesList().get(0).getMonth(), ba.getDatesList().get(0).getDay(), ba.getDatesList().get(0));
        } catch (IOException | DateNotAvailable e) {
            fail("Couldn't read from file");
        }
    }

}
