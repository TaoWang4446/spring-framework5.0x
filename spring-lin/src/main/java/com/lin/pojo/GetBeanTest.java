package com.lin.pojo;

/**
 * 创建 调用方法
 */
public abstract class GetBeanTest {

	public void showMe(){
		this.getBean().showMe();
	}

	/**
	 * 抽象方法  没有被实现 ，怎么能调用呢
	 * 答案是 spring为我们提供的获取器
	 *
	 * @return
	 */
	public abstract User getBean();
}
