package com.atguigu.cocurrent;

public class Test3 {
	public static void main(String[] args) {
		/**
		 * lambda表达式左侧为引用对象
		 */
		B b=(int x,int y)->{return (x+y);};
		System.out.println(b.add(1, 2));
	}
}
/**
 * @FunctionalInterface注解表名该接口类只能有一个抽象类，可以有其他方法
 * @author Administrator
 *
 */
@FunctionalInterface
interface B{
		
	public int add(int x,int y);
	
	default int Divider(int x,int y) {
		return x/y;
	}
	
	public static int  add1(int x,int y) {
		return 0;
	}
}