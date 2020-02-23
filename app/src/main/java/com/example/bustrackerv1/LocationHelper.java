package com.example.bustrackerv1;

public class LocationHelper {
    private double Logitude;
    private double Latitude;

    public LocationHelper(double logitude, double latitude) {
        Logitude = logitude;
        Latitude = latitude;
    }

    public double getLogitude() {
        return Logitude;
    }

    public void setLogitude(double logitude) {
        Logitude = logitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }
}
