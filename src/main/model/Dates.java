package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents dates that can be chosen for the services
public class Dates implements Writable {

    private String month;
    private int day;

    //EFFECTS: creates dates for the workers
    public Dates(String month, int day) {
        this.month = month;
        this.day = day;
    }

    //EFFECTS: gets the dates month
    public String getMonth() {
        return month;
    }

    //EFFECTS: gets the dates day
    public int getDay() {
        return day;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("month", month);
        json.put("day", day);
        return json;
    }

}

