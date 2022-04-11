package com.bingoabin.annotation;

/**
 * @author xubin03
 * @date 2021/5/19 8:56 下午
 */
public class Annotation {
	// #在编译时进行格式检查(JDK内置的三个基本注解)
	// @Override: 限定重写父类方法, 该注解只能用于方法
	// @Deprecated: 用于表示所修饰的元素(类, 方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择
	// @SuppressWarnings: 抑制编译器警告
	public static void main(String[] args) {

	}

	//自定义注解：
	//     定义新的 Annotation 类型使用 @interface 关键字
	//     自定义注解自动继承了java.lang.annotation.Annotation接口
	//     Annotation 的成员变量在 Annotation 定义中以无参数方法的形式来声明。其方法名和返回值定义了该成员的名字和类型。我们称为配置参数。类型只能是八种基本数据类型、String类型、Class类型、enum类型、Annotation类型、以上所有类型的数组。
	//     可以在定义 Annotation 的成员变量时为其指定初始值, 指定成员变量的初始值可使用 default 关键字
	//     如果只有一个参数成员，建议使用参数名为value
	//     如果定义的注解含有配置参数，那么使用时必须指定参数值，除非它有默认值。格式是“参数名 = 参数值”，如果只有一个参数成员，且名称为value，可以省略“value=”
	//     没有成员定义的 Annotation 称为标记; 包含成员变量的 Annotation 称为元数据 Annotation
	// 注意：自定义注解必须配上注解的信息处理流程才有意义。
	public @interface MyAnnotation {
		String value() default "hello";
	}
}
