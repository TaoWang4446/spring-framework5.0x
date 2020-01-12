package com.lin.temp.demo.initDemo;

/**
 * <client>()方法是由编译器 自动收集类中的所有类变量（静态变量）的赋值动作和 静态语句块（static{}）中的语句合并时产生的，
 * 编译器收集的顺序是由语句在源文件中出现的 顺序 决定的，
 * 静态语句块只能访问到定义在静态语句块 之前 的变量，
 * 定义在它之后的变量，静态语句块能进行 赋值操作 ，但是 不能进行访问。
 *
 */
public class Test {
	static{
		i = 0; 				//这句能编译通过
		//System.out.println(i); //这里编译会报错，提示“非法向前引用”
	}
	static int i;
}