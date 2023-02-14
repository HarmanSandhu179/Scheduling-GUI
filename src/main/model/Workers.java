package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents the workers that can be chosen, with a name and list of scheduled dates.
public class Workers implements Writable {

    private String name;
    private ArrayList<Dates> scheduledDates;

    //EFFECTS: creates a worker with a given name and creates a list of jobs
    // that are assigned to them.
    public Workers(String name) {
        this.name = name;
        scheduledDates = new ArrayList<>();
    }

    //EFFECTS: gets the name of the worker
    public String getName() {
        return name;
    }

    public ArrayList<Dates> getScheduledDates() {
        return scheduledDates;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }
}

