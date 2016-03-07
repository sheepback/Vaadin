package com.example.testme.server.broadcast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Alexander Thomas
 * @date 17.01.2016
 */
@SuppressWarnings("serial")
public class Broadcaster implements Serializable {
	public static List<String> userid = new ArrayList<String>();
	public static List<String> userlist = new ArrayList<String>();
	static ExecutorService executorService = Executors.newSingleThreadExecutor();

	public interface BroadcastListener {
		void receiveBroadcast(String message);
		void receiveBroadcast(String message, boolean logged);
	}

	//private static LinkedList<BroadcastListener> listeners = new LinkedList<BroadcastListener>();
	private static Map<BroadcastListener, String> listeners = new HashMap<BroadcastListener, String>();


	public static synchronized void register(BroadcastListener listener, String username) {
		//listeners.add(listener);
		listeners.put(listener, username);
	}

	public static synchronized void unregister(BroadcastListener listener) {
		listeners.remove(listener);
	}
	
	public static synchronized void broadcast(final String message) {
		for (final BroadcastListener listener : listeners.keySet())
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					listener.receiveBroadcast(message);
				}
			});
	}
	
	public static synchronized void whisper(final String message, String username, String username2) {
		boolean sent = false;
		BroadcastListener l = null;
		for (final Entry<BroadcastListener, String> listener : listeners.entrySet()){
			if(listener.getValue().equals(username) || listener.getValue().equals(username2)){
				//save the user who sent the message to use it later on
				//for sending him info if the message was delivered
				if(listener.getValue().equals(username2)){
					l = listener.getKey();
				}
				//send the message
				executorService.execute(new Runnable() {
				@Override
				public void run() {
					listener.getKey().receiveBroadcast(message);
				}
			});
				//Is the User online? if yes the message can be send
				if(listener.getValue().equals(username)){
					sent=true;
				}
			}
		}
		final BroadcastListener li = l;
		//if the message couldnt be delivered, notify the user who sent the message
		if(sent == false)
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					li.receiveBroadcast("System: Message cannot be delivered! User not found!\n");
				}
			});
		
	}
	
	public static synchronized void broadcast(final String id, final String message, final boolean logged) {
		if(logged == true){
			userid.add(id);
			userlist.add(message);
		}
		else{
			for(int i=0;i<userid.size();i++){
				if(userid.get(i).equals(id)){
					userid.remove(i);
				}
			}
			for(int i=0;i<userlist.size();i++){
				if(userlist.get(i).equals(message)){
					userlist.remove(i);
				}
			}
		}
		for (final BroadcastListener listener : listeners.keySet())
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					listener.receiveBroadcast(message, logged);
				}
			});
	}
}