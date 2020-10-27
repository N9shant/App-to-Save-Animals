package com.example.myfirst;

public class NGO {
    public String NGO_Name, DateField, Reg_Email, Reg_Password, Confirm_Password;

    public NGO() {

    }

    public NGO(String NGO_Name, String DateField, String Reg_Email, String Reg_Password, String Confirm_Password) {
        this.NGO_Name = NGO_Name;
        this.DateField = DateField;
        this.Reg_Email = Reg_Email;
        this.Reg_Password = Reg_Password;
        this.Confirm_Password = Confirm_Password;
    }
}
