package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a service having a service description and a list of all services
public class Services implements Writable {

    private String service;

    //EFFECTS: creates a service with its given description
    public Services(String serviceDescription) {
        service = serviceDescription;
    }

    public String getServiceDescription() {

        return service;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("serviceDescription", service);
        return json;
    }
}
