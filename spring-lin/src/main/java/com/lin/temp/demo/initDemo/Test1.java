/**
 *<client>()方法对于类或接口来说并不是必需的，
 * 如果一个类中没有静态语句块，也没有静态变量的赋值操作，
 * 那么编译器可以不为这个类生成<client>()方法。
 *
 * 接口中不能使用静态语句块，但仍然有变量赋值初始化的操作，因此接口和类一样都会生成<client>()方法。
 * 但接口与类不同的是，执行接口的<client>()方法不需要执行父接口的<client>()方法，只有当父接口定义的变量被使用时父接口才会初始化。
 * 另外接口的实现进行初始化时，也不会执行接口的<client>()方法，同理除非访问了接口中定义的静态变量才会初始化接口。
 * 如果通过子类访问定义在父类中的静态变量时，只有父类会被初始化，子类则不会被初始化，如下代码清单：
 *

 */
class Parent {
	public static int A = 1;
	static {
		System.out.println("this is parent");
	}
}

class Sub extends Parent {
	public static int B = 2;
	static {
		System.out.println("this is Sub");
	}
}

public class Test1 {
	public static void main(String[] args) {
		int i = Sub.A;
	}
}
// output:
// this is parent

