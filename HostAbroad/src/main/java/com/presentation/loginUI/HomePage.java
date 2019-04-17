package com.presentation.loginUI;

import java.io.File;

import com.presentation.headerAndFooter.Footer;
import com.presentation.headerAndFooter.Header;
import com.vaadin.annotations.Theme;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class HomePage extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -810830636383065778L;
	private Panel mainPanel;
	private VerticalLayout mainLayout;

	@Override
	protected void init(VaadinRequest request) {
		mainLayout = new VerticalLayout();
		mainLayout.setWidth("100%");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);

		mainPanel = new Panel();

		GridLayout grid = new GridLayout(2, 4); // I use a grid layout because I will need 3 rows and 3 columns
		// Locating the inner layouts
		Component header = new Header();
		mainLayout.addComponent(header);
		
		

		grid.addComponent(createLeftPartOfLogin(), 0, 0);
		grid.addComponent(createRightPartOfLogin(), 1, 0);

		Label separator = new Label("<hr />", ContentMode.HTML);
		separator.setHeight(59, Unit.PIXELS);
		separator.setWidthUndefined();

		grid.addComponent(separator);
		grid.addComponent(createCenterPartOfLogin(), 0, 3, 1, 3);
		grid.setSizeFull();
		grid.setWidth("100%");

		mainPanel.setContent(grid);
		
		mainLayout.addComponent(grid);
		mainLayout.setComponentAlignment(grid, Alignment.MIDDLE_CENTER);

		mainLayout.addComponent(new Footer());
		this.setContent(mainLayout);
		this.setSizeUndefined();
		this.setSizeFull();

	}

	private Image loadImage(String url) { // This method load all images
		// reading the image
		// -----------------------------------
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

		// Image as a file resource
		FileResource resource = new FileResource(new File(/*basepath + "/WEB-INF/images/" +*/ url));

		// Show the image
		Image image = new Image("", resource);

		// -----------------------------------

		return image;
	}

	private Component createLeftPartOfLogin() { //
		VerticalLayout Traveller = new VerticalLayout();
		Label titulo = new Label("Traveler");
		final Page.Styles styles = Page.getCurrent().getStyles();
		String css = ".v-label-stylename {\n" + // This fragment allows you to configure the title font
				"    font-size: 35px;\n" + "    font-weight: bold;\n" + "    line-height: normal;\n" + "}";
		styles.add(css);
		titulo.setStyleName("v-label-stylename");
		Label description = new Label("Traveling around the world is your passion?\n" + 
				"<br>\n" + 
				"Are you ready for new adventures?\n" + 
				"<br>\n" + 
				"Do you want to pay with your knowledge?\n" + 
				"<br>\n" + 
				"Come and join us now, Traveler.",
				ContentMode.HTML);

		Image img = new Image();
		img.setSource(new ExternalResource("https://raw.githubusercontent.com/evivar/images/master/traveler.jpg"));
		img.setWidth(400, Unit.PIXELS);

		Traveller.addComponent(titulo);
		Traveller.addComponent(description);
		Traveller.addComponent(img);
		Traveller.setComponentAlignment(titulo, Alignment.TOP_CENTER);
		Traveller.setComponentAlignment(description, Alignment.MIDDLE_CENTER);
		Traveller.setComponentAlignment(img, Alignment.BOTTOM_CENTER);
		Traveller.setSizeFull();
		Traveller.setWidth("100%");
		Traveller.setHeightUndefined();
		Traveller.setMargin(true);

		return Traveller;

	}

	private Component createCenterPartOfLogin() {
		VerticalLayout Logo = new VerticalLayout();
		Component panel = createBottomPanel();
		Logo.addComponent(panel);
		Logo.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		Logo.setSizeFull();
		Logo.setMargin(false);
		return Logo;
	}

	private Component createRightPartOfLogin() {
		VerticalLayout Host = new VerticalLayout();
		Host.setSizeFull();
		Host.setHeightUndefined();
		Host.setWidth("100%");
		Host.setMargin(true);
		Label title = new Label("Host");
		final Page.Styles styles = Page.getCurrent().getStyles();
		String css = ".v-label-stylename {\n" + "    font-size: 35px;\n" + "    font-weight: bold;\n"
				+ "    line-height: normal;\n" + "}";
		styles.add(css);
		title.setStyleName("v-label-stylename");
		Label description = new Label(
				"Do you like meeting new people and learning " +
				"<br>\n" 
				+ "about different cultures?\n" + 
				"<br>\n" + 
				"Do you want to gain new knowledge?\n" + 
				"<br>\n" + 
				"Join us as Host. Explore the world in the comfort of your home.\n",
				ContentMode.HTML);

		Image img = new Image();
		img.setSource(new ExternalResource("https://raw.githubusercontent.com/evivar/images/master/host.jpg"));
		img.setWidth(400, Unit.PIXELS);

		Host.addComponent(title);
		Host.addComponent(description);
		Host.addComponent(img); // TODO LUEGO SE PONDRA ESTE METODO EN UNA CLASE AUXILIAR
		Host.setComponentAlignment(title, Alignment.TOP_CENTER);
		Host.setComponentAlignment(description, Alignment.MIDDLE_CENTER);
		Host.setComponentAlignment(img, Alignment.BOTTOM_CENTER);

		return Host;
	}

	private Component createBottomPanel() {
		HorizontalLayout panel = new HorizontalLayout();
		Button join = new Button("Join");
		join.setId("joinBtn");
		join.addClickListener(event -> {
			HomePage.this.getUI().getPage().setLocation("register");
		});
		panel.addComponent(join);
		panel.setComponentAlignment(join, Alignment.MIDDLE_CENTER);

		Button login = new Button("Sign in");
		login.setId("loginBtn");
		login.addClickListener(event -> {
			HomePage.this.getUI().getPage().setLocation("login");
		});
		panel.addComponent(login);
		panel.setComponentAlignment(login, Alignment.MIDDLE_CENTER);
		panel.setWidthUndefined();
		return panel;
	}

}
