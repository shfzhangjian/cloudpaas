/**
 * 
 */
package com.cloudpaas.cache.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 大鱼
 *
 * @date 2019年8月22日 上午10:37:51
 */
@Retention(RetentionPolicy.RUNTIME)//在运行时可以获取  
@Target(value = {ElementType.METHOD, ElementType.TYPE})//作用到类，方法，接口上等
public @interface  CacheClear {

	/**
	 * 健值
	 * @return
	 */
	public String key() default "";
	
	/**
	 * 健值前缀
	 * @return
	 */
	public String prefix() default "";
	
	/**
	 * 
	 * @return
	 */
	public String[] keys() default "";
	
	/**
	 * 
	 * @return
	 */
	String keyGenerator() default "";
}