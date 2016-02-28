package com.example.testme.client.lobby.forum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.testme.client.Presenter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FailedListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

/**
 * @author Alexander Thomas
 * @date 07.02.2016
 */
@SuppressWarnings("serial")
public class ForumPresenter extends CustomComponent implements Presenter  {
	
	public interface Display{
		ForumView getDisplay();
	}
	
	Display display;
	
	Upload upload;
	
	File file;
	
	Logger logger = Logger.getLogger("ForumPresenter");
	
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
		this.display.getDisplay().viewLayout.addComponent(upload);
		this.display.getDisplay().viewLayout.setComponentAlignment(upload, Alignment.MIDDLE_CENTER);
	}
	
	public Display getForumView(){
		return this.display;
	}
	
}