package com.example.bustrackerv1;

public class Drivers {
    public String FullName;
    public String Email;
    public String Phone;
    public String Password;
    public String BusNumber;

    public Drivers(){

    }

    public Drivers(String fullName, String email, String phone, String password, String busNumber) {
        FullName = fullName;
        Email = email;
        Phone = phone;
        Password = password;
        BusNumber = busNumber;
    }
}
