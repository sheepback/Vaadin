package com.example.testme.client.lobby.chat;

import com.example.testme.client.View;
import com.example.testme.client.lobby.chat.ChatPresenter.Display;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * @author Alexander Thomas
 * @date 16:06:35 14.01.2016
 */
public class ChatView implements Display, View{

	VerticalLayout viewLayout;
	TextArea lab;
	TextField tb;
	Button sendButton;
	
	public ChatView(){
		lab = new TextArea("ChatMessages:");
		
		tb = new TextField();
		sendButton = new Button("Send");
		sendButton.setClickShortcut(KeyCode.ENTER);
		
		HorizontalLayout hl = new HorizontalLayout(tb, sendButton);
		
		VerticalLayout fields = new VerticalLayout(lab, hl);
		fields.setSpacing(true);
		fields.setMargin(new MarginInfo(true, true, true, false));
		fields.setSizeUndefined();
		
		viewLayout = new VerticalLayout(fields);
		viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
		viewLayout.setStyleName(Reindeer.LAYOUT_BLACK);
		viewLayout.setSizeFull();
	}

	@Override
	public ChatView getDisplay() {
		return this;
	}
	
	public VerticalLayout getViewLayout(){
		return this.viewLayout;
	}
	
	public Button getSendButton(){
		return sendButton;
	}
	
	public TextField getTextField(){
		return tb;
	}
	
	public TextArea getTextArea(){
		return lab;
	}
}
