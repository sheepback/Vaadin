package com.example.testme.client.lobby;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.shared.User;
import com.example.testme.client.Presenter;
import com.example.testme.client.lobby.chat.ChatPresenter;
import com.example.testme.client.lobby.forum.ForumPresenter;
import com.example.testme.server.broadcast.Broadcaster;
import com.example.testme.server.broadcast.Broadcaster.BroadcastListener;
import com.vaadin.annotations.Push;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;

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

	// One NAME has to be empty for the Navigator
	public static final String NAME = "";
	
	private final String WHITESPACE ="\\s+";
	
	private final String[] HELP = new String []{"/h", "/help"}, WHISPER = new String []{"@", "/w "};
	
	private Display display;
	
	private ChatPresenter cp;
	
	private ForumPresenter fp;
	
	private String username;
	
	private TabSheet tabsheet;
	
	private User u;
		
	WebBrowser webBrowser;
	
	final Logger logger = Logger.getLogger("LobbyPresenter");

	public LobbyPresenter() {
		this.display = new LobbyView();
		cp = new ChatPresenter();
		fp = new ForumPresenter();
	
		//add Presenter to TabSheet
		tabsheet = new TabSheet();
		tabsheet.addTab(display.getDisplay().viewLayout, "Lobby");
		tabsheet.addTab(cp.getChatView().getDisplay().getViewLayout(),"Chat");
		tabsheet.addTab(fp.getForumView().getDisplay().getViewLayout(), "Forum");
		tabsheet.addTab(display.getDisplay().logout, "Logout");
		tabsheet.setSizeFull();
		setCompositionRoot(tabsheet);
		setSizeFull();
		bind();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		getUI().getPage().setTitle("Lobby");
		u = (User) getSession().getAttribute("user");
		// Get the user name from the session
		String[] user = u.getUsername().split("@");
		username = user[0];
		Broadcaster.register(this, username);
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
		//LOGOUT through listener
		tabsheet.addSelectedTabChangeListener(new SelectedTabChangeListener() {
			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				if(event.getTabSheet().getSelectedTab().equals(display.getDisplay().logout)){
				// "Logout" the user
				logger.log(Level.INFO,"logge "+u.getUsername()+" aus...");
				getSession().setAttribute("user", null);
				//Broadcast the user logged out
				Broadcaster.broadcast(getUI().getSession().getSession().getId(),username, false);
				// Refresh this view, should redirect to login view
				getUI().getNavigator().navigateTo(NAME);
				}
			}
		});
		
		//CHATPresenter
		cp.getChatView().getDisplay().getSendButton().addClickListener(new ClickListener(){
			//Send Messages
			/*
			 * Cases:
			 * 1. Help "/h" or "/help"
			 * 2. WhisperMode with "@"
			 * 3. WhisperMode with "/w "
			 * 4. Normal message
			 */
			@Override
			public void buttonClick(ClickEvent event) {
				String value = cp.getChatView().getDisplay().getTextField().getValue();
				//1. Help "/h" or "/help"
				if(value.equals(HELP[0])||value.equals(HELP[1])){
					cp.getChatView().getDisplay().getTextArea().setValue(cp.getChatView().getDisplay().getTextArea().getValue()
							+ "################### Commands ####################\n\n"
							+ "List of commands: \"/h\" or \"/help\"\n"
							+ "Private message to another client: \"@username message\" or \"/w username message \"\n"
							+"\n");
					cp.getChatView().getDisplay().getTextField().clear();
				}
				// 2. WhisperMode with "@"
				else if(value.startsWith(WHISPER[0])){
					String[] splitter = value.split(WHISPER[0]);
					splitter=splitter[1].split(WHITESPACE);
					Broadcaster.whisper(username+": "+value+"\n", splitter[0], username);
					cp.getChatView().getDisplay().getTextField().clear();
				}
				// 3. WhisperMode with "/w " (there is a blank behind the w!)
				else if(value.startsWith(WHISPER[1])){
					String[] splitter = value.split(WHISPER[1]);
					splitter=splitter[1].split(WHITESPACE);
					Broadcaster.whisper(username+": "+value+"\n", splitter[0], username);
					cp.getChatView().getDisplay().getTextField().clear();
				}
				// 4. Normal message
				else if(!(value.equals(""))){
					Broadcaster.broadcast(username+": "+value+"\n");
					cp.getChatView().getDisplay().getTextField().clear();
				}
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