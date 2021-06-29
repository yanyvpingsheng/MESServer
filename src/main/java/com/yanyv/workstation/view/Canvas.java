package com.yanyv.workstation.view;

import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Data
public class Canvas implements View{
    private double width;
    private double height;

    private List<View> children = new ArrayList<>();


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        json.put("width", width);
        json.put("height", height);
        for (View v : children) {
            array.put(v.toJson());
        }

        json.put("children", array);
        return json;
    }
}
