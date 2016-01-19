package com.example.testme.server.broadcast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

	private static LinkedList<BroadcastListener> listeners = new LinkedList<BroadcastListener>();

	public static synchronized void register(BroadcastListener listener) {
		listeners.add(listener);
	}

	public static synchronized void unregister(BroadcastListener listener) {
		listeners.remove(listener);
	}
	
	public static synchronized void broadcast(final String message) {
		for (final BroadcastListener listener : listeners)
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					listener.receiveBroadcast(message);
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
		for (final BroadcastListener listener : listeners)
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					listener.receiveBroadcast(message, logged);
				}
			});
	}
}