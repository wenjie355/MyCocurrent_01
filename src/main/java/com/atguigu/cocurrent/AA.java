package com.atguigu.cocurrent;

import org.junit.Test;

public class AA {

	
	@Test
	public void test() {
		test1();
		System.out.println("main");
	}

	public  void test1() {
		test2();
	}

	public  void test2() {
		System.out.println("22");
	}
}
