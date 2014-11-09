/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classPackage;

import guiPackage.LoginGUI;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;


/**
 *
 * @author Meng
 */
// I have to add compareto method in this class to compare customer, ingredients and so on by creating different
//Comparator<T> to sort things. 

/*

 */
public class RestaurantSystem implements Serializable{

    private static List<Customer> customerList;
    private static List<FoodItem> foodItemList;
    private static List<Order> orderList;
    private static List<Ingredient> ingredientList;
    private static List<InventoryRecord> inventoryRecordList;
   private static List<DrinkSnack> drinkSnackList ;
   private static List<Dish> dishList;
   private static Customer currentUser;
   private static Order currentOrder;

    public RestaurantSystem() {
        customerList = new LinkedList<>();
        foodItemList = new LinkedList<>();
        orderList = new LinkedList<>();
        ingredientList = new LinkedList<>();
        inventoryRecordList = new LinkedList<>();
        drinkSnackList = new LinkedList<>();
        dishList = new LinkedList<>();
        currentUser = null;
        currentOrder = null;
        getCustomerListFromFile();
        getIngredientListFromFile();
        getDishListFromFile();
        getDrinkSnackListFromFile();
        getOrderListFromFile();
    }
public static Customer getCurrentUser(){
    return currentUser;
}
public static Order getCurrentOrder(){
    return currentOrder;
}

    public static List<Customer> getCustomerList() {
     if(customerList.size()==0){
        getCustomerListFromFile();
        return customerList;
        }
        else{
            return customerList;
        }
    }

    public static List<FoodItem> getFoodItemList() {
        return foodItemList;
    }

    public static List<Order> getOrderList() {
         if(orderList.size()==0){
        getOrderListFromFile();
        return orderList;
        }
        else{
            return orderList;
        }
    }
    public static List<Ingredient>  getIngredientList(){
       if(ingredientList.size()==0){
        getIngredientListFromFile();
        return ingredientList;
        }
        else{
            return ingredientList;
        }
    }

    public static List<InventoryRecord> getInventoryRecordList() {
        return inventoryRecordList;
    }

    public static List<Dish> getDishList() {
        if(dishList.size()==0){
        getDishListFromFile();
        return dishList;
        }
        else{
            return dishList;
        }
    }

    public static List<DrinkSnack> getDrinkSnackList() {
        if(drinkSnackList.size()==0){
        getDrinkSnackListFromFile();
        return drinkSnackList;
        }
        else{
        return drinkSnackList;
        }
    }
    
        public static void setCurrentUser(Customer c){
        currentUser = c;
    }
        public static void setCurrentOrder(Order o){
            currentOrder = o;
        }

    public static void setCustomerList(List<Customer> l){
        customerList = l;
    }
    public static void setFoodItemList(List<FoodItem> l){
        foodItemList = l;
    }
    public static void setOrderList( List<Order>  l){
        orderList = l;
    }
    public static void setIngredientList(List<Ingredient> l){
        ingredientList = l ;
    }
    public static void setInventoryRecordList(List<InventoryRecord> l){
        inventoryRecordList = l;
    }
    public static void addCustomerToList(Customer c) {
        customerList.add(c);
        writeCustomerListToFile();
        System.out.println("customer is added into customerList");
    }
    public static void addDishToList(Dish dish){
        dishList.add(dish);
        writeDishListToFile();
        addFoodItemToList(dish);
        System.out.println("dish is added into dishList");
    }
    public static void addSnackDrinkToList(DrinkSnack ds){
        drinkSnackList.add(ds);
        writeDrinkSnackListToFile();
        System.out.println("snack or drink is added into dishList");
        addFoodItemToList(ds);
    }
    public static void addFoodItemToList(FoodItem foodItem) {
        foodItemList.add(foodItem);
         System.out.println("foodItem is added into customerList");
    }

    public static void addOrderToList(Order order) {
        orderList.add(order);
        writeOrderListToFile();
         System.out.println("order is added into customerList");

    }

    public static void addIngredientToList(Ingredient in) {
        ingredientList.add(in);
        writeIngredientListToFile();
         System.out.println("ingredient is added into customerList");
    }

    public static void addInventoryRecordToList(InventoryRecord ir) {
        inventoryRecordList.add(ir);
         System.out.println("inventoryRecord is added into customerList");
    }

   

    public static Customer searchCustomer(int id) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId() == id) {
                return customerList.get(i);
            }
        }
        return null;
    }
    public static void writeCustomerListToFile(){
        PrintWriter pw=null;
        try{
            pw = new PrintWriter("customerList.txt");
            for(Customer c: getCustomerList()){
                pw.println(c.getName()+","+c.getPhone());
            }
            pw.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(pw!=null){
                pw.close();
            }
        }
    }
    public static void getCustomerListFromFile(){
        Scanner scan = null;
        try{
            File file = new File("customerList.txt");
            if(file.exists()){
                System.out.println("customerList.txt exists");
            }else{
                file.createNewFile();
            }
            scan = new Scanner(file);
            while(scan.hasNext()){
                String line = scan.nextLine();
                String[] itemInfo = line.split(",");
                customerList.add(new Customer(itemInfo[0],itemInfo[1]));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
     public static void writeOrderListToFile(){
        PrintWriter pw=null;
        try{
            pw = new PrintWriter("orderList.txt");
            for(Order c: getOrderList()){
                pw.println(c.getCustomer().getName()+","+c.getDate()+","+c.getStatus()); // put customer name, date, status and fooditemlist into file
                for(int i= 0;i<c.getFoodItemList().size();i++){
                pw.print(c.getFoodItemList().get(i).getName()+",");//write foodItemList to file
            }
                pw.println();
            }
            pw.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(pw!=null){
                pw.close();
            }
        }
    }
    public static void getOrderListFromFile(){
        Scanner scan = null;
        try{
            File file = new File("orderList.txt");
            if(file.exists()){
                System.out.println("orderList.txt exists");
            }else{
                file.createNewFile();
            }
            scan = new Scanner(file);
            Customer c=null;
           
            
            while(scan.hasNext()){
                 List<FoodItem> foodItemListInOrder = new LinkedList<>();
                String line1 = scan.nextLine();
                String line2 = scan.nextLine();
                String[] itemInfo = line1.split(",");  // includes customername, date and status
                String[] foodNames = line2.split(",");
                System.out.println("foodName size: "+foodNames.length);
                for(int i  =0;i<foodNames.length;i++){ // initialize foodItemList in order
                    System.out.println("foodname: "+foodNames[i]);
                    for(int j=0;j<dishList.size();j++){
                        System.out.println("dishlist size: "+dishList.size() + dishList.get(j).getName());
                        
                        
                        if(dishList.get(j).getName().equals(foodNames[i])){
                            foodItemListInOrder.add(dishList.get(j));
                            System.out.println("addddd");
                        }
                 
                    }
                    for(int t=0;t<drinkSnackList.size();t++){
                        if(drinkSnackList.get(t).getName().equals(foodNames[i])){
                            foodItemListInOrder.add(drinkSnackList.get(t));
                        }
                    }
                }
                // find the customer
                for(int i =0; i<customerList.size();i++){
                    if(customerList.get(i).getName().equals(itemInfo[0])){
                        c = customerList.get(i);
                    }
                }
                Order order = new Order(c);
                order.setDate(itemInfo[1]);
                order.setStatus(Boolean.parseBoolean(itemInfo[2]));
                order.setFoodItemList(foodItemListInOrder);
                System.out.println("foodItem size for an order: "+foodItemListInOrder.size());
                
           //     foodItemListInOrder.remove(foodItemListInOrder.size()-1);
                orderList.add(order);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
     public static void writeIngredientListToFile(){
        PrintWriter pw=null;
        try{
            pw = new PrintWriter("ingredientList.txt");
            for(Ingredient c: getIngredientList()){
                pw.println(c.getName()+","+c.getCalorie());
            }
            pw.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(pw!=null){
                pw.close();
            }
        }
    }
    public static void getIngredientListFromFile(){
        Scanner scan = null;
        try{
            File file = new File("ingredientList.txt");
            if(file.exists()){
                System.out.println("ingredientList.txt exists");
            }else{
                file.createNewFile();
            }
            scan = new Scanner(file);
            while(scan.hasNext()){
                String line = scan.nextLine();
                String[] itemInfo = line.split(",");
                ingredientList.add(new Ingredient(itemInfo[0],Integer.parseInt(itemInfo[1])));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
     public static void writeDishListToFile(){
        PrintWriter pw=null;
        try{
            pw = new PrintWriter("dishList.txt");
            for(Dish c: getDishList()){
                pw.println(c.getName()+","+c.getPrice()+","+c.getDescription()+","+c.getFlavor());
                for(int i=0;i<c.getIngredientNames().length;i++){
                    pw.print(c.getIngredientNames()[i]+",");
                }
                pw.println();
                for(int i=0;i<c.getIngredientWeights().length;i++){
                    pw.print(c.getIngredientWeights()[i]+",");
                }
                pw.println();
            }
            pw.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(pw!=null){
                pw.close();
            }
        }
    }
    public static void getDishListFromFile(){
        Scanner scan = null;
        try{
            File file = new File("dishList.txt");
            if(file.exists()){
                System.out.println("dishList.txt exists");
            }else{
                file.createNewFile();
            }
            scan = new Scanner(file);
 
          
            while(scan.hasNext()){
            List<Integer> weights = new LinkedList<>();
            List<Ingredient> ingredients = new LinkedList<>();
                String line1 = scan.nextLine();
                String line2 = scan.nextLine();
                String line3 = scan.nextLine();
                String[] itemInfo = line1.split(",");
                String[] ingredientNames = line2.split(",");
                String[] ingredientWeights = line3.split(",");
                for(int i=0;i<ingredientWeights.length;i++){
                    weights.add(Integer.parseInt(ingredientWeights[i]));
                }
                for(int i=0;i<ingredientNames.length;i++){
                    for(int j=0;j<ingredientList.size();j++){
                        if(ingredientNames[i].equals(ingredientList.get(j).getName())){
                            ingredients.add(ingredientList.get(j));
                        }
                     
                    }
                }
  
               dishList.add(new Dish(itemInfo[0],Double.parseDouble(itemInfo[1]),itemInfo[2],ingredients,weights,Integer.parseInt(itemInfo[3])));
                System.out.println(dishList.size());
              
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
     public static void writeDrinkSnackListToFile(){
        PrintWriter pw=null;
        try{
            pw = new PrintWriter("drinkSnackList.txt");
            for(DrinkSnack c: getDrinkSnackList()){
                pw.println(c.getName()+","+c.getPrice()+","+c.getDescription()+","+c.getSize());
                for(int i=0;i<c.getIngredientNames().length;i++){
                    pw.print(c.getIngredientNames()[i]+",");
                }
                pw.println();
                for(int i=0;i<c.getIngredientWeights().length;i++){
                    pw.print(c.getIngredientWeights()[i]+",");
                }
                pw.println();
            }
            pw.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(pw!=null){
                pw.close();
            }
        }
    }
    public static void getDrinkSnackListFromFile(){
        Scanner scan = null;
        try{
            File file = new File("drinkSnackList.txt");
            if(file.exists()){
                System.out.println("drinkSnackList.txt exists");
            }else{
                file.createNewFile();
            }
            scan = new Scanner(file);

          
            while(scan.hasNext()){
                List<Integer> weights = new LinkedList<>();
                List<Ingredient> ingredients = new LinkedList<>();
                String line1 = scan.nextLine();
                String line2 = scan.nextLine();
                String line3 = scan.nextLine();
                String[] itemInfo = line1.split(",");
                String[] ingredientNames = line2.split(",");
                String[] ingredientWeights = line3.split(",");
                for(int i=0;i<ingredientWeights.length;i++){
                    weights.add(Integer.parseInt(ingredientWeights[i]));
                }
                for(int i=0;i<ingredientNames.length;i++){
                    for(int j=0;j<ingredientList.size();j++){
                        if(ingredientNames[i].equals(ingredientList.get(j).getName())){
                            ingredients.add(ingredientList.get(j));
                        }
                        
                    }
                }
                
                drinkSnackList.add(new DrinkSnack(itemInfo[0],Double.parseDouble(itemInfo[1]),itemInfo[2],ingredients,weights,Integer.parseInt(itemInfo[3])));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
  static  Comparator<Customer> byTotalExpense = new Comparator<Customer>() {
        @Override
        public int compare(Customer t, Customer t1) {
            int cost1 = 0, cost2 = 0;
            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).getCustomer() == t) {
                    cost1 += orderList.get(i).getTotalPrice();
                } else if (orderList.get(i).getCustomer() == t1) {
                    cost2 += orderList.get(i).getTotalCalories();
                }
            }
            if (cost1 > cost2) {
                return 1;
            } else if (cost1 == cost2) {
                return 0;
            } else {
                return -1;
            }
        }
    };

  static  Comparator<Customer> byOrderTimes = new Comparator<Customer>() {
        @Override
        public int compare(Customer t, Customer t1) {
            int order1 = 0, order2 = 0;
            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).getCustomer() == t) {
                    order1 += 1;
                } else if (orderList.get(i).getCustomer() == t1) {
                    order2 += 1;
                }
            }
            if (order1 > order2) {
                return 1;
            } else if (order1 == order2) {
                return 0;
            } else {
                return -1;
            }
        }
    };
  static  Comparator<FoodItem> byFoodOrderTimes = new Comparator<FoodItem>() {

        @Override
        public int compare(FoodItem t, FoodItem t1) {
            int order1 = 0, order2 = 0;
 
            for (int i = 0; i < orderList.size(); i++) {
                for (int j = 0; j < orderList.get(i).getFoodItemList().size(); j++) {
                    if (orderList.get(i).getFoodItemList().get(j).getName().equals( t.getName())) {
 
                        order1++;
                    } else if (orderList.get(i).getFoodItemList().get(j).getName().equals( t1.getName())) {
                        order2++;
                    }
                }
            }
            if (order1 > order2) {
                return -1;
            } else if (order1 == order2) {
                return 0;
            } else {
                return 1;
            }
        }

    };
    
   static Comparator<FoodItem> byPrice = new Comparator<FoodItem>(){

        @Override
        public int compare(FoodItem t, FoodItem t1) {
            if(t.getPrice()>t1.getPrice()){
                return 1;
            }
            else if(t.getPrice() == t1.getPrice()){
                return 0;
            }else{
                return -1;
            }
        }
        
    };
   static Comparator<FoodItem> byCalorie = new Comparator<FoodItem>(){

        @Override
        public int compare(FoodItem t, FoodItem t1) {
            if(t.getCalories()>t1.getCalories()){
                return 1;
            }
            else if(t.getCalories()== t1.getCalories()){
                return 0;
            }else{
                return -1;
            }
        }
     
   };
    public static List<Customer> sortByTotalExpense() {
        Collections.sort(customerList, byTotalExpense);
        return customerList;
    }

    public static List<Customer> sortByOrderTimes() {
        Collections.sort(customerList, byOrderTimes);
        return customerList;
    }

    public static List<FoodItem> sortByFoodOrderTimes() {
             Collections.sort(drinkSnackList,byFoodOrderTimes);
        Collections.sort(dishList,byFoodOrderTimes);
        return foodItemList;
    }
    public static List<FoodItem> sortByPrice(){
        Collections.sort(drinkSnackList,byPrice);
        Collections.sort(dishList,byPrice);
        return foodItemList;
    }
    public static List<FoodItem> sortByCalorie(){
        Collections.sort(drinkSnackList,byCalorie);
        Collections.sort(dishList,byCalorie);
        return foodItemList;
    }
    public static void main(String args[]) {

        RestaurantSystem rs = new RestaurantSystem();
        LoginGUI login = new LoginGUI();
        login.setVisible(true);
        
    }
    
}
