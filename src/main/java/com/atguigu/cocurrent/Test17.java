package com.atguigu.cocurrent;

import java.util.ArrayList;
import java.util.List;

public class Test17 {

	public static void main(String[] args) {
		/**
		 * substring()包前不包后
		 */
		/*String string="abcd";
		System.out.println(string.substring(0, 6));*/
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 30; i++) {
			int tmp=i;
			new Thread(() -> {
				list.add(tmp);
				System.out.println(Thread.currentThread().getName()+list);
			}, String.valueOf(i)).start();
		}
	}

}
