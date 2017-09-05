package com.test;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class JavaTest {
	
    
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		 
//		 Double rand = null;
//	        Random random = new Random();
//	        rand = random.nextDouble();
//	        System.out.println(rand);
//		
//		String fileName = "aaa.txt";
//		String prefixFileName = fileName.substring(0, fileName.lastIndexOf("."));
//        String suffixFileName = fileName.substring(fileName.lastIndexOf(".")).toUpperCase();
//        System.out.print( prefixFileName + "----" + suffixFileName );
		//create thread pool
		ExecutorService e = Executors.newFixedThreadPool(2);
//		List<Future> list = new ArrayList<Future>();
		for(int i=0;i<10;i++){
			Callable c = new JavaTestThread(i);
			Future f = e.submit(c);
			System.out.println("-------"+ f.get().toString());
		}
		
	
		
	}
    
}
class JavaTestThread implements Callable<Object>{
	private int flag = 0;
	
	public JavaTestThread(int f){
		this.flag = f;
	}

	@Override
	public Object call() throws Exception {
		return "this is thread ... " + flag;
	}
}

