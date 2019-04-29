package com.presentation.myPlacesUI;


import com.business.enums.FamilyUnit;
import com.business.transfers.TPlace;
import com.presentation.commands.CommandEnum.Commands;
import com.presentation.controller.Controller;
import com.presentation.headerAndFooter.Footer;
import com.presentation.headerAndFooter.Header;
import com.presentation.loginUI.AuthService;
import com.vaadin.annotations.Theme;
import com.vaadin.server.*;
import com.vaadin.shared.ui.slider.SliderOrientation;
import com.vaadin.ui.*;
import org.vaadin.easyuploads.UploadField;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Theme("mytheme")
public class MyPlacesUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		
		GridLayout mainGrid = new GridLayout(1, 2);
		mainGrid.setSpacing(true);
		mainGrid.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		GridLayout sections = new GridLayout(2, 1);
		sections.setSpacing(true);
		sections.setMargin(true);
		sections.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		VerticalLayout image = new VerticalLayout();
		image.setSpacing(true);
		image.setMargin(true);
		image.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		GridLayout fields = new GridLayout(2, 2);
		fields.setSpacing(true);
		fields.setMargin(true);
		fields.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

		Image placeImg = new Image();
		placeImg.setSource(new ExternalResource("https://raw.githubusercontent.com/OmegaSkyres/images/master/null.png"));
		placeImg.setId("PlaceImage");

		UploadField uploadField = new UploadField();
		uploadField.setId("uploadField");
		uploadField.setClearButtonVisible(false);
		uploadField.setButtonCaption("Select image");
		
		Button changeImg = new Button("Change image");
		changeImg.setIcon(FontAwesome.UPLOAD);
		changeImg.addClickListener(event -> {
			Notification.show("File: " + uploadField.getLastFileName());
		});
		changeImg.setId("PlaceChangeImg");
		
		image.addComponent(placeImg);
		image.addComponent(uploadField);
		
		sections.addComponent(image, 0, 0);
		
		TextArea description = new TextArea("Description");
		description.setWordWrap(false);
		description.setHeight("90%");
		description.setWidth("150%");
		description.setId("PlaceDescription");

		TextField address = new TextField("Address");
		address.setId("PlaceAddress");
		address.setWidth("150%");



        // Create a horizontal slider
        Slider duration = new Slider("Maximum duration of stay: ", 0, 4);
        duration.setId("slider");
        duration.setOrientation(SliderOrientation.HORIZONTAL);
        duration.setWidth("200px");
        Label days = new Label("Days");
        duration.addValueChangeListener(event -> {
            if(duration.getValue() == 0.0) {
					days.setValue("Days");
				}
				else if(duration.getValue() == 1.0) {
					days.setValue("1 Day - 6 Days");
				}
				else if(duration.getValue() == 2.0) {
					days.setValue("1 Week - 2 Weeks");
				}
				else if(duration.getValue() == 3.0) {
					days.setValue("2 Weeks - 4 Weeks");
				}
				else if(duration.getValue() == 4.0) {
					days.setValue("1 Month or more");
				}
				else if(duration.getValue() == 5.0) {
					days.setValue("More than a month");
				}
        });

		ComboBox<String> unitfamily = new ComboBox<>("I live with");
		unitfamily.setItems("Single", "With friends", "With family");
		unitfamily.setTextInputAllowed(false);
		unitfamily.setId("unitFamily");
		
		fields.addComponent(address, 0, 0);
		fields.addComponent(description, 1, 0);
		fields.addComponent(days, 0, 1);
		fields.addComponent(duration, 1, 1);
		sections.addComponent(fields, 1, 0);

		Button save = new Button("Save", FontAwesome.SAVE);
		save.setId("saveButton");
		save.setStyleName("v-button-register");
		save.addClickListener(event->{
			FamilyUnit familyUnit;
			if(unitfamily.getValue().equals("Alone")){
				familyUnit = FamilyUnit.Alone;
			}
			else if(unitfamily.getValue().equals("With family")){
				familyUnit = FamilyUnit.Family;
			}
			else{
				familyUnit = FamilyUnit.Friends;
			}
				if(address.getValue().length() > 0 && address.getValue().length() < 50){
					TPlace tPlace = new TPlace(address.getValue(),description.getValue(), new ArrayList<>(),"",familyUnit, AuthService.getUserNickName());
					Controller.getInstance().action(Commands.CommandAddPlace, tPlace);
				}
				else {
					Notification.show("Invalid Address", Notification.Type.ERROR_MESSAGE);
				}
			
		});
		
		mainGrid.addComponent(sections);
		mainGrid.addComponent(save);
		
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

