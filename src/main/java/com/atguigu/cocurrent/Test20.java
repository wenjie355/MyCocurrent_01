package com.atguigu.cocurrent;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import org.junit.Test;

public class Test20 {
	
	public static void main(String[] args) {
		System.out.println(args.toString());
		long maxMemory = Runtime.getRuntime().maxMemory();
		long totalMemory = Runtime.getRuntime().totalMemory();
		
		System.out.println("最大内存为:"+(maxMemory/1024/1024)+"MB");
		System.out.println("最小内存为:"+(totalMemory/1024/1024)+"MB");
		byte[] b =new byte[1*1024*1024*100];
		String s="";
		try {
			while(true) {
				s+=b;
			}
		} catch (Throwable e) {
			System.out.println("**************");
			e.printStackTrace();
		}
		
 	}

	
	
	
	private int count; 
	private  int digui(int i) {
		count++;
		if(i<=0) {
			return 0;
		}else {
			return digui(i-1)+digui(i-2);
		}
		
	}
 }

interface TestInterface1{
	
	
}
class TestInterface2 implements Comparator<Test20>{

	@Override
	public int compare(Test20 o1, Test20 o2) {
		return 0;
	}
	
}

interface TestInterface3 extends TestInterface1{
	
	
}