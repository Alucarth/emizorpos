package com.ipxserver.davidtorrez.fvpos.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by David Torrez on 26/10/2015.
 */
public class Branches {
    private String name;
    private String id;

    public static Branches fromJson(String jsonText)
    {
        Branches branch = new Branches();
        try {
            JSONObject json = new JSONObject(jsonText);
            if(json.has("branch_id"))
            {
                branch.setId(json.getString("branch_id"));
            }
            if(json.has("name"))
            {
                branch.setName(json.getString("name"));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return branch;
    }
    public static ArrayList<Branches> fromArrayJson(String jsonArrayText)
    {
        ArrayList<Branches> branches = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(jsonArrayText);

            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject json = jsonArray.getJSONObject(i);
                Branches branch = Branches.fromJson(json.toString());
                branches.add(branch);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return branches;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
