package com.presentation.card;

import java.io.File;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;


public class Card extends Panel{
	public Card(String nickname, String description) {
		//This is the horizontalLayout. It's used to locate the 2 inner VLayouts.
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		
		//Locating the inner layouts
		horizontalLayout.addComponent(createLeftPartOfCard(nickname));
		horizontalLayout.addComponent(createRightPartOfCard(description));
		
		this.setContent(horizontalLayout);
		this.setSizeUndefined();
	}
	
	
	private Image loadImage(String url) {
		//reading the image
		//-----------------------------------
		String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();

		//Image as a file resource
		FileResource resource = new FileResource(new File(basepath +
		                      "/WEB-INF/images/" + url));
		
		//Show the image 
		Image image = new Image("", resource);
		//-----------------------------------
		
		return image;
	}
	
	private Label createNicknameLabel(String nick) {
		Label nickname = new Label("<b> &emsp;&ensp;" + nick + "</b>");
		nickname.setContentMode(ContentMode.HTML);
		
		return nickname;
	}

	/** private VerticalLayout createLeftPartOfCard(String nickname)
	 *  Creates inner layout for the left part of the panel(containing nickname and photo) 
	 * */
	private VerticalLayout createLeftPartOfCard(String nickname) {
		
		VerticalLayout imageNickNameLayout = new VerticalLayout();
		//later the loadImage will be called with the photo that correspond to the user
		Image userIcon = new Image();
		userIcon.setSource(new ExternalResource("https://raw.githubusercontent.com/evivar/images/master/cartas.jpg"));
		imageNickNameLayout.addComponent(userIcon);
		Label nicknameLabel = this.createNicknameLabel(nickname);
		imageNickNameLayout.addComponent(nicknameLabel);
		imageNickNameLayout.setComponentAlignment(nicknameLabel, Alignment.TOP_LEFT);
		imageNickNameLayout.setMargin(false);
		
		return imageNickNameLayout;
	}

	/** private VerticalLayout createRightPartOfCard(String description) 
	 *  Creates inner layout for the right part of the panel(containing user description)
	 * */
	private VerticalLayout createRightPartOfCard(String description) {
		
		VerticalLayout descriptionLayout = new VerticalLayout();
		//Creates the textArea
		TextArea area = new TextArea();
		area.setStyleName("borderless");
		area.setSizeFull();
		area.setReadOnly(true);
		area.setWordWrap(true);
		// Put the content in it
		area.setValue(description);
		
		VaadinIcons v = VaadinIcons.HEART;
		Button button = new Button("", v);
		descriptionLayout.setWidth("500px");
		descriptionLayout.addComponent(button);
		descriptionLayout.addComponent(area);
		descriptionLayout.setComponentAlignment(button, Alignment.TOP_RIGHT);
		
		
		return descriptionLayout;
	}

}
