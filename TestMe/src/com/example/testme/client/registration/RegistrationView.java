package com.example.testme.client.registration;

import com.example.testme.client.View;
import com.example.testme.client.registration.RegistrationPresenter.Display;
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
 * @date 18.01.2016
 */
public class RegistrationView implements Display, View{
	
	VerticalLayout viewLayout;
	
	final TextField email;

	final PasswordField password;

	final Button reg;
	
	RegistrationView(){
		//
		//Explanations and Documentation in LoginView
		//
		email = new TextField("Your Email: ");
		email.setWidth("300px");
		email.setRequired(true);
		email.setInputPrompt("Your username (eg. joe@email.com)");
		email.addValidator(new EmailValidator(
				"Username must be an email address"));
		
		password = new PasswordField("Your Password: ");
		password.setWidth("300px");
		password.addValidator(new PasswordValidator());
		password.setRequired(true);
		password.setValue("");
		password.setNullRepresentation("");
		
		reg = new Button("Registrate");
		reg.setClickShortcut(KeyCode.ENTER);
		
		VerticalLayout fields = new VerticalLayout(email, password, reg);
		fields.setCaption("Please enter your Data for Registration.");
		fields.setSpacing(true);
		fields.setMargin(new MarginInfo(true, true, true, false));
		fields.setSizeUndefined();

		Label impr = new Label("Copyright by Sh33pb4ck, Version 0.1 2016");

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

	@Override
	public RegistrationView getDisplay() {
		return this;
	}

}
