package com.example.testme.client.lobby;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.testme.client.Presenter;
import com.example.testme.client.lobby.chat.ChatPresenter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Alexander Thomas
 * @date 23:31:46 07.01.2016
 */
@SuppressWarnings("serial")
public class LobbyPresenter extends CustomComponent implements Presenter {
	
	public interface Display{
		LobbyView getDisplay();
	}

	public static final String NAME = "";
	
	private Display display;
	
	private ChatPresenter cp;
	
	Logger logger = Logger.getLogger("LobbyPresenter");

	public LobbyPresenter() {
		this.display = new LobbyView();
		cp = new ChatPresenter();
		VerticalLayout viewLayout = new VerticalLayout( cp.getChatView().getDisplay().getViewLayout(), display.getDisplay().viewLayout);
		setCompositionRoot(viewLayout);
		setSizeFull();
		bind();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		getUI().getPage().setTitle("Lobby");

		// Get the user name from the session
		String username = String.valueOf(getSession().getAttribute("user"));

		// And show the username
		display.getDisplay().text.setValue("Hello " + username);
	}
	
	public void bind(){
		display.getDisplay().logout.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				// "Logout" the user
				logger.log(Level.INFO,"logge "+getSession().getAttribute("user")+" aus...");
				getSession().setAttribute("user", null);
				// Refresh this view, should redirect to login view
				getUI().getNavigator().navigateTo(NAME);
			}
		});
		
		cp.getChatView().getDisplay().getSendButton().addClickListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {

				cp.getChatView().getDisplay().getTextArea().setValue(cp.getChatView().getDisplay().getTextArea().getValue()+"\n"+getSession().getAttribute("user")+": "+cp.getChatView().getDisplay().getTextField().getValue());
				cp.getChatView().getDisplay().getTextField().clear();	
			}
			
		});
	}
}