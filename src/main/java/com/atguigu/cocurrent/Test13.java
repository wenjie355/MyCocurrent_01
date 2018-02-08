package com.atguigu.cocurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test13 {

	public static void main(String[] args) {
	//	testThreadpool1();
		
		//ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(7);
		ScheduledExecutorService poolExecutor = Executors.newScheduledThreadPool(7);
		try {
			for (int i = 1; i <= 20; i++) {
				poolExecutor.schedule(()->{System.out.println("hello");}, 2, TimeUnit.SECONDS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			poolExecutor.shutdown();
		}
	
	}

	private static void testThreadpool1() {
		/**
		 * Executors.newSingleThreadExecutor()获得线程池对象
		 */
		//ExecutorService service = Executors.newSingleThreadExecutor();//单例线程池
		//ExecutorService service = Executors.newFixedThreadPool(5);//指定数量的线程池
		ExecutorService service = Executors.newCachedThreadPool();//根据CPU自己选择获得线程
		Future<Integer> ft = null;
		try {
			for (int i = 1; i <= Integer.MAX_VALUE; i++) {
				ft = service.submit(()->{
					System.out.println(Thread.currentThread().getName()+"号受理窗口");	
					//TimeUnit.SECONDS.sleep(1);
					return new Random().nextInt(4)+1;
				});
				System.out.println(Thread.currentThread().getName()+ft.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			service.shutdown();
		}
	}
	
	
	
	
}	
