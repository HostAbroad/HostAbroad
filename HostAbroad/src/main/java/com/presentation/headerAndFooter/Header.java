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
	
	static boolean logedIn = false;
	
	public Header() {
		HorizontalLayout header = new HorizontalLayout();

		header.setStyleName("header-color-blue");

		header.setWidth("100%");
		header.setMargin(false);
		header.setHeight(60, Unit.PIXELS);
		header.setSpacing(false);
		Label title = new Label("Host Abroad");
		/*
		 * Image logo = this.loadImage("logo.png"); logo.setWidth(80, Unit.PIXELS);
		 */
		title.setStyleName("title-label");
		header.addComponent(title);
		header.setComponentAlignment(title, Alignment.TOP_LEFT);


		GridLayout menu = new GridLayout(5, 1);
		
		if(logedIn) {
			logedMenu(menu);
		}
		else {
			notLogedMenu(menu);
		}
		

		header.addComponent(menu);
		header.setComponentAlignment(menu, Alignment.MIDDLE_RIGHT);
		this.setContent(header);

	}

	private void logedMenu(GridLayout menu) {
		Button home = new Button("Home");
		home.setStyleName("borderless-button");
		home.setStyleName("v-button v-widget borderless-colored v-button-borderless-colored");
		home.addClickListener(event -> {
			Page.getCurrent().setLocation("HostAbroad");
		});
		menu.addComponent(home);

		Button profile = new Button("My Profile");
		profile.setStyleName("borderless-button");
		profile.setStyleName("v-button v-widget borderless-colored v-button-borderless-colored");
		profile.addClickListener(event -> {
			Page.getCurrent().setLocation("my_profile");
		});
		menu.addComponent(profile);

		Button search = new Button("Search");
		search.setStyleName("borderless-button");
		search.setStyleName("v-button v-widget borderless-colored v-button-borderless-colored");
		search.addClickListener(event -> {
			Page.getCurrent().setLocation("search");
			});
		menu.addComponent(search);

		Button contact = new Button("Contact us");
		contact.setStyleName("borderless-button");
		contact.setStyleName("v-button v-widget borderless-colored v-button-borderless-colored");

		contact.addClickListener(event -> {
			JavaScript.getCurrent().execute("alert('Send us an email to hostabroad@outlook.es')");
		});
		menu.addComponent(contact);
		
		Button logout = new Button("Log out");
		logout.setStyleName("borderless-button");
		logout.setStyleName("v-button v-widget borderless-colored v-button-borderless-colored");
		logout.addClickListener(event -> {
			setLoged(false);
			Page.getCurrent().setLocation("HostAbroad");
		});
		menu.addComponent(logout);
	}
	
	private void notLogedMenu(GridLayout menu) {
		Button home = new Button("Home");
		home.setStyleName("borderless-button");
		home.setStyleName("v-button v-widget borderless-colored v-button-borderless-colored");
		home.addClickListener(event -> {
			Page.getCurrent().setLocation("HostAbroad");
		});
		menu.addComponent(home);

		Button search = new Button("Search");
		search.setStyleName("borderless-button");
		search.setStyleName("v-button v-widget borderless-colored v-button-borderless-colored");
		search.addClickListener(event -> {
			Page.getCurrent().setLocation("search");
			});
		menu.addComponent(search);

		Button contact = new Button("Contact us");
		contact.setStyleName("borderless-button");
		contact.setStyleName("v-button v-widget borderless-colored v-button-borderless-colored");

		contact.addClickListener(event -> {
			JavaScript.getCurrent().execute("alert('Send us an email to hostabroad@outlook.es')");
		});
		menu.addComponent(contact);
		
		Button register = new Button("Register");
		register.setStyleName("borderless-button");
		register.setStyleName("v-button v-widget borderless-colored v-button-borderless-colored");
		register.addClickListener(event -> {
			logedIn = false;
			Page.getCurrent().setLocation("register");
		});
		menu.addComponent(register);
		
		Button login = new Button("Log in");
		login.setStyleName("borderless-button");
		login.setStyleName("v-button v-widget borderless-colored v-button-borderless-colored");
		login.addClickListener(event -> {
			Page.getCurrent().setLocation("login");
		});
		menu.addComponent(login);
	}
	
	public static void setLoged(boolean state) {
		logedIn = state;
	}
	
	public static boolean getLoged() {
		return logedIn;
	}

}
