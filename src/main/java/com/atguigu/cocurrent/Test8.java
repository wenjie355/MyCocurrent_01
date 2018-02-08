package com.atguigu.cocurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class Test8 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> ft = new FutureTask<>(() -> {
			TimeUnit.SECONDS.sleep(2);
			System.out.println("come in callable");
			return 200;
		});
		FutureTask<Integer> ft1 = new FutureTask<>(() -> {
			TimeUnit.SECONDS.sleep(2);
			System.out.println("come in callable");
			return 200;
		});
		new Thread(ft, "thread name").start();
		new Thread(ft1, "thread name").start();
		// FutureTask.get()获得当前线程的计算返回值
		System.out.println(ft.get());
		System.out.println(ft1.get());
		System.out.println(Thread.currentThread().getName() + "  hello");
	}
}

/*
 * class MyCallbale implements Callable<Integer>{
 * 
 * @Override public Integer call() throws Exception {
 * System.out.println("come in callable"); return 200; }
 * 
 * }
 * 
 * 
 * 
 * 
 * 

 */