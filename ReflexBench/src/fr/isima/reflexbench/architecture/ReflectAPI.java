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
    
    
    public Long process(ReflectRequestType type, Object obj) {
        Long beginMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        switch(type)
        {
            case LIST_ALL_METHODS   : listMethods(obj);        break;
            case LIST_ALL_FIELDS    : listFields(obj);         break;
            case SEARCH_FOR_FIELD   : searchForFields(obj);    break;
            case SEARCH_FOR_METHOD  : searchForMethods(obj);   break;
            case INVOKE_METHOD      : invokeMethod(obj);       break;
        }
        Long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        return endMemory - beginMemory;
    }
    
    /**
     * List all the declared methods from obj class.
     * 
     * @param obj Object to process 
     */
    public abstract void listMethods(Object obj);
    
    /**
     * List all the declared fields from obj class.
     * 
     * @param obj Object to process.
     */
    public abstract void listFields(Object obj);
    
    /**
     * Search for a specific field in obj class, and get the value of this field.
     * <p>This field is specified in the sample class by the tag name <i>searchingField</i></p>
     * 
     * @param obj Object to process.
     */
    public abstract void searchForFields(Object obj);
    
    /**
     * Search for a specific method in obj class.
     * <p>This method name is specified in the sample class by the tag name <i>searchingMethod</i></p>
     * 
     * @param obj Object to process.
     */
    public abstract void searchForMethods(Object obj);
    
    /**
     * Search and invoke the specific method.
     * 
     * @param obj Object to process.
     */
    public abstract void invokeMethod(Object obj);
}
