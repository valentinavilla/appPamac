package com.example.application.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.vaadin.flow.router.RouteParameters;

@Entity
public class Contact extends AbstractEntity {

    @NotEmpty
    private String firstName = "";

    @NotEmpty
    private String lastName = "";

    @NotNull
    @ManyToOne
    private Status status;

    @Email
    @NotEmpty
    private String email = "";

    public enum genere{
        M,
        F;
    }

    private genere sesso=genere.M;

    public void setGenere(genere s){
        this.sesso=s;
    }

    @Override
    public String toString() {
        String s;
        if(sesso==genere.M){s="M";}
        else{s="F";}
        return firstName + "," + lastName+","+status+","+email+","+ s;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        if(sesso==genere.M){return "images/tipo.jpg";}
        if (sesso==genere.F) {return "images/tipa.jpg";}
        return "images/pic.jpg";

    }

 }

