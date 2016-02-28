package com.example.testme.client.lobby.forum;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.testme.client.Presenter;
import com.example.testme.client.lobby.forum.game.Field;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;

/**
 * @author Alexander Thomas
 * @date 07.02.2016
 */
@SuppressWarnings("serial")
public class ForumPresenter extends CustomComponent implements Presenter {

	public interface Display {
		ForumView getDisplay();
	}

	Display display;


	File file;

	Logger logger = Logger.getLogger("ForumPresenter");

	public ForumPresenter() {
		this.display = new ForumView();
		setSizeFull();
		bind();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// Never entered cause LobbyPresenter is ParentView
	}

	@Override
	public void bind() {
		for(Field f : display.getDisplay().getFields()){
			f.addClickListener(new ClickListener(){

				@Override
				public void buttonClick(ClickEvent event) {
					display.getDisplay().setMoney(display.getDisplay().getMoney()+1);
				}
				
			}
			);
		}
	}

	public Display getForumView() {
		return this.display;
	}

}