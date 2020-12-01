package com.example.myfirst;

public class Post {

    public String Description, Urgency_level, Select_NGO, Post_Type, Animal_Category, Address, selectedUri;

    public Post() {

    }

    public Post(String Description, String Urgency_level, String Select_NGO, String Post_Type, String Animal_Category, String Address, String selectedUri) {
        this.Description = Description;
        this.Urgency_level = Urgency_level;
        this.Select_NGO = Select_NGO;
        this.Post_Type = Post_Type;
        this.Animal_Category = Animal_Category;
        this.Address = Address;
        this.selectedUri = selectedUri;
    }
}
