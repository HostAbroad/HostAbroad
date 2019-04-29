package com.presentation.loginUI;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.presentation.headerAndFooter.Footer;
import com.presentation.headerAndFooter.Header;
import com.vaadin.annotations.Theme;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class HomePage extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -810830636383065778L;
	private VerticalLayout mainLayout;

	@Override
	protected void init(VaadinRequest request) {
	
		mainLayout = new VerticalLayout();
		mainLayout.setStyleName("v-scrollable h-scrollable homePage-main-layout");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(false);

		GridLayout grid = new GridLayout(1, 1); // I use a grid layout because I will need 3 rows and 3 columns
		grid.setSpacing(true);

		Component mainPanel = splitPanel();
		mainPanel.setSizeFull();
		grid.addComponent(mainPanel, 0, 0);

		mainLayout.addComponent(new Header());
		mainLayout.addComponent(grid);
		mainLayout.setComponentAlignment(grid, Alignment.MIDDLE_CENTER);
		mainLayout.addComponent(new Label("&nbsp;", ContentMode.HTML));
		Component bottom = createBottomPanel();

		mainLayout.addComponent(bottom);
		mainLayout.setComponentAlignment(bottom, Alignment.MIDDLE_CENTER);
		mainLayout.addComponent(new Label("&nbsp;", ContentMode.HTML));
		mainLayout.addComponent(new Footer());
		this.setContent(mainLayout);
		this.setWidthUndefined();
	}

	private Component splitPanel() {
		GridLayout mainLayout = new GridLayout(3, 1);
		mainLayout.setSpacing(true);
		mainLayout.setSizeFull();
		mainLayout.setWidthUndefined();
		mainLayout.setHeightUndefined();
		mainLayout.setStyleName("homePage-main-layout");

		VerticalLayout travelerLayout = new VerticalLayout();
		travelerLayout.setSpacing(true);
		travelerLayout.setMargin(true);
		travelerLayout.setSizeFull();
		travelerLayout.setWidthUndefined();
		travelerLayout.setHeightUndefined();
		travelerLayout.setStyleName("homePage-traveler-layout");

		Image Timg = new Image();
		Timg.setSource(new ExternalResource("https://raw.githubusercontent.com/evivar/images/master/traveler.jpg"));
		Timg.setWidth(400, Unit.PIXELS);
		Timg.setStyleName("homePage-traveler-img");
		travelerLayout.addComponent(Timg);
		travelerLayout.setComponentAlignment(Timg, Alignment.MIDDLE_CENTER);

		Label Ttitle = new Label("Traveler");
		Ttitle.setStyleName("homePage-traveler-title");
		travelerLayout.addComponent(Ttitle);
		travelerLayout.setComponentAlignment(Ttitle, Alignment.MIDDLE_CENTER);

		Label Tdescription = new Label("<p>Traveling around the world is your passion? Are you "
				+ "<br> ready for new adventures? Do you want to pay with"
				+ "<br> your knowledge? Come and join us now, Traveler</p>", ContentMode.HTML);
		;
		Tdescription.setStyleName("homePage-traveler-description");
		travelerLayout.addComponent(Tdescription);
		travelerLayout.setComponentAlignment(Tdescription, Alignment.MIDDLE_CENTER);

		VerticalLayout hostLayout = new VerticalLayout();
		hostLayout.setSpacing(true);
		hostLayout.setMargin(true);
		hostLayout.setSizeFull();
		hostLayout.setWidthUndefined();
		hostLayout.setHeightUndefined();
		hostLayout.setStyleName("homePage-host-layout");

		Image img = new Image();
		img.setSource(new ExternalResource("https://raw.githubusercontent.com/evivar/images/master/host.jpg"));
		img.setWidth(400, Unit.PIXELS);
		img.setStyleName("homePage-host-img");
		hostLayout.addComponent(img);
		hostLayout.setComponentAlignment(img, Alignment.MIDDLE_CENTER);

		Label title = new Label("Host");
		title.setStyleName("homePage-host-title");
		hostLayout.addComponent(title);
		hostLayout.setComponentAlignment(title, Alignment.MIDDLE_CENTER);

		Label description = new Label("<p>Do you like meeting new people and learning about different"
				+ " <br> cultures? Do you want to gain new knowledge? Join us as "
				+ " <br> Host. Explore the world in the comfort of your home.</p>", ContentMode.HTML);
		;
		description.setStyleName("homePage-host-description");
		hostLayout.addComponent(description);
		hostLayout.setComponentAlignment(description, Alignment.MIDDLE_CENTER);

		mainLayout.addComponent(travelerLayout, 0, 0);
		mainLayout.addComponent(new Label("&nbsp; &nbsp; &nbsp;", ContentMode.HTML), 1, 0);
		mainLayout.addComponent(hostLayout, 2, 0);
		mainLayout.setComponentAlignment(travelerLayout, Alignment.MIDDLE_CENTER);
		mainLayout.setComponentAlignment(hostLayout, Alignment.MIDDLE_CENTER);
		return mainLayout;
	}

	private Component createBottomPanel() {
		GridLayout panel = new GridLayout(3, 1);
		panel.setSpacing(false);
		Button join = new Button("Join us");
		join.setIcon(FontAwesome.USER_PLUS);
		join.setStyleName(
				"v-button v-widget large v-button-large v-button v-widget icon-align-top v-button-icon-align-top v-button-register");
		join.setId("saveButton");
		join.addClickListener(event -> {
			HomePage.this.getUI().getPage().setLocation("register");
		});
		panel.addComponent(join, 0, 0);

		panel.addComponent(new Label("&nbsp; &nbsp; &nbsp;", ContentMode.HTML), 1, 0);

		Button login = new Button("Log in");
		login.setIcon(FontAwesome.SIGN_IN);
		login.setStyleName(
				"v-button v-widget large v-button-large v-button v-widget icon-align-top v-button-icon-align-top v-button-register");
		login.setId("loginBtn");
		login.addClickListener(event -> {
			HomePage.this.getUI().getPage().setLocation("login");
		});
		panel.addComponent(login, 2, 0);
		panel.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		return panel;
	}

}
