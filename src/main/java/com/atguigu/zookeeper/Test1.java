package com.atguigu.zookeeper;

public class Test1 {
	private static int count;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	
	public static  void test() {
		int a=1;
		if(count<=20) {
			count++;
			test();
			a++;
		}
		
		System.out.println(count);
		System.out.println("a:"+a);
	}
}
