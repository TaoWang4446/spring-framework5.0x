/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory;

import org.springframework.lang.Nullable;

/**
 * Interface to be implemented by objects used within a {@link BeanFactory} which
 * are themselves factories for individual objects. If a bean implements this
 * interface, it is used as a factory for an object to expose, not directly as a
 * bean instance that will be exposed itself.
 *
 * <p><b>NB: A bean that implements this interface cannot be used as a normal bean.</b>
 * A FactoryBean is defined in a bean style, but the object exposed for bean
 * references ({@link #getObject()}) is always the object that it creates.
 *
 * <p>FactoryBeans can support singletons and prototypes, and can either create
 * objects lazily on demand or eagerly on startup. The {@link SmartFactoryBean}
 * interface allows for exposing more fine-grained behavioral metadata.
 *
 * <p>This interface is heavily used within the framework itself, for example for
 * the AOP {@link org.springframework.aop.framework.ProxyFactoryBean} or the
 * {@link org.springframework.jndi.JndiObjectFactoryBean}. It can be used for
 * custom components as well; however, this is only common for infrastructure code.
 *
 * <p><b>{@code FactoryBean} is a programmatic contract. Implementations are not
 * supposed to rely on annotation-driven injection or other reflective facilities.</b>
 * {@link #getObjectType()} {@link #getObject()} invocations may arrive early in
 * the bootstrap process, even ahead of any post-processor setup. If you need access
 * other beans, implement {@link BeanFactoryAware} and obtain them programmatically.
 *
 * <p>Finally, FactoryBean objects participate in the containing BeanFactory's
 * synchronization of bean creation. There is usually no need for internal
 * synchronization other than for purposes of lazy initialization within the
 * FactoryBean itself (or the like).
 *
 *
 * *由{@link BeanFactory}中使用的对象实现的接口
 * *本身就是个别对象的工厂。如果bean实现了这一点
 * *接口，它被用作对象要公开的工厂，而不是直接作为
 * 将暴露自身的bean实例。
 * *
 * * <p><b>NB:实现此接口的bean不能作为普通bean使用。</b>
 * FactoryBean是在bean样式中定义的，但是对象是为bean公开的
 * *引用({@link #getObject()})总是它创建的对象。
 * *
 * * <p> factorybean可以支持单例和原型，也可以创建
 * *在需要时或启动时急切地延迟对象。{@link SmartFactoryBean}
 * 接口允许暴露更细粒度的行为元数据。
 * *
 * * <p>此接口在框架本身中大量使用，例如for
 * * AOP {@link org.springframework.aop.framework.ProxyFactoryBean}或
 * * {@link org.springframework.jndi.JndiObjectFactoryBean}。它可以用来
 * *自定义组件;但是，这只在基础结构代码中很常见。
 * *
 * * <p><b>{@code FactoryBean}是一个编程契约。实现不
 * *应该依赖注释驱动的注入或其他反射设备。</b>
 * {@link #getObjectType()} {@link #getObject()}调用可能会提前到达
 * *引导过程，甚至在任何后处理器设置之前。如果你需要访问
 * *其他bean，实现{@link BeanFactoryAware}并以编程方式获取它们。
 * *
 * * <p>最后，FactoryBean对象参与到包含的BeanFactory中
 * * bean创建的同步。通常不需要内部的
 * *同步化，而不是为了延迟初始化
 * * FactoryBean本身(或类似的东西)。
 *
 * 一般 spring 利用反射机制 通过bean的class 指定实现类来实例化 bean。
 * 这个类 可以插手 bean的实例化过程。
 *
 * 这个 时候要返回 实现了FactoryBean接口的类的实例
 * 如果 要当前原有的 &+beanName即可
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 08.03.2003
 * @see org.springframework.beans.factory.BeanFactory
 * @see org.springframework.aop.framework.ProxyFactoryBean
 * @see org.springframework.jndi.JndiObjectFactoryBean
 */
public interface FactoryBean<T> {

	/**
	 * Return an instance (possibly shared or independent) of the object
	 * managed by this factory.
	 * <p>As with a {@link BeanFactory}, this allows support for both the
	 * Singleton and Prototype design pattern.
	 * <p>If this FactoryBean is not fully initialized yet at the time of
	 * the call (for example because it is involved in a circular reference),
	 * throw a corresponding {@link FactoryBeanNotInitializedException}.
	 * <p>As of Spring 2.0, FactoryBeans are allowed to return {@code null}
	 * objects. The factory will consider this as normal value to be used; it
	 * will not throw a FactoryBeanNotInitializedException in this case anymore.
	 * FactoryBean implementations are encouraged to throw
	 * FactoryBeanNotInitializedException themselves now, as appropriate.
	 * @return an instance of the bean (can be {@code null})
	 * @throws Exception in case of creation errors
	 * @see FactoryBeanNotInitializedException
	 *
	 * *返回一个由FactroyBean创建的对象的实例(可能是共享的或独立的)
	 * *由这家工厂管理。如果是单例 ，则放入单例缓存池中。
	 * * <p>与{@link BeanFactory}一样，这允许同时支持
	 * *单例和原型设计模式。
	 * * <p>如果这个FactoryBean在的时候还没有完全初始化
	 * *调用(例如，因为它涉及到一个循环引用)，
	 * *抛出一个对应的{@link FactoryBeanNotInitializedException}。
	 * * <p>从Spring 2.0开始，factorybean可以返回{@code null}
	 * *对象。工厂会认为这是正常价值使用;它
	 * 在这种情况下，将不再抛出FactoryBeanNotInitializedException。
	 * * FactoryBean实现鼓励抛出
	 * * FactoryBeanNotInitializedException本身现在，适当的。
	 * * @return bean的实例(可以是{@code null})
	 * *创建错误时抛出异常
	 * * @see FactoryBeanNotInitializedException
	 */
	@Nullable
	T getObject() throws Exception;

	/**
	 * Return the type of object that this FactoryBean creates,
	 * or {@code null} if not known in advance.
	 * <p>This allows one to check for specific types of beans without
	 * instantiating objects, for example on autowiring.
	 * <p>In the case of implementations that are creating a singleton object,
	 * this method should try to avoid singleton creation as far as possible;
	 * it should rather estimate the type in advance.
	 * For prototypes, returning a meaningful type here is advisable too.
	 * <p>This method can be called <i>before</i> this FactoryBean has
	 * been fully initialized. It must not rely on state created during
	 * initialization; of course, it can still use such state if available.
	 * <p><b>NOTE:</b> Autowiring will simply ignore FactoryBeans that return
	 * {@code null} here. Therefore it is highly recommended to implement
	 * this method properly, using the current state of the FactoryBean.
	 * @return the type of object that this FactoryBean creates,
	 * or {@code null} if not known at the time of the call
	 * @see ListableBeanFactory#getBeansOfType
	 */
	@Nullable
	Class<?> getObjectType();//返回 FactoryBean 创建的类型

	/**
	 * Is the object managed by this factory a singleton? That is,
	 * will {@link #getObject()} always return the same object
	 * (a reference that can be cached)?
	 * <p><b>NOTE:</b> If a FactoryBean indicates to hold a singleton object,
	 * the object returned from {@code getObject()} might get cached
	 * by the owning BeanFactory. Hence, do not return {@code true}
	 * unless the FactoryBean always exposes the same reference.
	 * <p>The singleton status of the FactoryBean itself will generally
	 * be provided by the owning BeanFactory; usually, it has to be
	 * defined as singleton there.
	 * <p><b>NOTE:</b> This method returning {@code false} does not
	 * necessarily indicate that returned objects are independent instances.
	 * An implementation of the extended {@link SmartFactoryBean} interface
	 * may explicitly indicate independent instances through its
	 * {@link SmartFactoryBean#isPrototype()} method. Plain {@link FactoryBean}
	 * implementations which do not implement this extended interface are
	 * simply assumed to always return independent instances if the
	 * {@code isSingleton()} implementation returns {@code false}.
	 * <p>The default implementation returns {@code true}, since a
	 * {@code FactoryBean} typically manages a singleton instance.
	 * @return whether the exposed object is a singleton
	 * @see #getObject()
	 * @see SmartFactoryBean#isPrototype()
	 */
	default boolean isSingleton() {
		return true;
	}

}
