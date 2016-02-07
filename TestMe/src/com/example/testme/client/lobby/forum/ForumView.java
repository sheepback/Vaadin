package com.example.testme.client.lobby.forum;

import com.example.testme.client.View;
import com.example.testme.client.lobby.forum.ForumPresenter.Display;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * @author Alexander Thomas
 * @date 07.02.2016
 */
public class ForumView implements Display, View{
	
	VerticalLayout viewLayout;
	
	TextArea ta;
	
	public ForumView(){
		ta = new TextArea();
		viewLayout = new VerticalLayout(ta);
		viewLayout.setComponentAlignment(ta, Alignment.MIDDLE_CENTER);
		viewLayout.setStyleName(Reindeer.LAYOUT_BLACK);
		viewLayout.setSizeFull();
	}
	
	@Override
	public ForumView getDisplay() {
		return this;
	}
	
	public VerticalLayout getViewLayout(){
		return this.viewLayout;
	}
}
