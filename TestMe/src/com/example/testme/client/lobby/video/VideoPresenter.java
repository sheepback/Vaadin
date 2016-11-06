package com.example.testme.client.lobby.video;


import com.example.testme.client.Presenter;
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
		String[] uri = "https://www.youtube.com/watch?v=7hCMV9MZM7k".split("watch\\?v=");
		uri[0]= uri[0]+"v/"+uri[1];
		
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
