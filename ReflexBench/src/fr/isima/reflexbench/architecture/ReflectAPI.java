/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.isima.reflexbench.architecture;

/**
 *
 * @author gaetan
 */
public abstract class ReflectAPI {
    
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    public void process(ReflectRequestType type, Object obj) {
        
        switch(type)
        {
            case LIST_ALL_METHODS   : listMethods(obj);        break;
            case LIST_ALL_FIELDS    : listFields(obj);         break;
            case SEARCH_FOR_FIELD   : searchForFields(obj);    break;
            case SEARCH_FOR_METHOD  : searchForMethods(obj);   break;
            case INVOKE_METHOD      : invokeMethod(obj);       break;
        }
        
    }
    
    public abstract void listMethods(Object obj);
    public abstract void listFields(Object obj);
    public abstract void searchForFields(Object obj);
    public abstract void searchForMethods(Object obj);
    public abstract void invokeMethod(Object obj);
}
