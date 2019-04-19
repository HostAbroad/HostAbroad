package com.presentation.myPlacesUI;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;

import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
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
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

public class MyPlacesUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		HorizontalLayout mainLayout = new HorizontalLayout();

		VerticalLayout secondaryLayout = new VerticalLayout();
		
		GridLayout grid = new GridLayout(2, 2);
		grid.setSpacing(true);


		
		Upload upload = new Upload("Add an image", configureReciever());
		upload.setImmediateMode(false);
		upload.setButtonCaption("Upload");
		
		
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
		
		
		mainLayout.addComponent(upload);
		secondaryLayout.addComponent(grid);
		secondaryLayout.addComponent(save);
		secondaryLayout.setComponentAlignment(save, Alignment.MIDDLE_CENTER);
		mainLayout.addComponent(secondaryLayout);
		this.setContent(mainLayout);
	}

	private Receiver configureReciever() {
		// Show uploaded file in this placeholder
		Image image = new Image("Uploaded Image");

		// Implement both receiver that saves upload in a file and
		// listener for successful upload
		class ImageReceiver implements Receiver, SucceededListener {

            /**
			 * 
			 */
			private static final long serialVersionUID = -1978994458420453238L;

			public File file;
            
            FTPClient client = new FTPClient();
            

            public void uploadSucceeded(SucceededEvent event) {
                // Show the uploaded file in the image viewer
                image.setVisible(true);
                image.setSource(new FileResource(file));
            }
			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {
				filename = file.toString();
				// Read the file from resources folder.
		        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		        try (InputStream is = classLoader.getResourceAsStream(filename)) {
		            client.connect("087.ftp.azurewebsites.windows.net");
		            client.login("HostAbroad\\HostAbroad", "Ftp12345");

		            // Store file to server
		            client.storeFile(filename, is);
		            client.logout();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } finally {
		            try {
		                client.disconnect();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
				return null;
			}
        };
        ImageReceiver receiver = new ImageReceiver();
        
        
		// Create the upload with a caption and set receiver later
		Upload upload = new Upload("Upload Image Here", receiver);
		upload.addSucceededListener(receiver);
		return receiver;
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
