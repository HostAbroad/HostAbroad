package com.presentation.loginUI;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class LogOutButton extends UI{

	@Override
	protected void init(VaadinRequest request) {
		//Window w = new Window();
		System.out.println("Printing session info: " + AuthService.isAuthenticated());
		System.out.println("Printing cookie info: " + AuthService.isRememberedUser());
		Label label = new Label("User's email: " + AuthService.getAuthenticatedEmail());
		Button logOutBtn = new Button("LogOut");
		logOutBtn.setId("logOutBtn");
		logOutBtn.addClickListener(event -> {
			AuthService.logout();
		});
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.addComponent(label);
		vLayout.addComponent(logOutBtn);
		
		//w.setContent(vLayout);
		this.setContent(vLayout);
	}

}
