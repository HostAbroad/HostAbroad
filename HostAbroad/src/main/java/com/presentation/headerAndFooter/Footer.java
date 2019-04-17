package com.presentation.headerAndFooter;

import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

@Theme("mytheme")
public class Footer extends Panel {

	public Footer() {
		GridLayout footer = new GridLayout(3, 1);
		
		GridLayout social = new GridLayout(4, 1);
		//social.setSpacing(true);
		
		footer.setStyleName("footer-color-gray");
		footer.setWidth("100%");
		footer.setHeight(60, Unit.PIXELS);
		//footer.setMargin(true);
		//footer.setSpacing(true);
		Label title = new Label("Host Abroad ©");
		title.setStyleName("title-label");
		footer.addComponent(title);
		//footer.setComponentAlignment(title, Alignment.TOP_LEFT);
		
		Button twitter = new Button();
		twitter.setIcon(FontAwesome.TWITTER);
		twitter.setCaption("Twitter");
		twitter.setStyleName("button-social");
		social.addComponent(twitter);
		
		Button facebook = new Button();
		facebook.setIcon(FontAwesome.FACEBOOK);
		facebook.setCaption("Facebook");
		facebook.setStyleName("button-social");
		social.addComponent(facebook);
		
		Button instagram = new Button();
		instagram.setIcon(FontAwesome.INSTAGRAM);
		instagram.setCaption("Instagram");
		instagram.setStyleName("button-social");
		social.addComponent(instagram);
		
		Button login = new Button();
		login.setCaption("Log in/out");
		login.addClickListener(event->{
			if(Header.getLoged()) {
				Header.setLoged(false);
			}
			else {
				Header.setLoged(true);
			}
			Page.getCurrent().setLocation("HostAbroad");
		});
		social.addComponent(login);
		
		footer.addComponent(social);
		footer.setComponentAlignment(social, Alignment.MIDDLE_CENTER);
		
		this.setContent(footer);
	}
	
}
