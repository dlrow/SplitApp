package com.app.split.entities;

import java.util.Objects;

public class User {

    String userId;
    String name;
    String email;
    String contact;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(contact, user.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, email, contact);
    }

    public User(String userId, String name, String email, String contact) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    //todo : validate for email
    public void setEmail(String email) {
        this.email = email;
    }

    //todo : validate  contact
    public void setContact(String contact) {
        this.contact = contact;
    }
}
