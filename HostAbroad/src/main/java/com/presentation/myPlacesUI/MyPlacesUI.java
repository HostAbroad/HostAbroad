package com.presentation.myPlacesUI;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;

public class MyPlacesUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		HorizontalLayout mainLayout = new HorizontalLayout();
		FormLayout placesForm = new FormLayout();
		
		Image pic = new Image();
		pic.setSource(new ExternalResource("https://cdn3.iconfinder.com/data/icons/musthave/256/Delete.png"));
		
		TextArea description = new TextArea("Description");
		description.setWordWrap(false);
		
		mainLayout.addComponent(pic);
		mainLayout.addComponent(placesForm);
		this.setContent(mainLayout);
	}

}
