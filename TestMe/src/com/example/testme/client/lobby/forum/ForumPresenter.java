package com.example.testme.client.lobby.forum;

import com.example.testme.client.Presenter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;

/**
 * @author Alexander Thomas
 * @date 07.02.2016
 */
@SuppressWarnings("serial")
public class ForumPresenter extends CustomComponent implements Presenter {
	
	public interface Display{
		ForumView getDisplay();
	}
	
	Display display;
	
	public ForumPresenter(){
		this.display = new ForumView();
		setSizeFull();
		bind();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		//Never entered cause LobbyPresenter is ParentView
	}

	@Override
	public void bind() {

	}
	
	public Display getForumView(){
		return this.display;
	}
}