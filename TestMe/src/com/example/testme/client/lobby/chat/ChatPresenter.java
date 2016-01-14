package com.example.testme.client.lobby.chat;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.testme.client.Presenter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * @author Alexander Thomas
 * @date 16:06:07 14.01.2016
 */
@SuppressWarnings("serial")
public class ChatPresenter extends CustomComponent implements Presenter{
	
	public interface Display{
		ChatView getDisplay();
	}
	
	Display display;
	String username;
	Logger logger = Logger.getLogger("ChatPresenter");

	public ChatPresenter(){
		this.display = new ChatView();
		bind();
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}

	@Override
	public void bind() {
	
	}
	
	public Display getChatView(){
		return (ChatView)this.display;
	}

}
