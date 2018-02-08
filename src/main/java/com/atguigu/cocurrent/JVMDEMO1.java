package com.atguigu.cocurrent;

public class JVMDEMO1 {

	public static void main(String[] args) {
	
		ClassLoader parent = JVMDEMO1.class.getClassLoader().getParent();
	
	}

}
