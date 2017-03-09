package com.keda.webservice.server.utils;

import org.apache.log4j.Logger;

public abstract class AbstarctTask   {

	private static final Logger log = Logger.getLogger(AbstarctTask.class);
	private static int threadNumber = 0;
	private Object lock = new Object();
	
	private Thread thread;
	
	private boolean work = true;
	
	private String name;
	
	/**
	 * 超时时间。单位：毫秒
	 */
	private long timeout = 10000;
	
	public abstract void run();
	
	public void start(){

		if(thread != null){
			//重复的启动
			return;
		}
		
		work = true;

		log.info("START ready....");
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(work){
					try{
						AbstarctTask.this.run();
					}catch(Exception e){
						log.warn("run", e);
					}
					
					if(!work){
						break;
					}
					synchronized (lock) {
						try {
							lock.wait(timeout);
						} catch (InterruptedException e) {
							log.error("wait()", e);
						}
					}
					
				}

				log.info("thread [" + name + "] exit");
				
			}
		});
		if(name != null){
			name += threadNumber ++ ;
			thread.setName(name);
		}
		thread.start();
		
	}
	
	public void stop(){

		log.info("STOP ready....");
		
		this.work = false;
		notifyTask();
		thread = null;
	}
	
	public void notifyTask(){
		synchronized (lock) {
			lock.notifyAll();
		}
	}
	public void setName(String name){
		this.name = name;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	
}
