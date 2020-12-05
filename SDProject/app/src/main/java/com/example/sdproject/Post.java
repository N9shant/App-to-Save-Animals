package com.example.sdproject;

public class Post {

    public static String Description, Urgency_level, Select_NGO, Post_Type, Animal_Category, Address;

    public Post() {

    }

    public Post(String Description, String Urgency_level, String Select_NGO, String Post_Type, String Animal_Category, String Address) {
        this.Description = Description;
        this.Urgency_level = Urgency_level;
        this.Select_NGO = Select_NGO;
        this.Post_Type =Post_Type;
        this.Animal_Category =Animal_Category;
        this.Address = Address;
    }

    public static String getDescription() {
        return Description;
    }

    public static void setDescription(String description) {
        Description = description;
    }

    public static String getUrgency_level() {
        return Urgency_level;
    }

    public static void setUrgency_level(String urgency_level) {
        Urgency_level = urgency_level;
    }

    public static String getSelect_NGO() {
        return Select_NGO;
    }

    public static void setSelect_NGO(String select_NGO) {
        Select_NGO = select_NGO;
    }

    public static String getPost_Type() {
        return Post_Type;
    }

    public static void setPost_Type(String post_Type) {
        Post_Type = post_Type;
    }

    public static String getAnimal_Category() {
        return Animal_Category;
    }

    public static void setAnimal_Category(String animal_Category) {
        Animal_Category = animal_Category;
    }

    public static String getAddress() {
        return Address;
    }

    public static void setAddress(String address) {
        Address = address;
    }
}
