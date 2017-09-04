/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.model;

/**
 *
 * @author diary
 */
public class BaseModel {
    private long id;
    private String reference; 

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    
    public BaseModel() {
    }

    
    public BaseModel(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getAllReference(){
        return this.getReference()+this.getId();
    }
    
}
