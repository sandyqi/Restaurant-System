/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classPackage;



/**
 *
 * @author Meng
 */
public class Customer {

    private int customerId;
    private static int ID = 1;
    private String cName;
    private String cPhone;


    public Customer(String name, String phone) {
        cName = name;
        cPhone = phone;
        customerId = ID;
        ID++;
    }
 
    public int getId() {
        return customerId;
    }

    public String getName() {
        return cName;
    }

    public String getPhone() {
        return cPhone;
    }

    public void setName(String name) {
        cName = name;
    }

    public void setPhone(String phone) {
        cPhone = phone;
    }



}
