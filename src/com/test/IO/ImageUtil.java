package com.test.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {
	
	//read local image to get inputStream
	public static FileInputStream readImage(String path) throws IOException{
		return new FileInputStream(new File(path));
	}
	
	//read image to get output stream
	public static void readBin2Image(InputStream in, String targetPath){
		
		File file = new File(targetPath);
		String path = targetPath.substring(0,targetPath.lastIndexOf("/"));
		if(!file.exists()){
			new File(path).mkdir();
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			int len = 0;
			byte[] b = new byte[1024];
			while((len = in.read(b)) != -1){
				fos.write(b,0,len);
			}
			fos.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
