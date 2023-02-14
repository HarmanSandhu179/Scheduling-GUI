package persistence;



import model.Dates;
import model.Services;
import model.Workers;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Based on JsonSerializationDemo; link below
// <https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git>


public class JsonTest {
    protected void checkServices(String name, Services services) {
        assertEquals(name, services.getServiceDescription());
    }

    protected void checkWorkers(String name, Workers worker) {
        assertEquals(name, worker.getName());
    }

    protected void checkDate(String month, int day, Dates date) {
        assertEquals(month, date.getMonth());
        assertEquals(day, date.getDay());
    }

}
