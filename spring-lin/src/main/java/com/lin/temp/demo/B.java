package com.lin.temp.demo;

public class B extends A {
    {
        System.out.print("E");
    }
 
    static {
        System.out.print("F");
    }
 
    B() {
        System.out.print("H");
    }
 
    B(int i) {
        System.out.print("G");
    }
 
 
}
