package com.presentation.myPlacesUI;


import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.slider.SliderOrientation;
import com.vaadin.ui.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyPlacesUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		HorizontalLayout mainLayout = new HorizontalLayout();

		VerticalLayout secondaryLayout = new VerticalLayout();
		



		/*
		Upload upload = new Upload("Add an image", configureReciever());
		upload.setImmediateMode(false);
		upload.setButtonCaption("Upload");
		*/
		
		TextArea description = new TextArea("Description");
		description.setWordWrap(false);
		description.setId("PlaceDescription");
		secondaryLayout.addComponent(description);

        // Create a horizontal slider
        Slider duration = new Slider("Duration: ", 0, 7);
        duration.setOrientation(SliderOrientation.HORIZONTAL);
        duration.setWidth("200px");
        Label dias = new Label("Days");
        duration.addValueChangeListener(event -> {
            if(duration.getValue() == 1.0) {
                dias.setValue("1 days");
            }
            else if(duration.getValue() == 2.0) {
                dias.setValue("2 days");
            }
            else if(duration.getValue() == 3.0) {
                dias.setValue("3 days");
            }
            else if(duration.getValue() == 4.0) {
                dias.setValue("4 days");
            }
            else if(duration.getValue() == 5.0) {
                dias.setValue("5 days");
            }
            else if(duration.getValue() == 6.0) {
                dias.setValue("6 days");
            }
            else if(duration.getValue() == 7.0) {
                dias.setValue("1 Week");
            }
        });
        secondaryLayout.addComponent(dias);
        secondaryLayout.setComponentAlignment(dias,Alignment.TOP_CENTER);
        secondaryLayout.addComponent(duration);
        secondaryLayout.setComponentAlignment(duration,Alignment.TOP_CENTER);



		ComboBox<String> country = new ComboBox<>("I live with");
		country.setItems("My house", "Sahara", "Bulgaria", "Mars");
		country.setId("PlaceCountry");
		secondaryLayout.addComponent(country);

		Button save = new Button("Save");
		save.addClickListener(event->{
				Notification notif = new Notification( "In construction.");
				notif.setDelayMsec(2000);
				notif.setPosition(Position.MIDDLE_CENTER);
				notif.show(Page.getCurrent());
			
		});
		save.setId("PlaceSave");
		
		
		//mainLayout.addComponent(upload);
		secondaryLayout.addComponent(save);
		secondaryLayout.setComponentAlignment(save, Alignment.MIDDLE_CENTER);
		mainLayout.addComponent(secondaryLayout);
		this.setContent(mainLayout);
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

	private void initDuration(ComboBox<String> duration) {
		List<String> days = new ArrayList<>();
		for (int i = 1; i < 32; i++) {
			days.add(i + " days");
		}
		duration.setItems(days);
	}

}

