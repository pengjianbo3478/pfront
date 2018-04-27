package com.gl365.app.common;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
/**
 * 权限拦截器
 * @author dfs_519
 * 2017年5月6日下午12:04:37
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Order(Ordered.HIGHEST_PRECEDENCE) // 优先级,暂定最高级
public @interface Permis {
	/**
	 * 权限控制：1（管理员）,2（店长）,3（操作员）,4（离职）
	 * 多个权限逗号分隔
	 * 默认""不受权限控制
	 */
	String permission() default "";
}
