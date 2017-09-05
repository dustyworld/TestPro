package com.test.thread;

public class TestRunnable {
	public static void main(String[] args) throws Exception{
		
		for(int i=0;i<10;i++){
			Thread t = new MyThread();
			t.start();
	    }
		Thread.sleep(10000);//让上面的线程运行完成
		R r = new R();
		for(int i=0;i<10;i++){
			Thread t = new Thread(r);
			t.start();
		}
	}
}
class MyThread extends Thread{
	public int x = 0;
	public void run(){
		System.out.println(++x);
	}
}

class R implements Runnable{
	private int x = 0;
	public void run(){
		System.out.println(++x);
	}
}
	
