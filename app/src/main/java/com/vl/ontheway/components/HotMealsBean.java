package com.vl.ontheway.components;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skathi on 4/22/2016.
 */
public class HotMealsBean {
    String Location;
    String Outlet;
    List<Integer> list = new ArrayList<Integer>();

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getOutlet() {
        return Outlet;
    }

    public void setOutlet(String outlet) {
        Outlet = outlet;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
