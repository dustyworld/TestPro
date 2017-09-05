package com.test.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class TestBlockingQueue {
	
	public static void main(String[] args){
		System.out.println("Producer --> Consumer begining");
		Queue<Integer> queue = new LinkedList<Integer>();
		int maxSize = 10;
		Producer p = new Producer(queue,maxSize,"thread -- producer");
		Consumer c = new Consumer(queue,maxSize,"thread -- consumer");
		p.start();
		c.start();
	}

}

class Producer extends Thread{
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	private Queue<Integer> queue;
	private int maxSize;
	public Producer(Queue<Integer> queue, int maxSize, String name){
		super(name);
		this.queue = queue;
		this.maxSize = maxSize;
	}
	@Override
	public void run(){
		while(true){
			synchronized(queue){
				while(queue.size() == maxSize){
					System.out.println("Queue is full, Producer thread waiting for consumer to take something from queue ******** " + "Current Date is: "+df.format(new Date()));
					try {
						queue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				
//				if(queue.size() == 0){
//					queue.notifyAll();
//				}
				
				Random r = new Random();
				int i = r.nextInt();
				System.out.println("Producing value : " + i+" *********** " + Thread.currentThread().getName()+" ******* Current Date is: "+df.format(new Date()));
				queue.add(i);
			}
		}
	}
}

class Consumer extends Thread{
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	private Queue<Integer> queue;
	private int maxSize;
	public Consumer(Queue<Integer> queue, int maxSize, String name){
		super(name);
		this.queue = queue;
		this.maxSize = maxSize;
	}
	@Override
	public void run(){
		while(true){
			synchronized(queue){
				while(queue.isEmpty()){
					System.out.println("Queue is empty," + "Consumer thread is waiting" + " for producer thread to put something in queue ******** " +"Current Date is: "+df.format(new Date()));
					try {
						queue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
//				if(queue.size() == maxSize){
//					queue.notifyAll();
//				}
				
				System.out.println("Consuming value : " + queue.remove()+" *********** " + Thread.currentThread().getName()+" ******* Current Date is: "+df.format(new Date()));
				queue.notifyAll();
			}
		}
	}
}

class BlockingQueue {
	@SuppressWarnings("rawtypes")
	private Queue queue = new LinkedList();
	private int limit = 10;

	public BlockingQueue(int limit) {
		this.limit = limit;

	}

	@SuppressWarnings("unchecked")
	public synchronized void enqueue(Object o) throws Exception {
		while (this.limit == this.queue.size()) {
			wait();
		}
		if(this.queue.size() == 0){
			notifyAll();
		}
		this.queue.add(o);
	}
	
	public synchronized Object dequeue() throws Exception{
		while(this.queue.size() == 0 ){
			wait();
		}
		if(this.queue.size() == this.limit){
		    notifyAll();	
		}
		return this.queue.remove(0);
	}
}
