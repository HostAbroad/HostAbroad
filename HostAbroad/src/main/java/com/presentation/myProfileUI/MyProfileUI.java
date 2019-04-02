package com.presentation.myProfileUI;

import java.util.ArrayList;

import com.business.enums.CountriesEnum;
import com.business.enums.DurationOfStayEnum;
import com.business.enums.InterestsEnum;
import com.business.enums.KnowledgesEnum;
import com.business.transfers.THost;
import com.business.transfers.TTraveler;
import com.business.transfers.TUser;
import com.presentation.card.Card;
import com.presentation.commands.CommandEnum.Commands;
import com.presentation.commands.Pair;
import com.presentation.controller.Controller;
import com.presentation.headerAndFooter.Footer;
import com.presentation.headerAndFooter.Header;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
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
public class MyProfileUI extends UI {

	private TabSheet tabs;

	// Hay que pasarle un transfer usuario desde el LoginUI, y de ahi sacar todos
	// los campos
	@Override
	protected void init(VaadinRequest request) {

		TUser myUser = new TUser("Prueba", "PruebaFull", "ivan@ucm.es", "1234", 5, "Im prueba", false, false);
		VerticalLayout superLayout = new VerticalLayout();
		superLayout.setSpacing(false);
		superLayout.setMargin(false);

		//Para probar que funciona añadir en la bbdd los likes con sus ID. Hay que poner en receivedUser el
		//mismo nombre que ponemos en este transfer
				
		ArrayList<Integer> likes = new ArrayList<Integer>();
		likes.add(1);
		likes.add(2);
		
		TUser myUser1 = new TUser("Prueba", "PruebaFull", "ivan@ucm.es", "1234", 5, "Im prueba", false, false, likes);
		

		HorizontalLayout mainLayout = new HorizontalLayout();
		mainLayout.setSizeFull();
		mainLayout.setId("mainLayout");
		VerticalLayout mainVertical = new VerticalLayout();
		mainVertical.setId("mainVertical");
		Panel panel = new Panel();
		panel.setSizeFull();
		panel.setContent(mainVertical);
		panel.setId("panel");
		mainLayout.addComponent(panel);

		this.tabs = new TabSheet();
		tabs.setStyleName("v-tabsheet v-widget centered-tabs v-tabsheet-centered-tabs equal-width-tabs v-tabsheet-equal-width-tabs padded-tabbar v-tabsheet-padded-tabbar v-has-width");
		mainVertical.addComponent(tabs);
		tabs.setId("tab");

		HorizontalLayout personalInfoTab = new HorizontalLayout();
		personalInfoForm(personalInfoTab, myUser1);
		tabs.addTab(personalInfoTab, "Personal Information", VaadinIcons.USER);

		HorizontalLayout propertiesTab = new HorizontalLayout();
		propertiesTab = myProperties(myUser1);
		tabs.addTab(propertiesTab, "My Properties", VaadinIcons.COGS);

		HorizontalLayout interestsTab = new HorizontalLayout();
		interestsTab = myInterests(myUser1);
		tabs.addTab(interestsTab, "Interests", VaadinIcons.CALC_BOOK);

		HorizontalLayout commentsTab = new HorizontalLayout();
		tabs.addTab(commentsTab, "Comments", VaadinIcons.ENVELOPE);

		HorizontalLayout messagesTab = new HorizontalLayout();
		tabs.addTab(messagesTab, "Messages", VaadinIcons.CHAT);

		HorizontalLayout myLikesTab = new HorizontalLayout();
		myLikesTab = myLikes(myUser1);
		myLikesTab.setId("myLikesTab");
		tabs.addTab(myLikesTab, "My Likes", VaadinIcons.HEART);

		superLayout.addComponent(new Header());
		superLayout.addComponentsAndExpand(panel);
		superLayout.addComponent(new Footer());

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

	public void personalInfoForm(HorizontalLayout layout, TUser user) {
		Panel panel = new Panel();
		panel.setWidth("100%");
		Binder<TUser> binder = new Binder<>(TUser.class);
		HorizontalLayout mainLayout = new HorizontalLayout();
		mainLayout.setSizeFull();
		mainLayout.setSpacing(true);

		VerticalLayout imageAndDescription = new VerticalLayout();
		imageAndDescription.setWidth("300px");
		imageAndDescription.setSpacing(true);

		Image profileImg = new Image();
		profileImg.setSource(new ExternalResource("https://raw.githubusercontent.com/evivar/images/master/User_Circle.png"));
		profileImg.setId("ProfileImage");

		TextArea description = new TextArea("Description");
		description.setWordWrap(true);
		description.setValue(user.getDescription());
		description.setId("ProfileDescription");

		imageAndDescription.addComponent(profileImg);

		imageAndDescription.addComponent(description);
		imageAndDescription.setComponentAlignment(description, Alignment.MIDDLE_CENTER);

		FormLayout personalInfo = new FormLayout();
		personalInfo.setSizeFull();
		personalInfo.setSpacing(true);

		TextField username = new TextField("Username");
		username.setValue(user.getNickname());
		username.setId("ProfileUsername");

		binder.forField(username)
				.withValidator(
						new StringLengthValidator("Nickname should contains between 4 and 20 characters.", 4, 20))
				.bind("nickname");

		TextField fullName = new TextField("Full name");
		fullName.setValue(user.getFullName());
		fullName.setId("ProfileFullName");

		binder.forField(fullName)
				.withValidator(new RegexpValidator("Full name should have a least 4 letters.", "^.{3,60}\\D+$"))
				.bind("fullName");

		TextField email = new TextField("E-Mail");
		email.setValue(user.getEmail());
		email.setId("ProfileEmail");

		binder.forField(email).withValidator(new EmailValidator("Invalid e-mail address {0}.")).bind("email");

		ComboBox<String> genderCB = new ComboBox<>("Gender");
		genderCB.setItems("Male", "Female", "Other");
		genderCB.setId("ProfileGender");

		ComboBox<String> languageCB = new ComboBox<>("Language");
		languageCB.setItems("Spanish", "Saharawi");
		languageCB.setId("ProfileLanguages");

		Button save = new Button("Save");
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

		personalInfo.addComponents(fullName, username, email, genderCB, languageCB, save);
		personalInfo.setComponentAlignment(save, Alignment.BOTTOM_RIGHT);

		mainLayout.addComponent(imageAndDescription);
		mainLayout.addComponent(personalInfo);
		layout.addComponent(mainLayout);
	}

	public HorizontalLayout myProperties(TUser user) {

		Panel panel = new Panel();
		panel.setWidth("100%");
		panel.setId("panelProperties");
		GridLayout mainLayout = new GridLayout(4, 1);
		mainLayout.setId("mainLayout");
		HorizontalLayout mainLayoutInterests = new HorizontalLayout();
		mainLayoutInterests.setId("mainLayoutProperties");
		mainLayoutInterests.setSizeFull();
		mainLayoutInterests.setSpacing(true);

		CheckBoxGroup<KnowledgesEnum> knowledges = new CheckBoxGroup<>("Knowledges: ");
		knowledges.setItems(KnowledgesEnum.values());
		knowledges.setId("knowledges");

		CheckBoxGroup<CountriesEnum> countries = new CheckBoxGroup<>("Countries I want to visit: ");
		countries.setItems(CountriesEnum.values());
		countries.setId("countries");

		RadioButtonGroup<DurationOfStayEnum> days = new RadioButtonGroup<DurationOfStayEnum>("Maximum stay: ");
		days.setItems(DurationOfStayEnum.values());
		days.setId("days");

		Pair<Integer, Object> resultRead = Controller.getInstance().action(Commands.CommandReadTravelerInformation,
				user);

		if (resultRead.getLeft() == 1) {

			for (int i = 0; i < ((TTraveler) resultRead.getRight()).getListOfKnowledges().size(); i++)
				knowledges.select(((TTraveler) resultRead.getRight()).getListOfKnowledges().get(i));

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

		mainLayout.addComponent(knowledges, 0, 0);
		mainLayout.addComponent(days, 1, 0);
		mainLayout.addComponent(countries, 2, 0);
		mainLayout.addComponent(saveButton, 3, 0);
		mainLayout.setComponentAlignment(saveButton, Alignment.MIDDLE_RIGHT);

		mainLayoutInterests.addComponent(mainLayout);

		return mainLayoutInterests;
	}

	@SuppressWarnings("unchecked")
	private HorizontalLayout myLikes(TUser myUser) {

		HorizontalLayout mainLayout = new HorizontalLayout();
		mainLayout.setId("mainLayout");
		mainLayout.setSizeFull();

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