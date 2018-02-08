package com.atguigu.zookeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import lombok.Getter;
import lombok.Setter;

public class BalanceTest
{
   /**
   * Logger for this class
   */
   private static final Logger logger = Logger.getLogger(HelloZK.class);
   //定义常量
   private static final String CONNECTSTRING = "192.168.29.129:2191";
   private static final int    SESSION_TIMEOUT = 50*1000;
   private static final String PATH = "/atguigu";
   private static final String SUB_PREFIX = "sub";
   //定义实例变量
   private @Setter@Getter ZooKeeper  zk = null;
   private int subCount = 5;
   private List<String> serviceNodeLists = new ArrayList<String>();
   private int serviceNum = 0;
   
   
   //以下为业务方法
   public ZooKeeper startZK() throws IOException
   {
       return new ZooKeeper(CONNECTSTRING, SESSION_TIMEOUT, new Watcher() {
          @Override
          public void process(WatchedEvent event)
          {
              try 
              {	/*为true时，此时的观察者并不是一次性的，而是持久的*/
              	
                 serviceNodeLists = zk.getChildren(PATH, true);
              }catch (KeeperException | InterruptedException e) {
                 e.printStackTrace();
              }
          }
       });
   }
   
   public String dealRequest() throws KeeperException, InterruptedException
   {
       serviceNum = serviceNum +1;
       
       for (int i = serviceNum; i <=subCount; i++) 
       {
          if(serviceNodeLists.contains(SUB_PREFIX+serviceNum))
          {
              return new String(zk.getData(PATH+"/"+SUB_PREFIX+serviceNum, false, new Stat()));
          }else{
              serviceNum = serviceNum +1;
          }
       }
       for (int i = 1; i <=subCount; i++) 
       {
          if(serviceNodeLists.contains(SUB_PREFIX+i))
          {
              serviceNum = i;
              return new String(zk.getData(PATH+"/"+SUB_PREFIX+serviceNum, false, new Stat()));             
          }
       }     
       return "null node~~~~~";
   }
   
   public static void main(String[] args) throws IOException, KeeperException, InterruptedException
   {
       BalanceTest test = new BalanceTest();
       
       test.setZk(test.startZK());
       Thread.sleep(3000);
       String result = null;
       //以轮询的方式访问15次，共计5个节点来应付实现负载均衡
       for (int i = 1; i <=15; i++) 
       {
          result = test.dealRequest();
          System.out.println("****loop:"+i+"\t"+test.serviceNum+"\t"+result);
          Thread.sleep(2000);
       }
   }
 
}
