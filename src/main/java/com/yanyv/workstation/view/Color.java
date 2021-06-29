package com.yanyv.workstation.view;

import org.json.JSONObject;

public class Color {
    private int r;
    private int g;
    private int b;

    public static Color rgb(int r, int g, int b) {
        Color color = new Color();
        color.r = r;
        color.g = g;
        color.b = b;
        return color;
    }

    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("r", r);
        object.put("g", g);
        object.put("b", b);
        return object;
    }
}
