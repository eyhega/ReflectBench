/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.isima.reflexbench.samples;

/**
 *
 * @author gaetan
 */
public class SampleEmployee extends SampleSourceCode {

    private final static int FIXED_SALARY = 2000;
    
    private double money;
    private String name;
    private String surname;
    private SampleAddress address;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public SampleAddress getAddress() {
        return address;
    }

    public void setAddress(SampleAddress address) {
        this.address = address;
    }
    
    
    
    public SampleEmployee() {
        searchingField  = "address";
        searchingMethod = "receiveSalary";
    }

    public SampleEmployee(String name, String surname, SampleAddress address) {
        this();
        this.name = name;
        this.surname = surname;
        this.address = address;
    }
    
    @Override
    public void fillObject() {
        name = "myName";
        surname = "mySurname";
        address = new SampleAddress();
        address.fillObject();
    }
    
    public void receiveSalary() {
        money+= FIXED_SALARY;
    }
    
    
    
}
