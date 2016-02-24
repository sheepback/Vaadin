package com.example.testme.client.lobby.forum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.example.testme.client.Presenter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;

/**
 * @author Alexander Thomas
 * @date 07.02.2016
 */
@SuppressWarnings("serial")
public class ForumPresenter extends CustomComponent implements Presenter, Receiver {
	
	public interface Display{
		ForumView getDisplay();
	}
	
	Display display;
	
	Upload upload;
	
	File file;
	
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
		upload = new Upload("Upload it here", this);
		this.display.getDisplay().viewLayout.addComponent(upload);
		this.display.getDisplay().viewLayout.setComponentAlignment(upload, Alignment.MIDDLE_CENTER);
	}
	
	public Display getForumView(){
		return this.display;
	}
	
	@Override
	public OutputStream receiveUpload(String filename, String mimeType) {
		// Create upload stream
        FileOutputStream fos = null; // Stream to write to
        try {
            // Open the file for writing.
        	file = new File("/VAADIN/themes/reindeer/icons/", filename);
            fos = new FileOutputStream(file);
        } 
        catch (final FileNotFoundException e) {
            new Notification("Could not open file<br/>",
                             e.getMessage(),
                             Notification.Type.ERROR_MESSAGE)
                .show(Page.getCurrent());
            return null;
        }
        return fos; // Return the output stream to write to
    }
	
	// This is called if the upload is finished.
    public void uploadSucceeded(SucceededEvent event) {
        // Log the upload on screen.
        this.display.getDisplay().getViewLayout().addComponent(new Label("File " + event.getFilename()
                + " of type '" + event.getMIMEType()
                + "' uploaded."));
    }

    // This is called if the upload fails.
    public void uploadFailed(FailedEvent event) {
        // Log the failure on screen.
    	this.display.getDisplay().getViewLayout().addComponent(new Label("Uploading "
                + event.getFilename() + " of type '"
                + event.getMIMEType() + "' failed."));
    }
}