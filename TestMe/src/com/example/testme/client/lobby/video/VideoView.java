package com.example.testme.client.lobby.video;

import com.example.testme.client.View;
import com.example.testme.client.lobby.video.VideoPresenter.Display;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * @author Alexander Thomas
 * @date 22.07.2016
 */
public class VideoView implements Display, View {

	VerticalLayout viewLayout;

	public VideoView(Embedded e, String uri) {
		viewLayout = new VerticalLayout();
		e.setWidth("820px");
		e.setHeight("665px");

		viewLayout.addComponent(e); // Add the component to the window or
									// layout.
		viewLayout.setStyleName(Reindeer.LAYOUT_BLACK);
		viewLayout.setComponentAlignment(e, Alignment.MIDDLE_CENTER);
		viewLayout.setSizeFull();
	}

	@Override
	public VideoView getDisplay() {
		return this;
	}

	public VerticalLayout getViewLayout() {
		return this.viewLayout;
	}

}
