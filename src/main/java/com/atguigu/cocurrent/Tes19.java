package com.atguigu.cocurrent;

public class Tes19 {

	 static abstract class Human{
		  
		         public abstract void say();
		  
		      };
		  
		      static class Man extends Human{
		  
		         @Override
		  
		         public void say(){
		  
		             System.out.println("hi,you are a good man!");
		  
		         }
		  
		      } ;
		  
		      static class Woman extends Human{
		  
		         @Override
		  
		         public void say(){
		  
		             System.out.println("hi,young lady!");
		  
		         }
		  
		      } ;
		  
		      //主函数入口
		  
		      public static void main(String[] args) {
		  
		         Human man = new Man();
		  
		         Human woman = new Woman();
		  
		         man.say();
		  
		         woman.say();
		  
		         woman = new Man();
		  
		         woman.say();
		  
		      }

}
