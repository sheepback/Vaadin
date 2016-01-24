package com.example.testme.client.lobby;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.testme.client.Presenter;
import com.example.testme.client.lobby.chat.ChatPresenter;
import com.example.testme.server.broadcast.Broadcaster;
import com.example.testme.server.broadcast.Broadcaster.BroadcastListener;
import com.vaadin.annotations.Push;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Alexander Thomas
 * @date 23:31:46 07.01.2016
 */
@Push(value = PushMode.AUTOMATIC, transport = Transport.WEBSOCKET)
@SuppressWarnings("serial")
public class LobbyPresenter extends CustomComponent implements Presenter, BroadcastListener {
	
	public interface Display{
		LobbyView getDisplay();
	}

	public static final String NAME = "";
	
	private Display display;
	
	private ChatPresenter cp;
	
	private String username;
	
	private VerticalLayout bindLayout;
	
	WebBrowser webBrowser;
	
	Logger logger = Logger.getLogger("LobbyPresenter");

	public LobbyPresenter() {
		this.display = new LobbyView();
		cp = new ChatPresenter();
		bindLayout = new VerticalLayout( cp.getChatView().getDisplay().getViewLayout(), display.getDisplay().viewLayout);
		bindLayout.setSizeFull();
		setCompositionRoot(bindLayout);
		setSizeFull();
		bind();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Broadcaster.register(this);
		getUI().getPage().setTitle("Lobby");
		// Get the user name from the session
		String[] user = getSession().getAttribute("user").toString().split("@");
		username = user[0];
		// And show the username and userInformation
		display.getDisplay().text.setValue("Hello " + username);
		webBrowser = Page.getCurrent().getWebBrowser();
		display.getDisplay().ip.setValue("IP: "+webBrowser.getAddress());
		display.getDisplay().clientInfo.setValue("Client Information: "+webBrowser.getBrowserApplication());
		display.getDisplay().isTouch.setValue("Touch Device: "+webBrowser.isTouchDevice());
		display.getDisplay().locale.setValue("Land: "+webBrowser.getLocale().toString());
		//Ask userlist for users who are logged in 
		cp.getChatView().getDisplay().login(Broadcaster.userlist);
	}
	
	public void bind(){
		display.getDisplay().logout.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// "Logout" the user
				logger.log(Level.INFO,"logge "+getSession().getAttribute("user")+" aus...");
				getSession().setAttribute("user", null);
				//Broadcast the user logged out
				Broadcaster.broadcast(getUI().getSession().getSession().getId(),username, false);
				// Refresh this view, should redirect to login view
				getUI().getNavigator().navigateTo(NAME);
			}
		});
		
			cp.getChatView().getDisplay().getSendButton().addClickListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				Broadcaster.broadcast(username+": "+cp.getChatView().getDisplay().getTextField().getValue()+"\n");
				cp.getChatView().getDisplay().getTextField().clear();
			}
		}); 
	}
	
	// Must also unregister when the UI expires    
    @Override
    public void detach() {
        Broadcaster.unregister(this);
        super.detach();
    }
    
    @Override
    public void receiveBroadcast(final String message) {
        // Must lock the session to execute logic safely
    		getUI().access(new Runnable() {
    			@Override
    			public void run() {
    				cp.getChatView().getDisplay().getTextArea().setValue(cp.getChatView().getDisplay().getTextArea().getValue()+message);
    			}
    		});
    	}

    @Override
    public void receiveBroadcast(final String message, final boolean logged) {
    	// When loggin in 
    	if(logged==true){
        // Must lock the session to execute logic safely
    		getUI().access(new Runnable() {
    			@Override
    			public void run() {
    				cp.getChatView().getDisplay().addUser(message);
    			}
    		});
    	}
    	// When logging out
    	else{
    		 // Must lock the session to execute logic safely
    		getUI().access(new Runnable() {
    			@Override
    			public void run() {
    				cp.getChatView().getDisplay().deleteUser(message);
    			}
    		});
    	}
    }
}