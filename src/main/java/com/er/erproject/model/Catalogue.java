/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.model;

import com.er.erproject.data.Reference;

/**
 *
 * @author diary
 */
public class Catalogue extends CatalogueModel{
    private boolean cacher;

    public boolean isCacher() {
        return cacher;
    }

    public void setCacher(boolean cacher) {
        this.cacher = cacher;
    }
    
    public Catalogue(){
        this.setReference(Reference.CATALOGUE);
    }
}
