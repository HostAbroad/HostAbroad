package com.presentation.myProfileUI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.vaadin.easyuploads.UploadField;
import org.vaadin.teemu.ratingstars.RatingStars;

import com.business.enums.CountriesEnum;
import com.business.enums.CountriesTokens;
import com.business.enums.DurationOfStayEnum;
import com.business.enums.FamilyUnit;
import com.business.enums.InterestsEnum;
import com.business.enums.InterestsTokens;
import com.business.enums.KnowledgesEnum;
import com.business.enums.KnowledgesTokens;
import com.business.enums.LanguagesEnum;
import com.business.enums.LanguagesTokens;
import com.business.transfers.THost;
import com.business.transfers.TPlace;
import com.business.transfers.TTraveler;
import com.business.transfers.TUser;
import com.fo0.advancedtokenfield.main.AdvancedTokenField;
import com.fo0.advancedtokenfield.model.Token;
import com.jarektoro.responsivelayout.ResponsiveColumn;
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.presentation.card.Card;
import com.presentation.commands.CommandEnum.Commands;
import com.presentation.commands.Pair;
import com.presentation.controller.Controller;
import com.presentation.headerAndFooter.Footer;
import com.presentation.headerAndFooter.Header;
import com.presentation.loginUI.AuthService;
import com.presentation.myPlacesUI.MyPlacesUI;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.slider.SliderOrientation;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SuppressWarnings("deprecation")
public class MyProfileUI extends UI {
	private static final long serialVersionUID = 1L;
	private String enumValue;

	// Hay que pasarle un transfer usuario desde el LoginUI, y de ahi sacar todos
	// los campos
	@Override
	protected void init(VaadinRequest request) {

		/*
		 * Pair<Integer, Object> userLoged =
		 * Controller.getInstance().action(Commands.CommandReadUser, new
		 * TUser(AuthService.getUserNickName())); TUser myUser = (TUser)
		 * userLoged.getRight();
		 */

		TUser myUser = new TUser("Ernes", "Ernesto", "ernesvivar@gmail.com", "Pablo2001", 3.0, "Holi", false, true);

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

		personalInfo.setHeight(75, Unit.PIXELS);
		personalInfo.addClickListener(event -> {
			pages.removeAllComponents();
			pages.addComponent(personalInfoForm(myUser));
			pages.setWidth("100%");
		});
		menu.addComponent(personalInfo);

		Button traveler = new Button("Traveler settings", VaadinIcons.PAPERPLANE);
		traveler.setStyleName("v-button v-widget icon-align-top v-button-icon-align-top-t");
		traveler.setWidth("100%");

		traveler.setHeight(80, Unit.PIXELS);
		traveler.addClickListener(event -> {
			pages.removeAllComponents();
			pages.addComponent(travelerInfo(myUser));
		});
		menu.addComponent(traveler);

		Button host = new Button("Host settings", VaadinIcons.HOME);
		host.setStyleName("v-button v-widget icon-align-top v-button-icon-align-top-h");
		host.setWidth("100%");
		host.setHeight(75, Unit.PIXELS);
		host.addClickListener(event -> {
			pages.removeAllComponents();
			pages.addComponent(hostInfo(myUser));
		});
		menu.addComponent(host);

		Button interests = new Button("Interests", VaadinIcons.CALC_BOOK);
		interests.setStyleName("v-button v-widget icon-align-top v-button-icon-align-top-i");
		interests.setWidth("100%");

		interests.setHeight(75, Unit.PIXELS);
		interests.addClickListener(event -> {
			pages.removeAllComponents();
			pages.addComponent(myInterests(myUser));
		});
		menu.addComponent(interests);

		Button place = new Button("Add place", VaadinIcons.MAP_MARKER);
		place.setStyleName("v-button v-widget icon-align-top v-button-icon-align-top-c");
		place.setWidth("100%");
		place.setHeight(75, Unit.PIXELS);
		place.addClickListener(event->{
			pages.removeAllComponents();
			pages.addComponent(addPlaces());
		});
		menu.addComponent(place);

		Button like = new Button("My likes", VaadinIcons.HEART);
		like.setStyleName("v-button v-widget icon-align-top v-button-icon-align-top-ml");
		like.setWidth("100%");
		like.setHeight(75, Unit.PIXELS);
		like.addClickListener(event -> {
			pages.removeAllComponents();
			pages.addComponent(myLikes(myUser));
		});
		menu.addComponent(like);

		Button matches = new Button("My matches", VaadinIcons.USERS);
		matches.setStyleName("v-button v-widget icon-align-top v-button-icon-align-top-mm");
		matches.setWidth("100%");
		matches.setHeight(75, Unit.PIXELS);
		matches.addClickListener(event -> {
			pages.removeAllComponents();
			pages.addComponent(myMatches(myUser));
		});
		menu.addComponent(matches);

		grid.addComponent(menu);
		grid.addComponent(pages, 2, 0);
		grid.setComponentAlignment(pages, Alignment.TOP_CENTER);

		grid.setComponentAlignment(menu, Alignment.MIDDLE_CENTER);

		superLayout.addComponent(new Header());
		superLayout.addComponentsAndExpand(grid);
		superLayout.addComponent(new Footer());
		personalInfo.click();
		this.setContent(superLayout);
		this.setContent(superLayout);
	}

	private HorizontalLayout hostInfo(TUser user) {
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

		AdvancedTokenField interests = new AdvancedTokenField();
		interests.setCaption("Interests: ");
		interests.setId("interests");
		interests.setAllowNewTokens(false);
		interests.clearTokens();
		interests.getTokensOfInputField().clear();
		InterestsTokens interest = new InterestsTokens();
		interests.addTokensToInputField(interest.getTokens());

		Pair<Integer, Object> resultRead = Controller.getInstance().action(Commands.CommandReadHostInformation, user);

		if (resultRead.getLeft() == 1) {

			for (int i = 0; i < ((THost) resultRead.getRight()).getListOfInterests().size(); i++)
				interests.addToken(new Token(((THost) resultRead.getRight()).getListOfInterests().get(i).name()));

		}

		THost tHost = new THost();

		Button saveButton = new Button("Save");
		saveButton.setId("saveButton");
		saveButton.addClickListener(event -> {

			// interests.getTokens().forEach(e -> debugLayout.addComponent(new
			// Label(e.toString())));
			InterestsEnum arrayInterests[] = null;
			ArrayList<InterestsEnum> arrayListInterests = new ArrayList<InterestsEnum>();
			List<InterestsEnum> setInterests = new ArrayList<>();
			interests.getTokens().forEach(e -> setInterests.add(InterestsEnum.valueOf(e.getValue())));

			arrayListInterests.addAll(setInterests);
			tHost.setNickname(user.getNickname());
			tHost.setListOfInterests(arrayListInterests);
			Pair<Integer, Object> result = Controller.getInstance().action(Commands.CommandEditHost, tHost);

			if (result.getLeft() == 1) {
				Notification not = new Notification("Saved", Notification.Type.HUMANIZED_MESSAGE);
				not.setDelayMsec(3000);
				not.show(Page.getCurrent());
			}

			else {
				Notification.show("Error, We couldnt save your interests", Notification.Type.ERROR_MESSAGE);

			}

		});

		mainLayout.addComponents(interests, saveButton);
		mainLayout.setComponentAlignment(interests, Alignment.BOTTOM_CENTER);
		mainLayout.setComponentAlignment(saveButton, Alignment.BOTTOM_CENTER);
		panel.setContent(mainLayout);
		mainLayoutInterests.addComponent(mainLayout);

		return mainLayoutInterests;
	}

	public GridLayout personalInfoForm(TUser user) {

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
		GridLayout fields = new GridLayout(4, 5);
		fields.setSpacing(true);
		fields.setMargin(true);
		fields.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

		Binder<TUser> binder = new Binder<>(TUser.class);

		Image profileImg = new Image();
		profileImg.setSource(
				new ExternalResource("https://raw.githubusercontent.com/evivar/images/master/User_Circle.png"));
		profileImg.setId("ProfileImage");

		RatingStars rate = new RatingStars();
		rate.setCaption("Rate: ");
		rate.setValue(user.getRating());
		rate.setReadOnly(true);

		UploadField uploadField = new UploadField();
		uploadField.setClearButtonVisible(false);
		uploadField.setButtonCaption("Select image");

		Button changeImg = new Button("Change image");
		changeImg.setIcon(FontAwesome.UPLOAD);
		changeImg.addClickListener(event -> {
			Notification.show("File: " + uploadField.getLastFileName());
		});
		changeImg.setId("ProfileChangeImg");

		image.addComponent(profileImg);
		image.addComponent(rate);
		image.setComponentAlignment(rate, Alignment.MIDDLE_CENTER);
		image.addComponent(uploadField);
		image.setComponentAlignment(uploadField, Alignment.MIDDLE_CENTER);
		image.addComponent(changeImg);
		image.setComponentAlignment(changeImg, Alignment.MIDDLE_CENTER);
		sections.addComponent(image, 0, 0);

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

		// Create a DateField with the default style
		DateField date = new DateField("Birthday");
		date.setRangeStart(LocalDate.now().minusYears(100)); // Cant put a strange date.
		LocalDate valid = LocalDate.now().minusYears(16);

		AdvancedTokenField lenguages = new AdvancedTokenField();
		lenguages.setCaption("Languages (Only European languages)");
		lenguages.setIcon(FontAwesome.BOOK);
		lenguages.setWidth("100%");
		lenguages.setAllowNewTokens(false);
		lenguages.clearTokens();
		lenguages.getTokensOfInputField().clear();
		LanguagesTokens tokens = new LanguagesTokens();
		lenguages.addTokensToInputField(tokens.getTokens());
		lenguages.setVisible(true);

		ComboBox<CountriesEnum> country = new ComboBox<>("Country");
		country.setItems(CountriesEnum.values());
		country.setId("countryComboBox");

		TextArea description = new TextArea("Description");
		description.setWordWrap(true);
		description.setValue(user.getDescription());
		description.setStyleName("v-textarea v-widget v-textarea-prompt");
		description.setId("ProfileDescription");

		Button save = new Button("Save");
		save.setId("saveButton");
		save.setStyleName("v-button-register");
		save.setIcon(FontAwesome.SAVE);
		save.addClickListener(event -> {

			ArrayList<LanguagesEnum> arrayListLanguages = new ArrayList<LanguagesEnum>();
			List<LanguagesEnum> setLanguages = new ArrayList<>();
			lenguages.getTokens().forEach(e -> setLanguages.add(LanguagesEnum.valueOf(e.getValue())));
			arrayListLanguages.addAll(setLanguages);

			if (binder.isValid() && (date.getValue().getYear() <= valid.getYear())) {
				TUser newUser = new TUser(user.getNickname(), fullName.getValue(), user.getPassword(), email.getValue(),
						description.getValue(), user.getPhoto(), genderCB.getValue(), date.getValue().toString(),
						user.getRating(), user.getHost(), user.getTraveler(), user.getLikes(), user.getRates(),
						(TreeSet<LanguagesEnum>) setLanguages, user.getInterests(), user.getMatches());
				Pair<Integer, Object> result = Controller.getInstance().action(Commands.CommandModifyBasicInformation,
						newUser);
				if ((boolean) result.getRight()) {
					Notification notif = new Notification("Changes saved!");
					notif.setDelayMsec(3000);
					notif.setPosition(Position.MIDDLE_CENTER);
					notif.show(Page.getCurrent());
				}
			} else {
				Notification notif = new Notification(
						"Please fill all of the fields correctly and and enter a valid date (min 16 years). Then click save.");
				notif.setDelayMsec(5000);
				notif.setPosition(Position.MIDDLE_CENTER);
				notif.show(Page.getCurrent());
			}
		});
		save.setId("ProfileSave");

		fields.addComponent(fullName, 0, 0);
		fields.addComponent(username, 1, 0);
		fields.addComponent(email, 0, 1);
		fields.addComponent(genderCB, 1, 2);
		fields.addComponent(country, 0, 2);
		fields.addComponent(date, 1, 1);
		fields.addComponent(lenguages, 3, 0);
		fields.addComponent(description, 0, 3, 3, 4);

		sections.addComponent(fields, 1, 0);

		mainGrid.addComponent(sections);
		mainGrid.addComponent(save);
		return mainGrid;
	}

	public GridLayout travelerInfo(TUser user) {

		GridLayout mainGrid = new GridLayout(1, 1);
		mainGrid.setSpacing(true);
		mainGrid.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		mainGrid.setSizeFull();
		mainGrid.setWidth("100%");

		GridLayout info = new GridLayout(3, 3);
		info.setSpacing(true);
		info.setMargin(true);
		info.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		info.setHeight("100%");
		info.setSizeFull();
		// info.setStyleName("v-scrollable");

		AdvancedTokenField knowledges = new AdvancedTokenField();
		knowledges.setCaption("Knowledges");
		knowledges.setIcon(FontAwesome.BOOK);
		knowledges.setWidth("100%");
		knowledges.setAllowNewTokens(false);
		knowledges.clearTokens();
		knowledges.getTokensOfInputField().clear();
		KnowledgesTokens tokens = new KnowledgesTokens();
		knowledges.addTokensToInputField(tokens.getTokens());
		info.addComponent(knowledges, 0, 0);
		knowledges.addTokenAddListener(event -> {

		});

		AdvancedTokenField countries = new AdvancedTokenField();
		countries.setCaption("Countries I want to visit");
		countries.setIcon(FontAwesome.GLOBE);
		countries.setWidth("100%");
		countries.setAllowNewTokens(false);
		countries.clearTokens();
		countries.getTokensOfInputField().clear();
		CountriesTokens country = new CountriesTokens();
		countries.addTokensToInputField(country.getTokens());
		info.addComponent(countries, 1, 0);

		VerticalLayout stay = new VerticalLayout();
		stay.setWidth("100%");
		stay.setSizeFull();
		// stay.setSizeUndefined();
		Slider days = new Slider("Maximum stay: ", 0, 4);
		days.setOrientation(SliderOrientation.HORIZONTAL);
		// days.setSizeFull();
		days.setWidth("100%");
		stay.addComponentsAndExpand(days);
		stay.addComponent(new Label("&nbsp;", ContentMode.HTML));
		Label dias = new Label("0 days");
		dias.setSizeFull();
		stay.addComponentsAndExpand(dias);
		stay.setComponentAlignment(dias, Alignment.MIDDLE_CENTER);
		days.addValueChangeListener(event -> {
			if (days.getValue() == 0.0) {
				dias.setValue("0 days");
				enumValue = "ZeroToSixDays";
			} else if (days.getValue() == 1.0) {
				dias.setValue("Six days");
				enumValue = "ZeroToSixDays";
			} else if (days.getValue() == 2.0) {
				dias.setValue("Two weeks");
				enumValue = "OneToTwoWeeks";
			} else if (days.getValue() == 3.0) {
				dias.setValue("One month");
				enumValue = "TwoWeeksToAMonth";
			} else {
				dias.setValue("More than a month");
				enumValue = "MoreThanMonth";
			}
		});
		info.addComponent(stay, 2, 0);

		mainGrid.addComponent(info, 0, 0);

		TTraveler tTraveler = new TTraveler();

		Button saveButton = new Button("Save");
		saveButton.setId("saveButton");
		saveButton.setStyleName("v-button-register");
		saveButton.setIcon(FontAwesome.SAVE);
		saveButton.addClickListener(event -> {
			/*
			 * The problem is that you can get a List of Tokens or a List of Strings, but
			 * not a List of KnowledgesEnum or CountriesEnum
			 */
			ArrayList<KnowledgesEnum> arrayListKnowledges = new ArrayList<KnowledgesEnum>();
			List<KnowledgesEnum> setKnowledges = new ArrayList<>();
			knowledges.getTokens().forEach(e -> setKnowledges.add(KnowledgesEnum.valueOf(e.getValue())));
			arrayListKnowledges.addAll(setKnowledges);

			ArrayList<CountriesEnum> arrayListCountries = new ArrayList<CountriesEnum>();
			List<CountriesEnum> setCountries = new ArrayList<>();
			countries.getTokens().forEach(e -> setCountries.add(CountriesEnum.valueOf(e.getValue())));
			arrayListCountries.addAll(setCountries);

			tTraveler.setNickname(user.getNickname());
			// tTraveler.setListOfKnowledges(arrayListKnowledges);
			tTraveler.setDurationOfStay(DurationOfStayEnum.valueOf(enumValue));
			// tTraveler.setListOfCountries(arrayListCountries);
			Pair<Integer, Object> resultEdit = Controller.getInstance().action(Commands.CommandEditTraveler, tTraveler);

			if (resultEdit.getLeft() == 1) {
				Notification not = new Notification("Saved", Notification.Type.HUMANIZED_MESSAGE);
				not.setDelayMsec(3000);
				not.show(Page.getCurrent());
			}

			else {
				Notification.show("Error, We couldnt save your properties", Notification.Type.ERROR_MESSAGE);

			}

		});

		info.addComponent(new Label("&nbsp;", ContentMode.HTML));
		info.addComponent(saveButton, 2, 2);

		Pair<Integer, Object> resultRead = Controller.getInstance().action(Commands.CommandReadTravelerInformation,
				user);

		if (resultRead.getLeft() == 1) {

			/*
			 * for (int i = 0;i < ((TTraveler)
			 * resultRead.getRight()).getListOfKnowledges().size(); i++)
			 * knowledges.addToken(new Token(((TTraveler)
			 * resultRead.getRight()).getListOfKnowledges().get(i).name()));
			 * 
			 * for (int i = 0; i < ((TTraveler)
			 * resultRead.getRight()).getListOfCountries().size(); i++)
			 * countries.addToken(new Token(((TTraveler)
			 * resultRead.getRight()).getListOfCountries().get(i).name()));
			 */
			if (((TTraveler) resultRead.getRight()).getDurationOfStay() != null)
				days.setValue(((TTraveler) resultRead.getRight()).getDurationOfStay().ordinal() + 0.0);

		}

		return mainGrid;
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

		Pair<Integer, Object> result = Controller.getInstance().action(Commands.CommandGetMyLike, myUser);

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

	private HorizontalLayout myMatches(TUser myUser) {

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

		Pair<Integer, Object> result = Controller.getInstance().action(Commands.CommandMyMatches, myUser);

		if (result.getLeft() == 1 && !((ArrayList<TUser>) result.getRight()).isEmpty()) {

			VerticalLayout resultsMatches = createResultPanelMatches((ArrayList<TUser>) result.getRight());
			resultsMatches.setId("resultsMatches");
			mainVertical.addComponent(resultsMatches);

		} else {

			Label labelNoMatches = new Label("No Matches");
			labelNoMatches.setId("labelNoMatches");
			mainVertical.addComponent(labelNoMatches);
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
			Card card = new Card(u);
			card.setId("card" + counter++);
			resultLayout.addComponent(card);
			card.setVisibleLikeButton(false);
			card.setVisibleAcceptButton(true);
			card.setVisibleDeclineButton(true);
			resultLayout.setComponentAlignment(card, Alignment.TOP_LEFT);
		}
		resultLayout.setHeight("100%");
		return resultLayout;
	}

	private VerticalLayout createResultPanelMatches(ArrayList<TUser> users) {
		VerticalLayout resultLayout = new VerticalLayout();
		resultLayout.setMargin(false);
		resultLayout.setSizeFull();
		resultLayout.removeAllComponents();
		resultLayout.setId("resultLayout");
		int counter = 1;
		for (TUser u : users) {
			Card card = new Card(u);
			card.setId("card" + counter++);
			resultLayout.addComponent(card);
			card.setVisibleLikeButton(false);
			card.setVisibleAcceptButton(false);
			card.setVisibleDeclineButton(false);
			card.setStarsAvailable(true);
			resultLayout.setComponentAlignment(card, Alignment.TOP_LEFT);
		}
		resultLayout.setHeight("100%");
		return resultLayout;
	}

	private GridLayout addPlaces() {
		GridLayout mainGrid = new GridLayout(1, 2);
		mainGrid.setSpacing(true);
		mainGrid.setResponsive(false);
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
		fields.setSizeFull();
		fields.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

		Image placeImg = new Image();
		placeImg.setSource(
				new ExternalResource("https://raw.githubusercontent.com/OmegaSkyres/images/master/null.png"));
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
		description.setSizeFull();
		description.setId("PlaceDescription");

		TextField address = new TextField("Address");
		address.setId("PlaceAddress");
		address.setWidth("150%");

		VerticalLayout stay = new VerticalLayout();
		stay.setWidth("100%");
		stay.setSizeFull();
		Slider duration = new Slider("Maximum duration of stay: ", 0, 4);
		duration.setId("slider");
		duration.setOrientation(SliderOrientation.HORIZONTAL);
		duration.setWidth("200px");
		Label days = new Label("Days");
		duration.addValueChangeListener(event -> {
			if (duration.getValue() == 0.0) {
				days.setValue("Days");
			} else if (duration.getValue() == 1.0) {
				days.setValue("1 Day - 6 Days");
			} else if (duration.getValue() == 2.0) {
				days.setValue("1 Week - 2 Weeks");
			} else if (duration.getValue() == 3.0) {
				days.setValue("2 Weeks - 4 Weeks");
			} else if (duration.getValue() == 4.0) {
				days.setValue("1 Month or more");
			} else if (duration.getValue() == 5.0) {
				days.setValue("More than a month");
			}
		});
		stay.addComponentsAndExpand(duration);
		stay.addComponent(new Label("&nbsp;", ContentMode.HTML));
		stay.addComponentsAndExpand(days);

		ComboBox<String> unitfamily = new ComboBox<>("I live with");
		unitfamily.setItems("Single", "With friends", "With family");
		unitfamily.setTextInputAllowed(false);
		unitfamily.setId("unitFamily");

		fields.addComponent(address, 0, 0);
		fields.setComponentAlignment(address, Alignment.TOP_LEFT);
		fields.addComponent(description, 1, 0);
		fields.setComponentAlignment(description, Alignment.TOP_LEFT);
		fields.addComponent(unitfamily, 0, 1);
		fields.addComponent(stay, 1, 1);
		sections.addComponent(fields, 1, 0);

		Button save = new Button("Save", FontAwesome.SAVE);
		save.setId("saveButton");
		save.setStyleName("v-button-register");
		save.addClickListener(event -> {
			FamilyUnit familyUnit;
			if (unitfamily.getValue().equals("Alone")) {
				familyUnit = FamilyUnit.Alone;
			} else if (unitfamily.getValue().equals("With family")) {
				familyUnit = FamilyUnit.Family;
			} else {
				familyUnit = FamilyUnit.Friends;
			}
			if (address.getValue().length() > 0 && address.getValue().length() < 50) {
				TPlace tPlace = new TPlace(address.getValue(), description.getValue(), new ArrayList<>(), "",
						familyUnit, AuthService.getUserNickName());
				Controller.getInstance().action(Commands.CommandAddPlace, tPlace);
			} else {
				Notification.show("Invalid Address", Notification.Type.ERROR_MESSAGE);
			}

		});

		mainGrid.addComponent(sections);
		mainGrid.addComponent(save);
		return mainGrid;
	}
}