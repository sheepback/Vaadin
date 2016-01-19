package com.example.testme.client.login;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.testme.client.Presenter;
import com.example.testme.client.lobby.LobbyPresenter;
import com.example.testme.client.registration.RegistrationPresenter;
import com.example.testme.server.broadcast.Broadcaster;
import com.example.testme.server.database.UserDAOImpl;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;

/**
 * @author Alexander Thomas
 * @date 00:29:41 08.01.2016
 */
@SuppressWarnings("serial")
public class LoginPresenter extends CustomComponent implements Presenter,
		Button.ClickListener {

	public interface Display {
		LoginView getDisplay();
	}

	public static final String NAME = "login";

	private Display display;
	
	private String usernameSession;
	
	Logger logger = Logger.getLogger("LoginPresenter");

	public LoginPresenter() {
		this.display = new LoginView();
		setCompositionRoot(display.getDisplay().viewLayout);
		display.getDisplay().user.focus();
		setSizeFull();
		bind();
	}

	public void enter(ViewChangeEvent event) {
		// focus the username field when user arrives to the login view
		getUI().getPage().setTitle("Login");
		// Get the user name from the session
	}

	@Override
	public void bind() {
		display.getDisplay().loginButton.addClickListener(this);
		
		display.getDisplay().regButton.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(RegistrationPresenter.NAME);
			}
			
		});
	}

	@Override
	public void buttonClick(ClickEvent event) {

		//
		// Validate the fields using the navigator. By using validors for the
		// fields we reduce the amount of queries we have to use to the database
		// for wrongly entered passwords
		//
		if (!display.getDisplay().user.isValid()
				|| !display.getDisplay().password.isValid()) {
			return;
		}
		String username = display.getDisplay().user.getValue();
		String password = display.getDisplay().password.getValue();

		//
		// Validate username and password with database here
		//

		UserDAOImpl user = new UserDAOImpl();
		if (user.login(username, password) == true) {

			// Store the current user in the service session
			getSession().setAttribute("user", username);
			logger.log(Level.INFO,"Logge "+username+" ein");
			// Navigate to main view
			String[] userSession = username.split("@");
			usernameSession = userSession[0];
			Broadcaster.broadcast(getUI().getSession().getSession().getId(),usernameSession, true);
			getUI().getNavigator().navigateTo(LobbyPresenter.NAME);

		} 
		else {
			// Wrong password clear the password field and refocuses it
			display.getDisplay().password.setValue(null);
			display.getDisplay().password.focus();
		}
	}
}
