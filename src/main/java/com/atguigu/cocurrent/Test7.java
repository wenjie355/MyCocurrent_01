package com.atguigu.cocurrent;

public class Test7 {

	public static void main(String[] args) {
		int letter = 97;
		String result = "";
		if(letter<=123) {
		for (int i = 0; i < 13; i++) {
				for (int j = 0; j < 2; j++) {
					result += (char) (letter++);
				}
				System.out.println(result);
				break;
			}
		}
		
	/*	for (int i = 0; i < 5; i++) {
			while (letter <= 123) {
				for (int j = 0; j < 2; i++) {
					result += (char) (letter++);
					System.out.println(letter);
				}
			}
		}*/
	}
}
