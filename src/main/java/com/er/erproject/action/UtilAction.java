/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.er.erproject.action;

import com.er.erproject.data.SessionReference;
import com.opensymphony.xwork2.Action;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author diary
 */
public class UtilAction {
    public String logout()throws Exception{
        HttpSession session = ServletActionContext.getRequest().getSession();
        Object object = session.getAttribute(SessionReference.USER);
        if (object == null) {
            return Action.SUCCESS;
        }
        session.removeAttribute(SessionReference.USER);
        return Action.SUCCESS;
    }
}
