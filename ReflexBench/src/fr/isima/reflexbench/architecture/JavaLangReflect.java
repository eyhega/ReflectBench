/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.isima.reflexbench.architecture;

import fr.isima.reflexbench.samples.SampleSourceCode;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gaetan
 */
public class JavaLangReflect extends ReflectAPI {
    
    public JavaLangReflect() {
        name = "java.lang.reflect";
    }

    @Override
    public void listMethods(Object obj) {
        final Method[] methods = obj.getClass().getDeclaredMethods();
    }

    @Override
    public void listFields(Object obj) {
        final Field[] fields = obj.getClass().getDeclaredFields();
    }

    @Override
    public void searchForFields(Object obj) {
        String fieldToSearch = ((SampleSourceCode)obj).getSearchingField();
        try {
            obj.getClass().getField(fieldToSearch);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(JavaLangReflect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(JavaLangReflect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void searchForMethods(Object obj) {
        String methodToSearch = ((SampleSourceCode)obj).getSearchingMethod();
        try {
            obj.getClass().getMethod(methodToSearch, null);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(JavaLangReflect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(JavaLangReflect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void invokeMethod(Object obj) {
        String methodToInvoke = ((SampleSourceCode)obj).getSearchingMethod();
        try {
            obj.getClass().getMethod(methodToInvoke, null).invoke(obj, null);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(JavaLangReflect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(JavaLangReflect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JavaLangReflect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(JavaLangReflect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(JavaLangReflect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
