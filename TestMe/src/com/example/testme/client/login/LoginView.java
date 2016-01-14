package com.example.testme.client.login;

import com.example.testme.client.View;
import com.example.testme.client.login.LoginPresenter.Display;
import com.example.testme.client.validator.PasswordValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * @author Alexander Thomas
 * @date 13:40:44 28.12.2015
 */

public class LoginView implements Display, View{

	final TextField user;

	final PasswordField password;

	final Button loginButton;

	final Label impr;

	final VerticalLayout viewLayout;


	public LoginView() {
		// Create the user input field
		user = new TextField("User:");
		user.setWidth("300px");
		user.setRequired(true);
		user.setInputPrompt("Your username (eg. joe@email.com)");
		user.addValidator(new EmailValidator(
				"Username must be an email address"));
		//user.setInvalidAllowed(false);

		// Create the password input field
		password = new PasswordField("Password:");
		password.setWidth("300px");
		password.addValidator(new PasswordValidator());
		password.setRequired(true);
		password.setValue("");
		password.setNullRepresentation("");

		// Create login button
		loginButton = new Button("Login");
		loginButton.setClickShortcut(KeyCode.ENTER);

		// Add both to a panel
		VerticalLayout fields = new VerticalLayout(user, password, loginButton);
		fields.setCaption("Please login to access the application.");
		fields.setSpacing(true);
		fields.setMargin(new MarginInfo(true, true, true, false));
		fields.setSizeUndefined();

		impr = new Label("Copyright by Sh33pb4ck, Version 0.1 2016");

		VerticalLayout impressum = new VerticalLayout(impr);
		impressum.setSpacing(true);
		impressum.setMargin(new MarginInfo(true, true, true, false));
		impressum.setSizeUndefined();
		
		// The view root layout
		viewLayout = new VerticalLayout(fields, impressum);
		viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
		viewLayout.setComponentAlignment(impressum, Alignment.BOTTOM_CENTER);
		viewLayout.setStyleName(Reindeer.LAYOUT_BLACK);
		viewLayout.setSizeFull();
	}

	@Override
	public LoginView getDisplay() {
		return this;
	}

}