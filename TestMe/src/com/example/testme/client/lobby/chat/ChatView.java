package com.example.testme.client.lobby.chat;

import java.util.ArrayList;
import java.util.List;

import com.example.testme.client.View;
import com.example.testme.client.lobby.chat.ChatPresenter.Display;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
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
	VerticalLayout userlist;
	Button sendButton;
	List<Label> labelList = new ArrayList<Label>();
	
	public ChatView(){
		lab = new TextArea("ChatMessages:");
		lab.setHeight("200px");
		lab.setWidth("600px");
		userlist = new VerticalLayout(new Label("Logged User: "));
		HorizontalLayout chat = new HorizontalLayout(lab, userlist);
		
		tb = new TextField();
		sendButton = new Button("Send");
		sendButton.setClickShortcut(KeyCode.ENTER);
		
		HorizontalLayout hl = new HorizontalLayout(tb, sendButton);
		
		VerticalLayout fields = new VerticalLayout(chat, hl);
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
	
	public VerticalLayout getUserlist(){
		return userlist;
	}
	
	public void addUser(String username){
		Label l = new Label(username);
		labelList.add(l);
		userlist.addComponent(l);
	}
	
	public void deleteUser(String username){
		for(Label n: labelList){
			if(n.getValue().equals(username)){
				userlist.removeComponent(n);
			}
		}
	}
	
	public void login(List<String> userlist){
		for(String n : userlist){
			Label l = new Label(n);
			labelList.add(l);
			this.userlist.addComponent(l);
		}
	}
	
	public void login(List<String> userlist, String username){
		for(String n : userlist){
			if(n.equals(username)){
				continue;
			}
			Label l = new Label(n);
			labelList.add(l);
			this.userlist.addComponent(l);
		}
	}
}
