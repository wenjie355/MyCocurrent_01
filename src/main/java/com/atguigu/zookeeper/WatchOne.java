package com.atguigu.zookeeper;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class WatchOne {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HelloZK.class);
	// 定义常量
	private static final String CONNECTSTRING = "192.168.29.129:2181";
	private static final String PATH = "/atguigu";
	private static final int SESSION_TIMEOUT = 50 * 1000;
	// 定义实例变量
	private ZooKeeper zk = null;

	// 以下为业务方法
	public ZooKeeper startZK() throws IOException {
		return new ZooKeeper(CONNECTSTRING, SESSION_TIMEOUT, new Watcher() {

			@Override	
			public void process(WatchedEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	public void stopZK() throws InterruptedException {
		if (zk != null) {
			zk.close();
		}
	}

	public void createZNode(String path, String nodeValue) throws KeeperException, InterruptedException {
		zk.create(path, nodeValue.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}
	/**
	 * 获取数据时设置监视者
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public String getZNode(String path) throws KeeperException, InterruptedException {
		/**
		 * Watcher()中的方法是异步回调，triggerValue()为异步方法
		 */
		byte[] byteArray = zk.getData(path, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				try {
					triggerValue(path);
				} catch (KeeperException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, new Stat());
		return new String(byteArray);
	}

	public String triggerValue(String path) throws KeeperException, InterruptedException {
		byte[] byteArray = zk.getData(path, false, new Stat());
		String retValue = new String(byteArray);
		System.out.println("**************triggerValue: " + retValue);
		return retValue;
	}
	
	
	public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
		   WatchOne watchOne = new WatchOne();
	       
	       watchOne.setZk(watchOne.startZK());
	       
	       if(watchOne.getZk().exists(PATH, false) == null)
	       {
	          watchOne.createZNode(PATH,"BBB");
	          System.out.println("**********************>: "+watchOne.getZNode(PATH));
	          Thread.sleep(Long.MAX_VALUE);
	       }else{
	          System.out.println("i have znode");
	       }
	}
	 public ZooKeeper getZk()
	   {
	       return zk;
	   }
	   public void setZk(ZooKeeper zk)
	   {
	       this.zk = zk;
	   }  
}
