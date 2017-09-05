package com.test.thread;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.test.IO.ImageUtil;


public class TestA {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		
//			TestThread t = new TestThread();
//			TestRunnable tr = new TestRunnable();
//			t.sayHello("Jim");
//	        t.getResult();
//	        t.testThreadSequence();
//			t.TestRunnableTest();
//			Object o = new Object();
//			o.wait();
//			o.notify();
//			o.notifyAll();
//			String s = "";
			
//			Thread.sleep(1000);
		    PreparedStatement ps = null;
		    Connection conn = null;
		    FileInputStream in = null;	
			String path = "C:\\Users\\jianjian\\Desktop\\Jim_photo.png";
			in = new FileInputStream(new File(path));
//			byte[] bt = new byte[in.available()];
//			in.read(bt);
			
//			byte[] bt = path.getBytes();
			
//			ByteArrayInputStream bais = new ByteArrayInputStream(bt);
			try {  
				
				
	            Class.forName("com.mysql.jdbc.Driver");
	            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test", "root", "root");
	            ps = conn.prepareStatement("UPDATE student SET portrait = ? WHERE name = ?");    
	            ps.setBlob(1, in);
	            ps.setString(2, "Sherley");
	            ps.executeUpdate();
	            System.out.println("----  success  -----");
	            
	        } catch (Exception e) {  
	        	System.out.println("----  faild  -----");
	            e.printStackTrace();  
	        }  
			
			
	}

}

class student {

	private int id;
	private String name;
	private String number;
	private Blob portrait;

	public student() {
		
	}

	public student(int id, String name, String number, Blob portrait) {
		
		this.id = id;
		this.name = name;
		this.number = number;
		this.portrait = portrait;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Blob getPortrait() {
		return portrait;
	}

	public void setPortrait(Blob portrait) {
		this.portrait = portrait;
	}

}
