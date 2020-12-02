package com.example.myfirst;

public class Post {

    public static String Description, Urgency_level, Select_NGO, Post_Type, Animal_Category, Address, selectedUri;

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

    public static String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public static String getUrgency_level() {
        return Urgency_level;
    }

    public void setUrgency_level(String urgency_level) {
        Urgency_level = urgency_level;
    }

    public static String getSelect_NGO() {
        return Select_NGO;
    }

    public void setSelect_NGO(String select_NGO) {
        Select_NGO = select_NGO;
    }

    public static String getPost_Type() {
        return Post_Type;
    }

    public void setPost_Type(String post_Type) {
        Post_Type = post_Type;
    }

    public static String getAnimal_Category() {
        return Animal_Category;
    }

    public void setAnimal_Category(String animal_Category) {
        Animal_Category = animal_Category;
    }

    public static String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public static String getSelectedUri() {
        return selectedUri;
    }

    public void setSelectedUri(String selectedUri) {
        this.selectedUri = selectedUri;
    }
}
