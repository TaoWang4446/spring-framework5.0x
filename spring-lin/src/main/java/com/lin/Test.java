package com.lin;

import com.lin.pojo.TestBean1;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Create by ljc on 2019/11/6
 */

public class Test
{
	public static void main(String[] args)
	{
		/**
		 * 使用注释启动Spring
		 * 把类扫描出来
		 * 把bean实例化
		 */
//		AnnotationConfigApplicationContext context =
//				new AnnotationConfigApplicationContext(AppConfig.class);


		/**
		 * 使用xml配置文件启动Spring
		 * 1.读取配置文件 spring-config.xml
		 * 2.根据 spring-config.xml 找到对应的类的配置，并实例化
		 * 3.调用实例化后的实例
		 */
		/**
		 * Resource资源是如何封装的呢？
		 * 配置文件的封装：
		 * by ClassPathResource 去读取配置文件
		 * url（统一资源定位器）：java中将不同来源的资源封装成url。通过注册不同的 handler（urlStreamHandler）处理不同来源的读取逻辑
		 * handler类型 通过 不同协议前缀来识别（eg：file： http: jar: ) classpath:
		 */
		BeanFactory context = new XmlBeanFactory(new ClassPathResource("spring-config.xml"));
		TestBean1 testBean1 = (TestBean1) context.getBean("testBean1");
		testBean1.say();

		//ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

		/**
		 * bean的加载 bean的获取过程：
		 * 1. 检查单例缓存中 对应的 已经 加载的bean。
		 * 2.返回对应的实例，有时候存在 如 beanFactory的情况，并不是直接返回实例本身，而是返回指定方法返回的实例
		 * 3.如果当前 不存在 beanName，调用 父工厂中的.getBean()去寻找，父工厂通常为空
		 * 4.将存储xml配置文件的 GenericBeanDefinition 转换为 RootBeanDefinition，如果 bean名字是子bean的话，同时会合并父类
		 * 相关属性 text。
		 * 5.递归调用 实例化bean
		 * 6.根据 scope 进行实例化
		 * 7.如果指定的需求类型不为空，需要进行类型转换，否则直接强制转换。
		 *
		 * spring 获取bena的规则中有一条：
		 * 尽可能保证 所有 bean初始化后，都会调用 注册的 BeanPostProcessor.postProcessAfterInitilzation()进行处理
		 */
//		GetBeanTest getBeanTest = (GetBeanTest) context.getBean("getBeanTest");
//		getBeanTest.showMe();

		//获取对应类
//		System.out.println(context.containsBean("testService"));
//		TestService testService = (TestService) context.getBean("testService");
//		System.out.println(testService);

		/**
		 * 循环依赖
		 * 两个或多对象 相互持有对方
		 * 有setter 构造的 循环依赖
		 * 造成 内存溢出
		 *
		 *
		 */
	}
}