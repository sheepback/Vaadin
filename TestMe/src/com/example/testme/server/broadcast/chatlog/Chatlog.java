package com.example.testme.server.broadcast.chatlog;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
/**
 * @author Alexander Thomas
 * @date 15:05:40 10.03.2016
 */
public class Chatlog{
		
	public static void logChat(String message){
		new Thread(() ->{
		Date date = new Date(); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH)+1;
	    int day = cal.get(Calendar.DAY_OF_MONTH);
		final String PATH = "\\Chatlogs\\"+month+"-"+year;
			try {
				File dir = new File(PATH);
				dir.mkdirs();
				FileWriter file = new FileWriter(PATH+"\\"+day+"-"+month+"Chat.log", true);
				BufferedWriter bf = new BufferedWriter(file);
				bf.write(message);  
				bf.newLine();
				bf.close();
				file.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}).start();
	}

}
