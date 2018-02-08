package com.atguigu.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class test1 {
	public void getCustomerInfo() {

        try {

          throw new IOException();

        } catch (FileNotFoundException ex) {

            System.out.print("FileNotFoundException!");

        } catch (IOException ex) {

            System.out.print("IOException!");

        } catch (java.lang.Exception ex) {

            System.out.print("Exception!");

        }
    }
	public static void main(String[] args) {
		
		
		/**
		 * 冒泡排序
		 */
		byte[] b = new byte[] { 1, 4, 5, 5, 3, 6, 7 };
		for (int i = 0; i < b.length-1; i++) {
			for (int j = 0; j < b.length-1-i; j++) {
				if(b[j]>b[j+1]) {
					byte tmp=b[j+1];
					b[j+1]=b[j];
					b[j]=tmp;
				}
			}
		}
		System.out.println(Arrays.toString(b));

		/**
		 * 选择排序
		 */
		int[] b1 = new int[20];
		for (int i = 0; i < b1.length; i++) {
			b1[i] = new Random().nextInt(b1.length);
		}

		for (int i = 0; i < b1.length - 1; i++) {
			// 假设第一个是最小的数的下标
			int minIndex = i;
			// (4)
			// b1.length-i-1
			for (int j = i + 1; j < b1.length; j++) {
				if (b1[j] < b1[minIndex]) {
					minIndex = j;
				}
			}
			if (i != minIndex) {
				// 基准点
				int tmp = b1[i];
				// 最小值与基准点交换
				b1[i] = b1[minIndex];
				b1[minIndex] = tmp;
			}
		}
		Arrays.sort(b1);
		System.out.println("****" + Arrays.binarySearch(b1, 3));
		System.out.println("*****选择排序:" + Arrays.toString(b1));
	}
		/**
		 * 快排
		 * @param left
		 * @param right
		 * @param arr
		 */
	public static void quickSort(int left, int right, int[] arr) {
		if (left > right) {
		return;
		}
		int p = arr[left];
		int i = left, j = right;
		while (i < j) {
			while (arr[j] >= p && i < j) {
				j--;
			}
			while (arr[i] <= p && i < j) {
				i++;
			}
			if (i < j) {
				int tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}
		arr[left] = arr[i];
		arr[i] = p;
		quickSort(left, i - 1, arr);
		quickSort(i + 1, right, arr);
	}

}
/*for (int i = 0; i < b.length - 1; i++) {
	for (int j = 0; j < b.length - 1 - i; j++) {
		if (b[j] > b[j + 1]) {
			byte tmp = b[j];
			b[j + 1] = b[j];
			b[j] = tmp;
		}
	}*/
