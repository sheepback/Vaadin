package com.example.testme.client;

import com.vaadin.annotations.Push;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.example.testme.client.lobby.LobbyPresenter;
import com.example.testme.client.lobby.chat.ChatPresenter;
import com.example.testme.client.login.LoginPresenter;

@SuppressWarnings("serial")
@Push
public class WebApp extends UI {

	@Override
	protected void init(VaadinRequest request) {

		getPage().setTitle("Welcome");
		//
		// Create a new instance of the navigator. The navigator will attach
		// itself automatically to this view.
		//
		new Navigator(this, this);

		//
		// The initial log view where the user can login to the application
		//
		getNavigator().addView(LoginPresenter.NAME, LoginPresenter.class);//

		//
		// Add the main view of the application
		//
		getNavigator().addView(LobbyPresenter.NAME, LobbyPresenter.class);
		
		//
		// Add Chat to the application
		//
		getNavigator().addView(ChatPresenter.NAME, ChatPresenter.class);
		//
		// We use a view change handler to ensure the user is always redirected
		// to the login view if the user is not logged in.
		//
		getNavigator().addViewChangeListener(new ViewChangeListener() {

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {

				// Check if a user has logged in
				boolean isLoggedIn = getSession().getAttribute("user") != null;
				boolean isLoginView = event.getNewView() instanceof LoginPresenter;

				if (!isLoggedIn && !isLoginView) {
					// Redirect to login view always if a user has not yet
					// logged in
					getNavigator().navigateTo(LoginPresenter.NAME);
					return false;

				} else if (isLoggedIn && isLoginView) {
					// If someone tries to access to login view while logged in,
					// then cancel
					return false;
				}

				return true;
			}

			@Override
			public void afterViewChange(ViewChangeEvent event) {

			}
		});
	}
}