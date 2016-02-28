package com.example.testme.client.lobby.forum;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.testme.client.View;
import com.example.testme.client.lobby.forum.ForumPresenter.Display;
import com.example.testme.client.lobby.forum.game.Field;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * @author Alexander Thomas
 * @date 07.02.2016
 */
public class ForumView implements Display, View {

	List<Field> fields = new ArrayList<Field>();

	List<HorizontalLayout> hz = new ArrayList<HorizontalLayout>();

	VerticalLayout viewLayout;
	
	HorizontalLayout statusbar;
	
	Label money;

	Logger logger = Logger.getLogger("ForumView");

	public ForumView() {
		money = new Label("10");
		statusbar = new HorizontalLayout(money);
		
		viewLayout = new VerticalLayout(statusbar);
		for (int j = 0; j < 3; j++) {
			hz.add(new HorizontalLayout());
			viewLayout.addComponent(hz.get(j));
			viewLayout.setComponentAlignment(hz.get(j), Alignment.MIDDLE_CENTER);
		}
		for (int i = 0; i < 12; i++) {
			fields.add(new Field(""));
			fields.get(i).setIcon(new ThemeResource("icons/gamepic.png"));
			if (i < 4) {
				hz.get(0).addComponent(fields.get(i));
				hz.get(0).setComponentAlignment(fields.get(i),Alignment.MIDDLE_CENTER);
			} else if (i < 8) {
				hz.get(1).addComponent(fields.get(i));
				hz.get(1).setComponentAlignment(fields.get(i),Alignment.MIDDLE_CENTER);
			} else if (i < 12) {
				hz.get(2).addComponent(fields.get(i));
				hz.get(2).setComponentAlignment(fields.get(i),Alignment.MIDDLE_CENTER);
			}
		}
		viewLayout.setStyleName(Reindeer.LAYOUT_BLACK);
		viewLayout.setSizeFull();
	}

	@Override
	public ForumView getDisplay() {
		return this;
	}

	public VerticalLayout getViewLayout() {
		return this.viewLayout;
	}

	public List<Field> getFields() {
		return fields;
	}
	
	public void setMoney(String money){
		this.money.setValue(money);
	}
}
