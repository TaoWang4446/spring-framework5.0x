package com.lin.temp.demo;

public class A {
	private int a ;
	private static int b1;
	private static int b=5;
	private final static int c = 10;
	public final static int d = 110;
    {
        System.out.print("A");
    }
 
    static {
        System.out.print("B");
    }
 
    A() {
        System.out.print("C");
    }
 
    A(int i) {
        System.out.print("D");
    }
 
    public static void main(String[] args) {
        B b = new B(10);
		System.out.println(A.b1);
		System.out.println(A.b);
		System.out.println(A.c);
		System.out.println(A.d);
		System.out.println("-------------");
        B b1=new B(1);
    }
 
}
