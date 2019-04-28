package com.presentation.loginUI;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class LogOutButton extends UI{

	@Override
	protected void init(VaadinRequest request) {
		System.out.println("Is autenticated: " + AuthService.isAuthenticated());
		System.out.println("Is remembered user: " + AuthService.isRememberedUser());
		Label label = new Label("User's email: " + AuthService.getAuthenticatedEmail());
		Label label2 = new Label("User's nickName: " + AuthService.getUserNickName());
		Button logOutBtn = new Button("LogOut");
		logOutBtn.setId("logOutBtn");
		logOutBtn.addClickListener(event -> {
			AuthService.logout();
		});
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.addComponent(label);
		vLayout.addComponent(label2);
		vLayout.addComponent(logOutBtn);
		
		this.setContent(vLayout);
	}

}
