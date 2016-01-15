package com.example.testme.server.http;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author Alexander Thomas
 * @date 15.01.2016
 */
public class HttpSessionCollector implements HttpSessionListener {
	private static final Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();
	Logger logger = Logger.getLogger("HttpSessionCollector");

	@Override
	public synchronized void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		sessions.put(session.getId(), session);
		logger.log(Level.INFO, "Found Session: "+session.getId()+"\nAdded Session, now: "+sessions.size());
	}

	@Override
	public synchronized void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		sessions.remove(session.getId(), session);
		logger.log(Level.INFO, "Removed Session, now: "+sessions.size());
	}

	public static HttpSession find(String sessionId) {
		return sessions.get(sessionId);
	}

	public static Map<String, HttpSession> getSessions() {
		return sessions;
	}
}