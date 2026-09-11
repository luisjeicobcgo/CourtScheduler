package com.example.courtscheduler.quarter2;
import org.junit.Test;

public class gominipeta {
    @Test
    public void printMyProfile(){
        String myName = "Luis";
        String petName1 = "Polar";
        String petName2 = "Kalli";
        String favFood = "chicken";
        int myAge = 16;

        System.out.println("--- MY DIGITAL PROFILE ---");
        System.out.println("Hello, my name is " + myName + " and I am " + myAge + " years old.");
        System.out.println("I have 2 wonderful pets named " + petName1 + " and " + petName2 + ".");
        System.out.println("If I could, I would eat " + favFood + " every single day!");
    }
}
