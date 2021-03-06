package com.example.testme.server.http;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.example.testme.server.broadcast.Broadcaster;

/**
 * @author Alexander Thomas
 * @date 15.01.2016
 */
public class HttpSessionCollector implements HttpSessionListener {
	private static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();
	Logger logger = Logger.getLogger("HttpSessionCollector");

	@Override
	public synchronized void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		sessions.put(session.getId(), session);
		logger.log(Level.INFO, "Found Session: "+session.getId()
				+"\nSession Creation Time: "+ new Date(session.getCreationTime())
				+"\nSessions now: "+sessions.size());
	}

	@Override
	public synchronized void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		for(int i=0;i<Broadcaster.userlist.size();i++){
			if(Broadcaster.userid.get(i).equals(session.getId())){
				logger.log(Level.INFO, "Removed Session "+session.getId()+" "+Broadcaster.userlist.get(i));
				Broadcaster.broadcast(Broadcaster.userid.get(i),Broadcaster.userlist.get(i), false);
			}
		}
		sessions.remove(session.getId(),session);
		logger.log(Level.INFO, "Sessions now: "+sessions.size());
	}

	public static HttpSession find(String sessionId) {
		return sessions.get(sessionId);
	}

	public static Map<String, HttpSession> getSessions() {
		return sessions;
	}
}