package com.presentation.card;

import java.io.File;
import java.util.ArrayList;

import com.business.transfers.TLikes;
import com.business.transfers.TMatches;
import com.business.transfers.TUser;
import com.presentation.commands.CommandEnum.Commands;
import com.presentation.commands.Pair;
import com.presentation.controller.Controller;
import com.presentation.loginUI.AuthService;

import org.vaadin.teemu.ratingstars.RatingStars;


import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;


public class Card extends Panel{
	

	Button like;
	Button acceptLikes;
	Button declineLikes;
	
	public Card(TUser tUser) {

	private RatingStars stars;
	
	public Card(String nickname, String description, Double rate, Boolean enabled) {
		stars = new RatingStars();
		stars.setValue(rate);
		stars.setReadOnly(enabled);

		//This is the horizontalLayout. It's used to locate the 2 inner VLayouts.
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		
		//Locating the inner layouts
		horizontalLayout.addComponent(createLeftPartOfCard(tUser.getNickname()));
		horizontalLayout.addComponent(createRightPartOfCard(tUser));
		
		
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
		Label nickname = new Label("<b> &nbsp;" + nick + "</b>");
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
		imageNickNameLayout.setComponentAlignment(userIcon, Alignment.MIDDLE_CENTER);
		Label nicknameLabel = this.createNicknameLabel(nickname);
		imageNickNameLayout.addComponent(nicknameLabel);
		imageNickNameLayout.setComponentAlignment(nicknameLabel, Alignment.MIDDLE_CENTER);
		imageNickNameLayout.setMargin(false);
		
		return imageNickNameLayout;
	}
	
	

	/** private VerticalLayout createRightPartOfCard(String description) 
	 *  Creates inner layout for the right part of the panel(containing user description)
	 * */
	private VerticalLayout createRightPartOfCard(TUser tUser) {
		
		VerticalLayout descriptionLayout = new VerticalLayout();
		//Creates the textArea
		TextArea area = new TextArea();
		area.setStyleName("borderless");
		area.setSizeFull();
		area.setReadOnly(true);
		area.setWordWrap(true);
		// Put the content in it
		if(tUser.getDescription() != null)
			area.setValue(tUser.getDescription());
		else
			area.setValue("");
		
		like = new Button();
		like.setStyleName("card-like-button");
		like.setIcon(FontAwesome.HEART);
		like.addClickListener(event -> {
			
			TLikes sendLike = new TLikes(AuthService.getUserNickName(), tUser.getNickname());
			Pair<Integer,Object> result = Controller.getInstance().action(Commands.CommandSendLike, sendLike);
			if (result.getLeft() == 1) {

				Notification.show("Like Sended!", Notification.Type.HUMANIZED_MESSAGE);

			} else {

				Notification.show("We couldnt send your like", Notification.Type.ERROR_MESSAGE);
			}
		});
		
		acceptLikes = new Button();
		acceptLikes.setStyleName("card-acceptLike-button");
		acceptLikes.setIcon(FontAwesome.CHECK);
		acceptLikes.addClickListener(event -> {
			
			TMatches acceptLike = new TMatches(AuthService.getUserNickName(), tUser.getNickname());
			Pair<Integer,Object> result = Controller.getInstance().action(Commands.CommandAcceptLike, acceptLike);
			if (result.getLeft() == 1) {


				Notification notA = new Notification("Like Accepted!","Click My Profile to update your changes!", Notification.Type.HUMANIZED_MESSAGE);
				notA.setDelayMsec(5000);
				notA.show(Page.getCurrent());

			} else {

				Notification.show("We couldnt accept your like", Notification.Type.ERROR_MESSAGE);
			}
		});
		
		declineLikes = new Button();
		declineLikes.setStyleName("card-declineLike-button");
		declineLikes.setIcon(FontAwesome.CLOSE);
		declineLikes.addClickListener(event -> {
			
			TMatches declineLike = new TMatches(AuthService.getUserNickName(), tUser.getNickname());
			Pair<Integer,Object> result = Controller.getInstance().action(Commands.CommandDeclineLike, declineLike);
			if (result.getLeft() == 1) {

				Notification notD = new Notification("Like Declined!","Click My Profile to update your changes!", Notification.Type.HUMANIZED_MESSAGE);
				notD.setDelayMsec(5000);
				notD.show(Page.getCurrent());

			} else {

				Notification.show("We couldnt decline your like", Notification.Type.ERROR_MESSAGE);
			}
		});
		
		acceptLikes.setVisible(false);
		declineLikes.setVisible(false);
		
		descriptionLayout.setWidth("500px");
		descriptionLayout.addComponent(like);
		descriptionLayout.addComponent(acceptLikes);
		descriptionLayout.addComponent(declineLikes);
		descriptionLayout.addComponent(area);
		descriptionLayout.setComponentAlignment(like, Alignment.TOP_RIGHT);
		descriptionLayout.setComponentAlignment(acceptLikes, Alignment.TOP_RIGHT);
		descriptionLayout.setComponentAlignment(declineLikes, Alignment.TOP_RIGHT);
		
		
		stars.setCaption("Rating: ");
		stars.setMaxValue(5);
		
		descriptionLayout.addComponent(stars);
		descriptionLayout.setComponentAlignment(stars, Alignment.BOTTOM_RIGHT);
		
		return descriptionLayout;
	}
	

	public void setVisibleAcceptButton(boolean visible) {
		acceptLikes.setVisible(visible);
	}
	
	public void setVisibleDeclineButton(boolean visible) {
		declineLikes.setVisible(visible);
	}
	
	public void setVisibleLikeButton(boolean visible) {
		like.setVisible(visible);
	}

}
