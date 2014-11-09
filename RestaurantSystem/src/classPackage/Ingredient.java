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
public class Ingredient {
    private String name;
    private int calorie;
    private int id;
    private static int ID =1;
    public Ingredient(String name, int c){
        this.name=name;
        this.calorie=c;
        this.id=ID;
        ID++;
    }
   
    public int getCalorie(){
        return this.calorie;
    }
    public String getName(){
        return this.name;
    }
    public int getId(){
        return this.id;
    }
}
