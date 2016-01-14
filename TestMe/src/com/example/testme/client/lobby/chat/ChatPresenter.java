package com.example.testme.client.lobby.chat;

import com.example.testme.client.Presenter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;


/**
 * @author Alexander Thomas
 * @date 16:06:07 14.01.2016
 */
@SuppressWarnings("serial")
public class ChatPresenter extends CustomComponent implements Presenter{
	
	public interface Display{
		ChatView getDisplay();
	}
	
	public final static String NAME = "ChatPresenter";
	
	Display display;
	
	String username;
	
	public ChatPresenter(){
		this.display = new ChatView();
		setSizeFull();
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
