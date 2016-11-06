package com.example.testme.client.lobby.forum;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.testme.client.Presenter;
import com.example.testme.client.lobby.forum.game.*;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
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
		
		Monster m = new Monster("Kobold", 20, 2, 8, 2);
		Fighter f = new Fighter("Miriam", 25, 3, 7, 2);
		Battle b = new Battle(m, f);
		while (m.getLevel() < 8 && f.getLevel() < 8) {
			if(f.getLevel()>m.getLevel()){
				m.setLevel();
			}
			b.fightround();
		}
		logger.log(Level.INFO, m.toString());
		logger.log(Level.INFO,f.toString());
	}

	public Display getForumView() {
		return this.display;
	}

}