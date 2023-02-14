package persistence;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import exceptions.DateNotAvailable;
import model.BookAppointments;
import model.Dates;
import model.Services;
import model.Workers;
import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Book Appointments from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BookAppointments read() throws IOException, DateNotAvailable {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookAppointments(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Book Appointments from JSON object and returns it
    private BookAppointments parseBookAppointments(JSONObject jsonObject) throws DateNotAvailable {
        ArrayList<Services> services = new ArrayList<>();
        ArrayList<Workers> workers = new ArrayList<>();
        ArrayList<Dates> dates = new ArrayList<>();

        BookAppointments appointments = new BookAppointments(services, workers, dates);
        addServices(appointments, jsonObject);
        addWorkers(appointments, jsonObject);
        addDates(appointments, jsonObject);
        return appointments;
    }

    // MODIFIES: ba
    // EFFECTS: parses dates from JSON object and adds them to book appointments
    private void addDates(BookAppointments ba, JSONObject jsonObject) throws DateNotAvailable {
        JSONArray jsonArray = jsonObject.getJSONArray("dates");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addDate(ba, nextThingy);
        }
    }

    // MODIFIES: ba
    // EFFECTS: parses date from JSON object and adds it to book appointments
    private void addDate(BookAppointments ba, JSONObject jsonObject) throws DateNotAvailable {
        String name = jsonObject.getString("month");
        int day = jsonObject.getInt("day");
        Dates date = new Dates(name, day);
        ba.bookDate(date);
    }

    // MODIFIES: ba
    // EFFECTS: parses workers from JSON object and adds them to book appointments
    private void addWorkers(BookAppointments ba, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("workers");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addWorker(ba, nextThingy);
        }
    }

    // MODIFIES: ba
    // EFFECTS: parses worker from JSON object and adds it to book appointments
    private void addWorker(BookAppointments ba, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Workers worker = new Workers(name);
        ba.bookWorker(worker);
    }


    // MODIFIES: ba
    // EFFECTS: parses services from JSON object and adds them to book appointments
    private void addServices(BookAppointments ba, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("services");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addService(ba, nextThingy);
        }
    }

    // MODIFIES: ba
    // EFFECTS: parses service from JSON object and adds it to book appointments
    private void addService(BookAppointments ba, JSONObject jsonObject) {
        String name = jsonObject.getString("serviceDescription");
        Services service = new Services(name);
        ba.bookService(service);
    }
}

