package com.example.builder;

import com.example.entitymapping.EntityMappingApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EntityMappingApplication.class)
public class BuilderTest {

    @Test
    public void testBuilder(){
        Details details = new DetailsBuilder()
                .addName("Mar", "Tys")
                .addLocation("Balanga", "City of Dreams", "Providence")
                .addBirth("December", "15", "1991")
                .build();

        Assert.assertEquals("Mar", details.getFirstName());
    }
}

class DetailsBuilder {
    private Details details;

    public DetailsBuilder(){
        details = new Details();
    }

    public Details build(){
        return details;
    }

    public DetailsBuilder addName(String firstName, String lastName){
        details.setFirstName(firstName);
        details.setLastName(lastName);
        return this;
    }

    public DetailsBuilder addLocation(String barangay, String city, String province){
        details.setBarangay(barangay);
        details.setCity(city);
        details.setProvince(province);
        return this;
    }

    public DetailsBuilder addBirth(String month, String date, String year){
        details.setMonth(month);
        details.setDate(date);
        details.setYear(year);
        return this;
    }
}


class Details {

    private String firstName;
    private String lastName;
    private String barangay;
    private String city;
    private String province;
    private String month;
    private String date;
    private String year;

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

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
