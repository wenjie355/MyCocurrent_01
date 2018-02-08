package com.atguigu.cocurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Test15 {
	public static void main(String[] args)
	{
		Semaphore semaphore = new Semaphore(8);//模拟3个停车位
		
		for (int i = 1; i <=40; i++) //模拟6部汽车
		{
			new Thread(() -> {
				try 
				{
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName()+"\t -----抢到车位");
					TimeUnit.SECONDS.sleep(new Random().nextInt(4));
					System.out.println(Thread.currentThread().getName()+"\t ----------离开车位");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					semaphore.release();
				}
			},String.valueOf(i)).start();
		}
	}
}
