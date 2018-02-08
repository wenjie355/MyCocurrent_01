package com.atguigu.test;

public class dervied extends Base {
	/**
	 * 执行完无参构造器，才给属性赋值
	 */
    private static String name = "dervied";

    public dervied() {
        tellName();
        printName();
    }
    //1
    public void tellName() {
        System.out.println("Dervied tell name: " + name);
    }
    
    public void printName() {
        System.out.println("Dervied print name: " + name);
    }

    public static void main(String[] args){
        
        new dervied();    
    }
}

class Base {
    
    private String name = "base";

    public Base() {
        tellName();
        printName();
    }
    
    public void tellName() {
        System.out.println("Base tell name: " + name);
    }
    
    public void printName() {
        System.out.println("Base print name: " + name);
    }
}
