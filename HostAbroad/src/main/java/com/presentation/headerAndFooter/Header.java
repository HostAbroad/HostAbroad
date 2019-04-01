package com.presentation.headerAndFooter;

import java.awt.Container;

import com.presentation.loginUI.HomePage;
import com.vaadin.annotations.Theme;
import com.vaadin.flow.component.Component;
import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

@Theme("mytheme")
public class Header extends Panel {
	
	public Header() {
		HorizontalLayout header = new HorizontalLayout();

		header.setStyleName("header-color-blue");

		header.setWidth("100%");
		header.setMargin(true);
		header.setSpacing(false);
		Label title = new Label("Host Abroad");
		/*
		 * Image logo = this.loadImage("logo.png"); logo.setWidth(80, Unit.PIXELS);
		 */
		title.setStyleName("title-label");
		header.addComponent(title);
		header.setComponentAlignment(title, Alignment.MIDDLE_LEFT);

		GridLayout menu = new GridLayout(4, 1);

		Button home = new Button("Home");
		home.setStyleName("borderless-button");
		home.setStyleName("v-button v-widget borderless v-button-borderless");
		home.addClickListener(event -> {
			Page.getCurrent().setLocation("HostAbroad");
		});
		menu.addComponent(home);

		Button profile = new Button("My Profile");
		profile.setStyleName("borderless-button");
		profile.setStyleName("v-button v-widget borderless v-button-borderless");
		profile.addClickListener(event -> {
			Page.getCurrent().setLocation("my_profile");
		});
		menu.addComponent(profile);

		Button search = new Button("Search");
		search.setStyleName("borderless-button");
		search.setStyleName("v-button v-widget borderless v-button-borderless");
		search.addClickListener(event -> {
			Page.getCurrent().setLocation("search");
			});
		menu.addComponent(search);

		Button contact = new Button("Contact us");
		contact.setStyleName("borderless-button");
		contact.setStyleName("v-button v-widget borderless v-button-borderless");

		contact.addClickListener(event -> {
			JavaScript.getCurrent().execute("alert('Send us an email to hostabroad@outlook.es')");
		});
		menu.addComponent(contact);

		header.addComponent(menu);
		header.setComponentAlignment(menu, Alignment.MIDDLE_RIGHT);
		this.setContent(header);

	}


}
