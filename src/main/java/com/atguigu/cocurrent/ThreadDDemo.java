package com.atguigu.cocurrent;

class Accumulation {
//	private String lation="";
	private int temp = 0;
	private int num = 1;
	private String name = "";

	private StringBuffer sb = new StringBuffer(name);

	/**
	 * 操作数字
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void add() throws InterruptedException {

		if (temp != 0) {
			this.wait();
		}
		int max=1;
		for (int i = num; i <= num+1; i++) {
			String of = String.valueOf(i);
			name += of;
			if (max == 2) {
				try {
					int parseInt = Integer.parseInt(name);
					num = parseInt;
				} catch (Exception e) {
					break;
				}
				
			}
			++max;
		}
		sb.append(name);
		++temp;
		System.out.println(Thread.currentThread().getName()+"\t"+sb.toString());
		this.notifyAll();

	}

	/**
	 * 操作英文字母
	 * @throws InterruptedException 
	 */
	public synchronized void sub() throws InterruptedException {
		int numa=65;
		char c = (char) numa;
		if (temp==0) {
			this.wait();
		}
		name+=c;
		sb.append(name);
		numa=numa+1;
		System.out.println(Thread.currentThread().getName()+"\t"+sb.toString());
		
		--temp;
		this.notifyAll();
		
	}
}

public class ThreadDDemo {
	public static void main(String[] args) {

		Accumulation accumulation = new Accumulation();
		
		
		new Thread(() -> {
			
				try {
					for (int i = 0; i < 26; i++) {
						
						accumulation.add();
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}, "your Thread A").start();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		new Thread(() -> {
			
				try {
					for (int i = 0; i <26; i++) {
						
						accumulation.sub();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
			}
		}, "your Thread B").start();
		
	}
}
