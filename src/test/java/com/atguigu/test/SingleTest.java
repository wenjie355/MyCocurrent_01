package com.atguigu.test;

import sun.security.jca.GetInstance;

public class SingleTest {

	private SingleTest() {}
	
	//饿汉式
	
	private static final SingleTest instance  = new SingleTest();
	
	public static SingleTest GetInstance() {
		return instance;
	}
		
	
	public static void main(String[] args) {
	System.out.println(	lazySingle.GetInstance());
	
	}
}

class lazySingle{
	private lazySingle() {}
	
	private static lazySingle  instance =null;
	
	public static lazySingle  GetInstance() {
		if(instance==null) {
			
			synchronized (lazySingle.class) {
				if(instance==null) {
					instance=new lazySingle();
				}
			}
		}
		return instance;
	}
}
