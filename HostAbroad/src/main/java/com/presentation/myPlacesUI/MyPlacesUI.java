package com.presentation.myPlacesUI;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MyPlacesUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		HorizontalLayout mainLayout = new HorizontalLayout();

		VerticalLayout secondaryLayout = new VerticalLayout();
		
		GridLayout grid = new GridLayout(2, 2);
		grid.setSpacing(true);

		Image pic = new Image();
		pic.setSource(new ExternalResource("https://cdn3.iconfinder.com/data/icons/musthave/256/Delete.png"));

		TextArea description = new TextArea("Description");
		description.setWordWrap(false);
		description.setId("PlaceDescription");
		grid.addComponent(description, 0, 0);

		ComboBox<String> duration = new ComboBox<>("How long can I host");
		initDuration(duration);
		duration.setId("PlaceDuration");
		grid.addComponent(duration, 1, 0);

		ComboBox<String> country = new ComboBox<>("I live");
		country.setItems("My house", "Sahara", "Bulgaria", "Mars");
		country.setId("PlaceCountry");
		grid.addComponent(country, 0, 1);

		Button save = new Button("Save");
		save.addClickListener(event->{
				Notification notif = new Notification( "In construction.");
				notif.setDelayMsec(2000);
				notif.setPosition(Position.MIDDLE_CENTER);
				notif.show(Page.getCurrent());
			
		});
		save.setId("PlaceSave");
		
		
		mainLayout.addComponent(pic);
		secondaryLayout.addComponent(grid);
		secondaryLayout.addComponent(save);
		secondaryLayout.setComponentAlignment(save, Alignment.MIDDLE_CENTER);
		mainLayout.addComponent(secondaryLayout);
		this.setContent(mainLayout);
	}

	private void initDuration(ComboBox<String> duration) {
		List<String> days = new ArrayList<>();
		for (int i = 1; i < 32; i++) {
			days.add(i + " days");
		}
		duration.setItems(days);
	}

}
