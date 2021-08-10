package com.bingoabin.classloader;

import java.lang.reflect.Method;

public class TestMyClassLoader {

	/*public static void main(String[] args) throws Exception {
		// 自定义类加载器的加载路径
		MyClassLoader myClassLoader = new MyClassLoader("D:\\lib");
		// 包名+类名
		Class<?> c = myClassLoader.loadClass("classloader.Test");

		if (c != null) {
			Object obj = c.newInstance();
			Method method = c.getMethod("say", null);
			method.invoke(obj, null);
			System.out.println(c.getClassLoader().toString());
		}
	}*/

	public static void main(String[] args) {
		while (true){
			try {
				String path = "/Users/kkb-james/01-kkb/05-workspace/vip-class/jvm/src/main/java/";
				MyClassLoader loader = new MyClassLoader(path);
				Class<?> aClass = loader.findClass("classloader.Teacher");
				Object instance = aClass.newInstance();
				Method method = aClass.getMethod("teach");
				method.invoke(instance);
				Thread.sleep(5000) ;

				//DriverManager;
			}catch (Exception e){
				System.out.println("没有找到类");
				try {
				 	Thread.sleep(5000);
				}catch (Exception e1){
					e1.printStackTrace();
				}
			}
		}
	}
}
