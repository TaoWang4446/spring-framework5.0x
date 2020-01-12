package com.lin.temp.demo.initDemo;

import java.util.Random;

/**
 * 虚拟机会保证一个类的<client>()方法在多线程环境中被正确的加锁、同步，
 * 如果多个线程去初始化一个类，那么只会有一个线程去执行这个类的<client>()方法，
 * 其他线程都需要阻塞等待，直到活动线程的<client>()方法执行完毕。
 * 如果一个类的<client>()方法有耗时很长的操作，就能造成多个进程阻塞，在实际应用中，这种阻塞往往是很隐蔽的。
 * 同一个虚拟机上类的<client>()方法只会执行一次。
 * 编译时的常量访问不会对该类进行初始化，如果只有在运行时才能确定，则会执行类的初始化动作，
 * 如下代码：

 */
class Init{
	static final int x = 6 / 3;
	static final int y = new Random().nextInt(100);
	static{
		System.out.println("init----");
	}
}

public class Test2 {
	public static void main(String[] args) {
		System.out.println(Init.x); //此处Init类不会进行初始化
		System.out.println(Init.y); //执行这条语句时，Init类才进行初始化
	}
}

/**
 * 类只有在被首次主动使用时，才进行初始化，类的主动使用方式：
 *
 * （1）、实例化一个类，new一个类的实例对象
 *
 * （2）、访问类的静态变量
 *
 * （3）、调用类的静态方法
 *
 * （4）、通过反射调用类
 *
 * （5）、实例化类的子类
 *
 * （6）、被标位启动类的类
 *
 * Java虚拟机执行类的初始化语句为类赋予初始值，在程序中静态变量初始化有两种方式
 * （1）、
 * 在变量声明处初始化
 * （2）、在静态代码块中进行初始化
 *
 * private static int param1 = 1;//变量声明时初始化
 * 	private static int param2;
 * 	static{
 * 		param2 = 2; //静态代码块中进行初始化
 *        }
 *
 * */
// output:
//2
//init----
//91

