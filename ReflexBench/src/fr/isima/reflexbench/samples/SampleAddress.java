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
public class SampleAddress extends SampleSourceCode {
    
    private String streetName;
    private String houseNumber;
    private String zipCode;
    private String cityName;

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCityName() {
        return cityName;
    }
    
    
    
    public SampleAddress() {
        searchingField = "cityName";
        searchingMethod= "invalidateAddress";
    }

    public SampleAddress(String streetName, String houseNumber, String zipCode, String cityName) {
        this();
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.cityName = cityName;
    }
    
    
    
    
    public void invalidateAddress() {
        System.out.println("Someone request to change the currentAddress");
    }
    
}
