package com.example.myfirst;

public class User {
    public String Fname, Lname, Email, Mobile, Password, conf_Password;

    public User() {

    }

    public User(String Fname, String Lname, String Email, String Mobile, String Password, String conf_Password) {
        this.Fname = Fname;
        this.Lname = Lname;
        this.Email = Email;
        this.Mobile = Mobile;
        this.Password = Password;
        this.conf_Password = conf_Password;
    }
}
