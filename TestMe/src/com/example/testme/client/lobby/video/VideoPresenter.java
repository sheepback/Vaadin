package com.example.testme.client.lobby.video;


import java.util.List;
import java.util.Random;

import com.example.testme.client.Presenter;
import com.google.api.services.youtube.model.SearchResult;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;


/**
 * @author Alexander Thomas
 * @date 22.07.2016
 */
public class VideoPresenter extends CustomComponent implements Presenter {

	private static final long serialVersionUID = -5617905386293708260L;
	
	public interface Display {
		VideoView getDisplay();
	}
	
	Display display;
	
	public VideoPresenter(){
		Search search = new Search("Die Antwoord");
		List<SearchResult> list = search.searchVideo();
		Random r = new Random();
		String uri = list.get(r.nextInt(25)).toString().split("videoId\":\"")[1].split("\"")[0];
		uri = "https://www.youtube.com/v/"+uri;
		//String[] uri = "https://www.youtube.com/watch?v=7hCMV9MZM7k".split("watch\\?v=");
		Embedded e = new Embedded(null, new ExternalResource(uri));
		e.setMimeType("application/x-shockwave-flash");
		e.setParameter("allowFullScreen", "true");
		this.display = new VideoView(e, uri);
		bind();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bind() {		
	}
	
	public Display getVideoView() {
		return this.display;
	}

}
