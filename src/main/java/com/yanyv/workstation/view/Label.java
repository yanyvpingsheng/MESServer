package com.yanyv.workstation.view;

import lombok.Data;
import org.json.JSONObject;

@Data
public class Label implements View{
    private double layoutX;
    private double layoutY;
    private double width;
    private double height;
    private Color color = Color.rgb(255, 255, 255);
    private String text;

    public Label(String text) {
        this.text = text;
    }

    @Override
    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("type", "Label");
        object.put("x", layoutX);
        object.put("y", layoutY);
        object.put("w", width);
        object.put("h", height);
        object.put("c", color.toJson());
        object.put("t", text);
        return object;
    }
}
