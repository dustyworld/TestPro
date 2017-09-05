package com.test.thread;

import java.util.ArrayList;
import java.util.List;


public class TestThread{
	
	public void sayHello(String name){
		System.out.println("hello, "+name);
	}
	
	public void TestRunnableTest(){
		TestRunnable r = new TestRunnable();
		for(int i=0;i<2;i++){
		    new Thread(r).start();
		}
		
	}
	
	public void testThreadSequence() throws Exception{
		Long time1 = System.currentTimeMillis();
		TestThreadPriority t1 = new TestThreadPriority("--- 线程1 ---",1);
//		t1.setPriority(0);
		t1.start();
		
//		t1.join();
		TestThreadPriority t2 = new TestThreadPriority("--- 线程2 ---",2);
		t2.start();
//		t2.join();
		TestThreadPriority t3 = new TestThreadPriority("--- 线程3 ---",3);
		t3.start();
//		t3.join();
//		List<String> list = new ArrayList<String>();
//		for(int i=0;i<1110000;i++){
//			list.add(""+i);
//		}
//		System.out.println("current thread is "+Thread.currentThread().getName()+" 执行结果: list length is "+list.size());
		Long time2 = System.currentTimeMillis();
		System.out.println("用时："+ (time2 - time1));
	}
	
    public void getResult(){
    	Bank b = new Bank();
        BankThread bt1 = new BankThread(b);
	    new Thread(bt1,"----柜台取钱任务----").start();    //柜台取钱
		BankThread bt2 = new BankThread(b);
		new Thread(bt2,"----ATM取钱任务----").start();    //ATM取钱
		BankThread bt3 = new BankThread(b);            
		new Thread(bt3,"----互联网转账任务----").start();    //互联网转账
    }	
    
	class Bank {

		private int money = 500;
		public synchronized int getMoney(int num){
			synchronized(this){
				if(num < 0){
					return -1;
				}else if(money < 0){
					return -2;
				}else if(money - num < 0){
					return -3;
				}else{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					money -= num;
					System.out.println("余额:"+money + "--------- current thread----"+ Thread.currentThread());
				}
			}
			return num;
		}
	}

	class BankThread extends Thread{
		private Bank bank = null;
		public BankThread(Bank bank){
			this.bank = bank;
		}
		@Override
		public void run(){
			String flag = bank.getMoney(200)+"";
			
			if("-1".equals(flag)){
				flag = "请输入正确的数字";
			}
			if("-2".equals(flag)){
				flag = "抱歉，余额不足";
			}
			if("-3".equals(flag)){
				flag = "抱歉，余额不足，无法完成操作";
			}
			System.out.println("取钱:"+flag+"--------- current thread----"+ Thread.currentThread());
		}
	}
	
	class TestThreadPriority extends Thread{
		private int num = 0;
		private List<String> list = new ArrayList<String>();
		public TestThreadPriority(String name, int num){
			super(name);
			this.num = num;
		}
		@Override
		public void run(){
			if(num == 1){
				for(int i=0;i<1000000;i++){
					list.add(""+i);
				}
			}
			if(num == 2){
				for(int i=0;i<100000;i++){
					list.add(""+i);
				}
			}
			if(num == 3){
				for(int i=0;i<10000;i++){
					list.add(""+i);
				}
			}
			
			
			System.out.println("current thread is "+Thread.currentThread().getName()+" 执行结果: list length is "+list.size());
		}
	}
	
	class TestRunnable implements Runnable{

		private int x = 0;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i =0;i<100;i++){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(x++);
			}
		}
		
	}
}




