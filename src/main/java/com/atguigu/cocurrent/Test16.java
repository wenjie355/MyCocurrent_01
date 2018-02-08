package com.atguigu.cocurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Test16 {
	public static void main(String[] args) {
		/**
		 * 聚齐7龙珠，呼唤神龙
		 */											//2
		CyclicBarrier  c = new CyclicBarrier(7, ()->{System.out.println(Thread.currentThread().getName()+"\t"+"打破屏障时所采取的任务");}) ;
		
		for (int i = 1; i <= 14; i++) {
			int tmp=i;
			new Thread(() -> {
				try {
					//使当前线程参加循环屏障
					c.await();//1
					//3
					System.out.println(Thread.currentThread().getName()+tmp);
					
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
	}
}
