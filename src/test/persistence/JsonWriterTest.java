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

public class JsonWriterTest extends JsonTest{

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
        worker1 = new Workers("Harman");
        aug1 = new Dates("August", 1);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            BookAppointments wr = new BookAppointments(listOfServices,listOfWorkers,listOfDate);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            BookAppointments ba = new BookAppointments(listOfServices,listOfWorkers,listOfDate);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(ba);
            writer.close();

          JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            ba = reader.read();
            assertEquals(0,listOfServices.size());
            assertEquals(0,listOfWorkers.size());
            assertEquals(0,listOfDate.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (DateNotAvailable e) {
            System.out.println("Date not avaible.");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            BookAppointments ba = new BookAppointments(listOfServices,listOfWorkers,listOfDate);
            ba.getServiceList().add(lawnMowing);
            ba.getWorkersList().add(worker1);
            ba.getDatesList().add(aug1);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(ba);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            ba = reader.read();
            assertEquals(1,listOfServices.size());
            assertEquals(1,listOfWorkers.size());
            assertEquals(1,listOfDate.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (DateNotAvailable e) {
            System.out.println("Date not available");
        }
    }
}
