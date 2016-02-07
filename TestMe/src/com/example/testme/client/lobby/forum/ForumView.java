package com.example.testme.client.lobby.forum;

import com.example.testme.client.View;
import com.example.testme.client.lobby.forum.ForumPresenter.Display;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Alexander Thomas
 * @date 07.02.2016
 */
public class ForumView implements Display, View{
	
	VerticalLayout viewLayout;
	
	public ForumView(){
		viewLayout = new VerticalLayout();
	}
	
	@Override
	public ForumView getDisplay() {
		return this;
	}
	
	public VerticalLayout getViewLayout(){
		return this.viewLayout;
	}
}
