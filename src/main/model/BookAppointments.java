package model;

import exceptions.DateNotAvailable;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents the list of all services, workers and dates booked
public class BookAppointments implements Writable {

    private ArrayList<Services> listOfServices;
    private ArrayList<Workers> listOfWorkers;
    private ArrayList<Dates> listOfDate;
    private Workers lastAddedWorker;

    // gets the list of services and workers from there respective classes
    public BookAppointments(ArrayList<Services> listOfServices, ArrayList<Workers> listOfWorkers,
                            ArrayList<Dates> listOfDate) {
        this.listOfServices = listOfServices;
        this.listOfWorkers = listOfWorkers;
        this.listOfDate = listOfDate;

    }

    //MODIFIES: this
    //EFFECTS: books the given service in the list of services
    public void bookService(Services service) {
        listOfServices.add(service);
        EventLog.getInstance().logEvent(new Event("Booked a service!"));
    }

    //MODIFIES: this
    //EFFECTS: books the given worker in the list of workers
    public void bookWorker(Workers worker) {
        listOfWorkers.add(worker);
        EventLog.getInstance().logEvent(new Event("Booked a worker!"));
    }

    //MODIFIES: this
    //EFFECTS: books the given date in the list of dates
    public void bookDate(Dates date) throws DateNotAvailable {
        if (!checkIfDateOpen().getScheduledDates().contains(date)) {
            listOfDate.add(date);
            checkIfDateOpen().getScheduledDates().add(date);
            EventLog.getInstance().logEvent(new Event("Booked a date!"));
        } else {
            throw new DateNotAvailable();
        }
    }

    //returns the last worker who was added to list of workers
    public Workers checkIfDateOpen() {
        lastAddedWorker = listOfWorkers.get(listOfWorkers.size() - 1);
        return lastAddedWorker;
    }

    public void removeAppointment() {
        listOfServices.remove(listOfServices.size() - 1);
        listOfWorkers.remove(listOfWorkers.size() - 1);
        listOfDate.remove(listOfDate.size() - 1);
        EventLog.getInstance().logEvent(new Event("Removed last added appointment!"));
    }




    public ArrayList<Services> getServiceList() {
        return listOfServices;
    }

    public ArrayList<Workers> getWorkersList() {
        return listOfWorkers;
    }

    public ArrayList<Dates> getDatesList() {
        return listOfDate;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("services", servicesToJson());
        json.put("workers", workersToJson());
        json.put("dates", datesToJson());
        return json;
    }


    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray servicesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Services t : listOfServices) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray workersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Workers t : listOfWorkers) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray datesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Dates t : listOfDate) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}
