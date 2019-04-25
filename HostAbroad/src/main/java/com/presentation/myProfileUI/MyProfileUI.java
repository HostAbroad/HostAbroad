package com.presentation.myProfileUI;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.text.DateFormat;
import java.nio.file.Files;

import org.vaadin.easyuploads.UploadField;

import com.business.enums.CountriesEnum;
import com.business.enums.DurationOfStayEnum;
import com.business.enums.InterestsEnum;
import com.business.transfers.THost;
import com.business.transfers.TTraveler;
import com.business.transfers.TUser;
import com.fo0.advancedtokenfield.main.AdvancedTokenField;
import com.presentation.card.Card;
import com.presentation.commands.CommandEnum.Commands;
import com.presentation.commands.Pair;
import com.presentation.controller.Controller;
import com.presentation.headerAndFooter.Footer;
import com.presentation.headerAndFooter.Header;
import com.presentation.loginUI.AuthService;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.Resource;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SuppressWarnings("deprecation")
public class MyProfileUI extends UI {

	// Hay que pasarle un transfer usuario desde el LoginUI, y de ahi sacar todos
	// los campos
	@Override
	protected void init(VaadinRequest request) {

		TUser myUser = new TUser("Prueba", "PruebaFull", "ivan@ucm.es", "1234", 5, "Im prueba", false, false);
		ArrayList<Integer> likes = new ArrayList<Integer>();
		likes.add(1);
		likes.add(2);
		
		TUser myUser1 = new TUser("Prueba", "PruebaFull", "ivan@ucm.es", "1234", 5, "Im prueba", false, false, likes);
		VerticalLayout superLayout = new VerticalLayout();
		superLayout.setStyleName("v-scrollable");
		superLayout.setSpacing(false);
		superLayout.setMargin(false);
		
		GridLayout grid = new GridLayout(3, 1);
		
		Label gap = new Label();
		gap.setWidth("3em");
		grid.addComponent(gap, 1, 0);
		
		GridLayout menu = new GridLayout(1, 6);
		
		HorizontalLayout pages = new HorizontalLayout();
		pages.setSizeFull();
		pages.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		Panel panel = new Panel();
		panel.setSizeFull();
		
		Button personalInfo = new Button("Personal information", VaadinIcons.USER);
		personalInfo.setStyleName("v-button v-widget icon-align-top v-button-icon-align-top-pi");
		personalInfo.setHeight(80, Unit.PIXELS);
		personalInfo.addClickListener(event ->{
			pages.removeAllComponents();
			try {
				pages.addComponent(personalInfoForm(myUser1));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			pages.setWidth("100%");
		});
		menu.addComponent(personalInfo);
		
		Button traveler = new Button("Traveler settings", VaadinIcons.PAPERPLANE);
		traveler.setStyleName("v-button v-widget icon-align-top v-button-icon-align-top-t");
		traveler.setWidth("100%");
		traveler.setHeight(80, Unit.PIXELS);
		traveler.addClickListener(event->{
			pages.removeAllComponents();
			pages.addComponent(myProperties(myUser1));
		});
		menu.addComponent(traveler);
		
		Button host = new Button("Host settings", VaadinIcons.HOME);
		host.setStyleName("v-button v-widget icon-align-top v-button-icon-align-top-h");
		host.setWidth("100%");
		host.setHeight(80, Unit.PIXELS);
		menu.addComponent(host);
		
		Button interests = new Button("Interests", VaadinIcons.CALC_BOOK);
		interests.setStyleName("v-button v-widget icon-align-top v-button-icon-align-top-i");
		interests.setWidth("100%");
		interests.setHeight(80, Unit.PIXELS);
		interests.addClickListener(event->{
			pages.removeAllComponents();
			pages.addComponent(myInterests(myUser1));
		});
		menu.addComponent(interests);
		
		Button comments = new Button("Comments", VaadinIcons.CHAT);
		comments.setStyleName("v-button v-widget icon-align-top v-button-icon-align-top-c");
		comments.setWidth("100%");
		comments.setHeight(80, Unit.PIXELS);
		menu.addComponent(comments);
		
		Button msgs = new Button("Messages", VaadinIcons.ENVELOPES);
		msgs.setStyleName("v-button v-widget icon-align-top v-button-icon-align-top-m");
		msgs.setWidth("100%");
		msgs.setHeight(80, Unit.PIXELS);
		menu.addComponent(msgs);
		
		Button like = new Button("My likes", VaadinIcons.HEART);
		like.setStyleName("v-button v-widget icon-align-top v-button-icon-align-top-ml");
		like.setWidth("100%");
		like.setHeight(80, Unit.PIXELS);
		like.addClickListener(event->{
			pages.removeAllComponents();
			pages.addComponent(myLikes(myUser1));
		});
		menu.addComponent(like);

		grid.addComponent(menu);
		grid.addComponent(pages, 2, 0);
		grid.setComponentAlignment(pages, Alignment.TOP_CENTER);
		
		grid.setComponentAlignment(menu, Alignment.MIDDLE_CENTER);

		superLayout.addComponent(new Header());
		superLayout.addComponentsAndExpand(grid);
		superLayout.addComponent(new Footer());
		personalInfo.click();
		this.setContent(superLayout);
	}

	private HorizontalLayout myInterests(TUser user) {
		Panel panel = new Panel();
		panel.setWidth("100%");
		panel.setId("panelInterests");
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setId("mainLayout");
		HorizontalLayout mainLayoutInterests = new HorizontalLayout();
		mainLayoutInterests.setId("mainLayoutInterests");
		mainLayoutInterests.setStyleName("v-scrollable");
		mainLayoutInterests.setSizeFull();
		mainLayoutInterests.setSpacing(true);

		CheckBoxGroup<InterestsEnum> interests = new CheckBoxGroup<>("Interests");
		interests.setItems(InterestsEnum.values());
		interests.setId("interests");

		Pair<Integer, Object> resultRead = Controller.getInstance().action(Commands.CommandReadHostInformation, user);

		if (resultRead.getLeft() == 1) {

			for (int i = 0; i < ((THost) resultRead.getRight()).getListOfInterests().size(); i++)
				interests.select(((THost) resultRead.getRight()).getListOfInterests().get(i));

		}

		THost tHost = new THost();

		Button saveButton = new Button("Save");
		saveButton.setId("saveButton");
		saveButton.addClickListener(event -> {

			/*
			 * InterestsEnum arrayInterests[] = null; ArrayList<InterestsEnum>
			 * arrayListInterests = new ArrayList<InterestsEnum>(); Set<InterestsEnum>
			 * setInterests = interests.getSelectedItems();
			 * arrayListInterests.addAll(setInterests);
			 * tHost.setNickname(user.getNickname());
			 * tHost.setListOfInterests(arrayListInterests); Pair<Integer, Object> result =
			 * Controller.getInstance().action(Commands.CommandEditHost, tHost);
			 * 
			 * if(result.getLeft() == 1) { Notification not = new Notification("Saved",
			 * Notification.Type.HUMANIZED_MESSAGE); not.setDelayMsec(3000);
			 * not.show(Page.getCurrent()); }
			 * 
			 * else { Notification.show("Error, We couldnt save your interests",
			 * Notification.Type.ERROR_MESSAGE);
			 * 
			 * }
			 */
		});

		mainLayout.addComponents(interests, saveButton);
		mainLayout.setComponentAlignment(interests, Alignment.BOTTOM_CENTER);
		mainLayout.setComponentAlignment(saveButton, Alignment.BOTTOM_CENTER);
		panel.setContent(mainLayout);
		mainLayoutInterests.addComponent(mainLayout);

		return mainLayoutInterests;
	}

	
	public GridLayout personalInfoForm( TUser user) throws IOException {
		
		TUser transfer = new TUser(AuthService.getAuthenticatedEmail());
		Pair<Integer,Object> result = Controller.getInstance().action(Commands.CommandReadUser,transfer);
		
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
		GridLayout fields = new GridLayout(3, 5);
		fields.setSpacing(true);
		fields.setMargin(true);
		fields.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		
		Binder<TUser> binder = new Binder<>(TUser.class);
        /*StreamResource sR = new StreamResource(new StreamSource() {
        	@Override
            public InputStream getStream() {
        		byte[] bi = ((TUser)result.getRight()).getFoto();
        		String text = "Date: " + DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM).format(new Date());
        		BufferedImage imagen = null;
        		InputStream in = new ByteArrayInputStream(bi);
        		try {
					imagen = ImageIO.read(in);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        		imagen.getGraphics().drawChars(text.toCharArray(), 0,
                        text.length(), 10, 20);

                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ImageIO.write(imagen, "png", bos);
                    return new ByteArrayInputStream(bos.toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        },"profileImage.png");
        
		
        Image profileImg = new Image("", sR);
		
		*/
		Image profileImg = new Image();
		profileImg.setSource(new ExternalResource("https://raw.githubusercontent.com/evivar/images/master/User_Circle.png"));
		profileImg.setId("ProfileImage");
		
		UploadField uploadField = new UploadField();
		uploadField.setClearButtonVisible(false);
		uploadField.setButtonCaption("Select image");
		
		Button changeImg = new Button("Change image");
		changeImg.setIcon(FontAwesome.UPLOAD);
		changeImg.addClickListener(event -> {

			if(uploadField.getLastFileName()  != null) {
					byte[] foto = uploadField.getLastFileName().getBytes();
					
						TUser photo = new TUser(foto);
						photo.setEmail(((TUser)result.getRight()).getEmail());
						Controller.getInstance().action(Commands.CommandSaveImage, photo);
					
					
			}
			
		});
		changeImg.setId("ProfileChangeImg");

		image.addComponent(profileImg);
		image.addComponent(uploadField);
		image.setComponentAlignment(uploadField, Alignment.MIDDLE_CENTER);
		image.addComponent(changeImg);
		image.setComponentAlignment(changeImg, Alignment.MIDDLE_CENTER);
		sections.addComponent(image, 0, 0);
		
		TextField username = new TextField("Username");
		username.setValue(((TUser)result.getRight()).getNickname());
		username.setId("ProfileUsername");

		binder.forField(username)
				.withValidator(
						new StringLengthValidator("Nickname should contains between 4 and 20 characters.", 4, 20))
				.bind("nickname");

		TextField fullName = new TextField("Full name");
		fullName.setValue(((TUser)result.getRight()).getFullName());
		fullName.setId("ProfileFullName");

		binder.forField(fullName)
				.withValidator(new RegexpValidator("Full name should have a least 4 letters.", "^.{3,60}\\D+$"))
				.bind("fullName");

		TextField email = new TextField("E-Mail");
		email.setValue(((TUser)result.getRight()).getEmail());
		email.setId("ProfileEmail");

		binder.forField(email).withValidator(new EmailValidator("Invalid e-mail address {0}.")).bind("email");

		ComboBox<String> genderCB = new ComboBox<>("Gender");
		genderCB.setItems("Male", "Female", "Other");
		genderCB.setId("ProfileGender");

		ComboBox<String> languageCB = new ComboBox<>("Language");
		languageCB.setItems("Spanish", "Saharawi");
		languageCB.setId("ProfileLanguages");

		Button save = new Button("Save");
		save.setIcon(FontAwesome.SAVE);
		save.addClickListener(event -> {
			if (binder.isValid()) {
				Notification notif = new Notification("Changes saved!");
				notif.setDelayMsec(3000);
				notif.setPosition(Position.MIDDLE_CENTER);
				notif.show(Page.getCurrent());
				// Hay que llamar al comando
			} else {
				Notification notif = new Notification("Please fill all of the fields correctly. Then click save.");
				notif.setDelayMsec(5000);
				notif.setPosition(Position.MIDDLE_CENTER);
				notif.show(Page.getCurrent());
			}
		});
		save.setId("ProfileSave");

		TextArea description = new TextArea("Description");
		description.setWordWrap(true);
		description.setValue(user.getDescription());
		description.setStyleName("v-textarea v-widget v-textarea-prompt");
		description.setId("ProfileDescription");

		fields.addComponent(fullName, 0, 0);
		fields.addComponent(username, 1, 0);
		fields.addComponent(email, 0, 1);
		fields.addComponent(genderCB, 1, 1);
		fields.addComponent(languageCB, 0, 2);
		fields.addComponent(description, 0, 3, 2, 4);
		
		sections.addComponent(fields, 1, 0);
		
		mainGrid.addComponent(sections);
		mainGrid.addComponent(save);
		return mainGrid;
	}

	public HorizontalLayout myProperties(TUser user) {

		Panel panel = new Panel();
		panel.setWidth("100%");
		panel.setId("panelProperties");
		GridLayout mainLayout = new GridLayout(4, 1);
		mainLayout.setId("mainLayout");
		HorizontalLayout mainLayoutInterests = new HorizontalLayout();
		mainLayoutInterests.setId("mainLayoutProperties");
		mainLayoutInterests.setStyleName("v-scrollable");
		mainLayoutInterests.setSizeFull();
		mainLayoutInterests.setSpacing(true);

		/*CheckBoxGroup<KnowledgesEnum> knowledges = new CheckBoxGroup<>("Knowledges: ");
		knowledges.setItems(KnowledgesEnum.values());
		knowledges.setId("knowledges");
*/
		CheckBoxGroup<CountriesEnum> countries = new CheckBoxGroup<>("Countries I want to visit: ");
		countries.setItems(CountriesEnum.values());
		countries.setId("countries");

		RadioButtonGroup<DurationOfStayEnum> days = new RadioButtonGroup<DurationOfStayEnum>("Maximum stay: ");
		days.setItems(DurationOfStayEnum.values());
		days.setId("days");

		Pair<Integer, Object> resultRead = Controller.getInstance().action(Commands.CommandReadTravelerInformation,
				user);

		if (resultRead.getLeft() == 1) {

			/*for (int i = 0; i < ((TTraveler) resultRead.getRight()).getListOfKnowledges().size(); i++)
				knowledges.select(((TTraveler) resultRead.getRight()).getListOfKnowledges().get(i));*/

			for (int i = 0; i < ((TTraveler) resultRead.getRight()).getListOfCountries().size(); i++)
				countries.select(((TTraveler) resultRead.getRight()).getListOfCountries().get(i));

			if (((TTraveler) resultRead.getRight()).getDurationOfStay() != null)
				days.setSelectedItem(((TTraveler) resultRead.getRight()).getDurationOfStay());

		}

		TTraveler tTraveler = new TTraveler();

		Button saveButton = new Button("Save");
		saveButton.setId("saveButton");
		saveButton.addClickListener(event -> {

			/*
			 * ArrayList<KnowledgesEnum> arrayListKnowledges = new
			 * ArrayList<KnowledgesEnum>(); Set<KnowledgesEnum> setKnowledges =
			 * knowledges.getSelectedItems(); arrayListKnowledges.addAll(setKnowledges);
			 * 
			 * ArrayList<CountriesEnum> arrayListCountries = new ArrayList<CountriesEnum>();
			 * Set<CountriesEnum> setCountries = countries.getSelectedItems();
			 * arrayListCountries.addAll(setCountries);
			 * 
			 * tTraveler.setNickname(user.getNickname());
			 * tTraveler.setListOfKnowledges(arrayListKnowledges);
			 * tTraveler.setDurationOfStay(days.getValue());
			 * tTraveler.setListOfCountries(arrayListCountries); Pair<Integer, Object>
			 * resultEdit = Controller.getInstance().action(Commands.CommandEditTraveler,
			 * tTraveler);
			 * 
			 * if(resultEdit.getLeft() == 1) { Notification not = new Notification("Saved",
			 * Notification.Type.HUMANIZED_MESSAGE); not.setDelayMsec(3000);
			 * not.show(Page.getCurrent()); }
			 * 
			 * else { Notification.show("Error, We couldnt save your properties",
			 * Notification.Type.ERROR_MESSAGE);
			 * 
			 * }
			 */
		});

		/*AdvancedTokenField tfm = new AdvancedTokenField();
		tfm.setCaption("Knowledges");
		mainLayout.addComponent(tfm, 0, 0);*/
		
		//mainLayout.addComponent(knowledges, 0, 0);
		mainLayout.addComponent(days, 1, 0);
		mainLayout.addComponent(countries, 2, 0);
		mainLayout.addComponent(saveButton, 3, 0);
		mainLayout.setComponentAlignment(saveButton, Alignment.MIDDLE_RIGHT);

		mainLayoutInterests.addComponent(mainLayout);

		return mainLayoutInterests;
	}

	private HorizontalLayout myLikes(TUser myUser) {

		HorizontalLayout mainLayout = new HorizontalLayout();
		mainLayout.setId("mainLayout");
		mainLayout.setSizeFull();
		mainLayout.setSpacing(true);
		

		// main helper
		VerticalLayout mainVertical = new VerticalLayout();
		mainVertical.setId("mainVertical");
		Panel panelMain = new Panel();
		panelMain.setSizeFull();
		panelMain.setContent(mainVertical);
		panelMain.setId("panelMain");
		mainLayout.addComponent(panelMain);

		Pair<Integer, Object> result = Controller.getInstance().action(Commands.CommandSendersLike, myUser);

		if (result.getLeft() == 1) {

			VerticalLayout resultsLikes = createResultPanel((ArrayList<TUser>) result.getRight());
			resultsLikes.setId("resultsLikes");
			mainVertical.addComponent(resultsLikes);

		} else {

			Label labelNoLikes = new Label("No likes");
			labelNoLikes.setId("labelNoLikes");
			mainVertical.addComponent(labelNoLikes);
		}

		return mainLayout;
	}

	private VerticalLayout createResultPanel(ArrayList<TUser> users) {
		VerticalLayout resultLayout = new VerticalLayout();
		resultLayout.setMargin(false);
		resultLayout.setSizeFull();
		resultLayout.removeAllComponents();
		resultLayout.setId("resultLayout");
		int counter = 1;
		for (TUser u : users) {
			Card card = new Card(u.getNickname(), u.getDescription());
			card.setId("card" + counter++);
			resultLayout.addComponent(card);
			resultLayout.setComponentAlignment(card, Alignment.TOP_LEFT);
		}
		resultLayout.setHeight("100%");
		return resultLayout;
	}
}