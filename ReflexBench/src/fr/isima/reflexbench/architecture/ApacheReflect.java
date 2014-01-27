/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.isima.reflexbench.architecture;

import fr.isima.reflexbench.samples.SampleSourceCode;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;

/**
 *
 * @author gaetan
 */
public class ApacheReflect extends ReflectAPI {
    
    public ApacheReflect() {
        name = "apache.reflect";
    }

    @Override
    public void listMethods(Object obj) {
        //doesn't exist into apache reflect engine
        //same as java.lang
        final Method[] methods = obj.getClass().getDeclaredMethods();
    }

    @Override
    public void listFields(Object obj) {
        PropertyUtils.getPropertyDescriptors(obj);
    }

    @Override
    public void searchForFields(Object obj) {
        String fieldToSearch = ((SampleSourceCode)obj).getSearchingField();
        try {
            PropertyUtils.getSimpleProperty(obj, fieldToSearch);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ApacheReflect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ApacheReflect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ApacheReflect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void searchForMethods(Object obj) {
        String methodToSearch = ((SampleSourceCode)obj).getSearchingMethod();
        Class<?> param = null;
        MethodUtils.getAccessibleMethod(obj.getClass(), methodToSearch, param);
    }

    @Override
    public void invokeMethod(Object obj) {
        String methodToSearch = ((SampleSourceCode)obj).getSearchingMethod();
        try {
            MethodUtils.invokeExactMethod(obj, methodToSearch, null);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ApacheReflect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ApacheReflect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ApacheReflect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
