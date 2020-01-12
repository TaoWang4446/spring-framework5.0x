package com.lin.temp.demo.demo1;


/**
 * <client>()方法与类的构造函数不同，它不需要显式的调用父类构造器，
 * 虚拟机会保证在<client>()方法执行之前，父类的<client>()方法已经执行完毕。
 * 因此在虚拟机中第一个被执行的<client>()方法肯定是java.lang.Object的<client>()方法。
 * 由于父类的<client>()方法先执行，也就意味着父类中定义的 静态语句块 要 优先于 子类的 变量赋值 操作，
 * 如下代码，字段B的值会是2而不会是1
 *
 */
public class Test {
	static class Parent{
		public static int A = 1;
		static{
			A = 2;
		}
	}

	static class Sub extends Parent{
		public static int B = A;
	}
	
	public static void main(String[] args) {
		System.out.println(Sub.B);
	}
}