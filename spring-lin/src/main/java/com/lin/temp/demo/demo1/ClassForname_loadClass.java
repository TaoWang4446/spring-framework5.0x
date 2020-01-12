package com.lin.temp.demo.demo1;


/**
 * forName0(className, true, ClassLoader.getClassLoader(caller), caller);
 * 在java中Class.forName()和ClassLoader都可以对类进行加载。
 * ClassLoader就是遵循双亲委派模型最终调用启动类加载器的类加载器，
 * 实现的功能是“通过一个类的全限定名来获取描述此类的二进制字节流”，
 * 获取到二进制流后放到JVM中。
 * Class.forName()方法实际上也是调用的CLassLoader来实现的。
 *
 */
public class ClassForname_loadClass {

	/*@CallerSensitive
	public static Class<?> forName(String className)
			throws ClassNotFoundException {
		Class<?> caller = Reflection.getCallerClass();
		return forName0(className, true, ClassLoader.getClassLoader(caller), caller);
	}

	最后调用的方法是forName0这个方法，在这个forName0方法中的第二个参数被默认设置为了true，
	这个参数代表是否对加载的类进行初始化，设置为true时会类进行初始化，代表会执行类中的静态代码块，以及对静态变量的赋值等操作。

也可以调用Class.forName(String name, boolean initialize,ClassLoader loader)方法来手动选择在加载类的时候是否要对类进行初始化。

应用场景
在我们熟悉的Spring框架中的IOC的实现就是使用的ClassLoader.loadClass();

而在我们使用JDBC时通常是使用Class.forName()方法来加载数据库连接驱动。
这是因为在JDBC规范中明确要求Driver(数据库驱动)类必须向DriverManager注册自己。

以MySQL的驱动为例解释：

public class Driver extends NonRegisteringDriver implements java.sql.Driver {
    // ~ Static fields/initializers
    // ---------------------------------------------

    //
    // Register ourselves with the DriverManager
    //
    static {
        try {
            java.sql.DriverManager.registerDriver(new Driver());
        } catch (SQLException E) {
            throw new RuntimeException("Can't register driver!");
        }
    }

    // ~ Constructors
    // -----------------------------------------------------------

    /**
     * Construct a new driver and register it with DriverManager
     *
     * @throws SQLException
     *             if a database error occurs.
     */
	//public Driver() throws SQLException {
		// Required for Class.forName().newInstance()
	//}
//}
	//*/

	//我们看到Driver注册到DriverManager中的操作写在了静态代码块中，这就是为什么在写JDBC时使用Class.forName()的原因了。
}
