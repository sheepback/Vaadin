package com.example.testme.client.registration;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.testme.client.Presenter;
import com.example.testme.client.login.LoginPresenter;
import com.example.testme.server.database.UserDAOImpl;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Button.ClickEvent;

/**
 * @author Alexander Thomas
 * @date 18.01.2016
 */
@SuppressWarnings("serial")
public class RegistrationPresenter extends CustomComponent implements Presenter{
	
	public interface Display {
		RegistrationView getDisplay();
	}
	
	public static final String NAME ="registration";
	
	private Display display;
	
	Logger logger = Logger.getLogger("RegistrationPresenter");
	
	public RegistrationPresenter(){
		this.display = new RegistrationView();
		setCompositionRoot(display.getDisplay().viewLayout);
		display.getDisplay().email.focus();
		setSizeFull();
		bind();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		getUI().getPage().setTitle("Registration");
	}

	@Override
	public void bind() {
		display.getDisplay().reg.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (!display.getDisplay().email.isValid() || !display.getDisplay().password.isValid()) {
					return;
				}
				String username = display.getDisplay().email.getValue();
				String password = display.getDisplay().password.getValue();
				UserDAOImpl user = new UserDAOImpl();
				if(user.create(username, password)==true){
					logger.log(Level.INFO, "created account for user: "+username);
					getUI().getNavigator().navigateTo(LoginPresenter.NAME);
				}
				else{
					getUI().setOverlayContainerLabel("Error - Try again");
				}
			}
		});
	}

}
