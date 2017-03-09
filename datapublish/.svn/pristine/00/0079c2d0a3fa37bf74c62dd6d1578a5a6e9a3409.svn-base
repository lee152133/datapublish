package com.keda.webservice.server.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;

public class FileUtil {
	/**
	 * the traditional io way 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(String filename) throws IOException{
		
		File f = new File(filename);
		if(!f.exists()){
			throw new FileNotFoundException(filename);
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream((int)f.length());
		BufferedInputStream in = null;
		try{
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while(-1 != (len = in.read(buffer,0,buf_size))){
				bos.write(buffer,0,len);
			}
			return bos.toByteArray();
		}catch (IOException e) {
			e.printStackTrace();
			throw e;
		}finally{
			try{
				in.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}
	
	
	public static String parseFile(String file) throws Exception{
		
		InputStream in = null;
		String bl = null;
		try {
			in = new FileInputStream(file);
			if(in!=null){
				byte[] buf = new byte[1024];
				int len = in.read(buf);
				//System.out.println("len == "+len);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				try{
					while( len!= -1 ){
						baos.write(buf, 0, len);
						len = in.read(buf);
					}
					baos.flush();
					System.out.println("baos.toByteArray().length == "+baos.toByteArray().length);
					bl = new String(Base64.encodeBase64(baos.toByteArray()),"UTF-8");

//					System.out.println("----->"+bl);
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(baos!=null){
						baos.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			file = null;
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return bl;
		
	}
	
	public static void saveFileByString(String str, String path){
		FileOutputStream fileoutputstream = null;
        try {
        	byte[] newFile = Base64.decodeBase64(str.getBytes("UTF-8"));
    		fileoutputstream = new FileOutputStream(path);
			fileoutputstream.write(newFile);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fileoutputstream != null)
					fileoutputstream.close();
			} catch (Exception e2) {
			}
		}
		
	}

}
