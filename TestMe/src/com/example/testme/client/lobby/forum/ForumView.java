package com.example.testme.client.lobby.forum;

import java.util.ArrayList;
import java.util.List;

import com.example.testme.client.View;
import com.example.testme.client.lobby.forum.ForumPresenter.Display;
import com.example.testme.client.lobby.forum.game.Field;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * @author Alexander Thomas
 * @date 07.02.2016
 */
public class ForumView implements Display, View{
	
	List<Field> fields = new ArrayList<Field>();
	
	VerticalLayout viewLayout;
	
	HorizontalLayout hz = new HorizontalLayout();
			
	public ForumView(){
		
		for (int i=0;i<10;i++) {
			fields.add(new Field("Feld "+i));
			hz.addComponent(fields.get(i));
			hz.setComponentAlignment(fields.get(i), Alignment.MIDDLE_CENTER);
		}
		viewLayout = new VerticalLayout(hz);
		viewLayout.setComponentAlignment(hz, Alignment.MIDDLE_CENTER);
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
	
	public List<Field> getFields(){
		return fields;
	}
}
