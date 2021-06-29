package com.yanyv.workstation.view;

import lombok.Data;
import org.json.JSONObject;

@Data
public class Line implements View {
    private double startX;
    private double startY;
    private double endX;
    private double endY;

    public Line(double startX, double startY, double endX, double endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("type", "Line");
        object.put("sx", startX);
        object.put("sy", startY);
        object.put("ex", endX);
        object.put("ey", endY);
        return object;
    }
}
