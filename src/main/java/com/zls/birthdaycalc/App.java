/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zls.birthdaycalc;

import static java.lang.Long.signum;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 *
 * @author zshug
 */
public class App {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate currentDate = LocalDate.now();
        String formatedCurrentDate = currentDate.format(formatter);
        int currentYear = currentDate.getYear();
        Scanner sc = new Scanner(System.in);
        
        String SHORTDELIMITER = "/";
        
        System.out.println("Welcome to your BirthDay Calculator!");
        boolean keepGoing = true;
        while(keepGoing){
                System.out.println("Please enter your birthday. eg 10/08/2000");
                String userInput = sc.nextLine();
                try {
                    LocalDate userBday = LocalDate.parse(userInput,formatter);
                    String formattedBday = userBday.format(formatter);
                    
                    
                    
                    String[] shortBdayTokens = formattedBday.split(SHORTDELIMITER);
                    String month = shortBdayTokens[0];
                    String day =shortBdayTokens[1];
                    String currentBday = month+"/"+day+"/"+currentYear;
                    LocalDate userCurrentBday = LocalDate.parse(currentBday,formatter);
                    
                    
                    long diff = currentDate.until(userCurrentBday, ChronoUnit.DAYS);
                    long age = userBday.until(userCurrentBday,ChronoUnit.YEARS);
                    if(Long.signum(diff) == -1){
                      String nextBdayString = month+"/"+day+"/"+(currentYear+1); 
                      LocalDate nextBday = LocalDate.parse(nextBdayString,formatter);
                      diff = currentDate.until(nextBday,ChronoUnit.DAYS);
                      age = userBday.until(nextBday,ChronoUnit.YEARS);
                    } 
                    
                    System.out.println(formattedBday);
                    System.out.println("That means your were born on a "+ userBday.getDayOfWeek()+ "!");
                    System.out.println("This year it falls on a "+userCurrentBday.getDayOfWeek()+"!");
                    System.out.println("And since today is "+formatedCurrentDate+ ", there's only "+diff+" more days until the next one!");
                    System.out.println("You must be excited to be turning " + age +"!");
                    keepGoing = false;
                } catch(DateTimeParseException e){
                    System.out.println("Please input a valid date in format MM/dd/yyyy");   
                    
                }
            }
        

    }
}
