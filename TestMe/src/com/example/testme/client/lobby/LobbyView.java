package com.example.testme.client.lobby;

import com.example.testme.client.lobby.LobbyPresenter.Display;
import com.example.testme.client.View;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * @author Alexander Thomas
 * @date 21:59:45 07.01.2016
 */
public class LobbyView implements Display, View {

	Label text;
	Label ip;
	Label clientInfo;
	Label isTouch;
	Label locale;
	TabSheet logout;
	Label impr;
	VerticalLayout viewLayout;

	public LobbyView() {
		text = new Label();
		ip = new Label();
		clientInfo = new Label();
		isTouch = new Label();
		locale = new Label();
		logout = new TabSheet();
		VerticalLayout fields = new VerticalLayout(text, ip, clientInfo, isTouch, locale);
		//fields.setComponentAlignment(logout, Alignment.MIDDLE_CENTER);
		fields.setSpacing(true);
		fields.setMargin(new MarginInfo(true, true, true, false));
		fields.setSizeUndefined();

		impr = new Label("Copyright by Sh33pb4ck, Version 0.1 2016");

		VerticalLayout impressum = new VerticalLayout(impr);
		impressum.setSpacing(true);
		impressum.setMargin(new MarginInfo(true, true, true, false));
		impressum.setSizeUndefined();

		viewLayout = new VerticalLayout(fields, impressum);
		viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
		viewLayout.setComponentAlignment(impressum, Alignment.BOTTOM_CENTER);
		viewLayout.setStyleName(Reindeer.LAYOUT_BLACK);
		viewLayout.setSizeFull();
	}

	public LobbyView getDisplay() {
		return this;
	}
}