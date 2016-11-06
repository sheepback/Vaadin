package com.example.testme.client.lobby.video;


import java.util.List;
import java.util.Random;

import com.example.testme.client.Presenter;
import com.google.api.services.youtube.model.SearchResult;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;


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
		Search search = new Search();
		List<SearchResult> list = search.searchVideo();
		Random r = new Random();
		String uri = list.get(r.nextInt(25)).toString();
		uri = "https://www.youtube.com/v/"+uri;
		//String[] uri = "https://www.youtube.com/watch?v=7hCMV9MZM7k".split("watch\\?v=");		
		this.display = new VideoView(uri);
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
