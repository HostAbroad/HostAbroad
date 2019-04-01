package com.presentation.createUserUI;

import java.io.File;
import java.util.Optional;
import java.util.stream.Collectors;

import com.business.transfers.TUser;
import com.presentation.commands.CommandEnum.Commands;
import com.presentation.commands.Pair;
import com.presentation.controller.Controller;
import com.presentation.headerAndFooter.Footer;
import com.presentation.headerAndFooter.Header;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.data.BindingValidationStatus;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class RegisterUserUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setMargin(false);
		mainLayout.setSpacing(false);
		AbsoluteLayout layout = new AbsoluteLayout(); // Using absolute layout to be able to put the background image
		layout.addComponent(loadImage("wallpaper.jpg"));

		// Creating the form
		FormLayout registerLayout = new FormLayout();
		Binder<TUser> binder = new Binder<>(TUser.class);

		// The user that we are going to create
		TUser user = new TUser();

		// Create fields
		TextField fullName = this.createTextField("Full name", VaadinIcons.USER);

		TextField nickname = this.createTextField("Nickname", VaadinIcons.USER_STAR);

		TextField email = this.createTextField("Email", VaadinIcons.ENVELOPE);

		PasswordField password = new PasswordField("Password");
		password.setId("passwordField");
		password.setIcon(VaadinIcons.KEY);
		password.setValueChangeMode(ValueChangeMode.EAGER);

		// Here are all of the validations
		binder.forField(fullName)
				.withValidator(new RegexpValidator("Field full name should have a least 4 letters.", "^.{3,60}\\D+$"))
				.bind("fullName");

		binder.forField(nickname)
				.withValidator(
						new StringLengthValidator("Field nickname should contains between 4 and 20 characters.", 4, 20))
				.bind("nickname");

		binder.forField(email).withValidator(new EmailValidator("Invalid e-mail address {0}.")).bind("email");
		binder.forField(password).withValidator(new RegexpValidator(
				"Should contains minimum eight characters, at least one uppercase letter, one lowercase letter and one number.",
				"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")).bind("password");

		NativeButton save = this.createSaveButton(binder, user);

		NativeButton reset = new NativeButton("reset");
		reset.setId("resetBtn");
		reset.addClickListener(event -> {
			// clear fields by setting null
			binder.readBean(null);
		});

		// Adding all fields
		registerLayout.addComponent(fullName);
		registerLayout.addComponent(nickname);
		registerLayout.addComponent(email);
		registerLayout.addComponent(password);

		// A layout to put reset and save buttons in a line
		HorizontalLayout saveAndResetLayout = new HorizontalLayout();
		saveAndResetLayout.addComponent(reset);
		saveAndResetLayout.addComponent(save);

		registerLayout.addComponent(saveAndResetLayout);

		Component panel = createPanel(registerLayout);
		layout.addComponent(panel, "top: 138.0px; left: 500.0px;");
		// The centered form
		layout.setWidth("100%");
		mainLayout.addComponent(new Header());
		mainLayout.addComponentsAndExpand(layout);
		mainLayout.addComponent(new Footer());
		this.setContent(mainLayout);
	}

	private TextField createTextField(String text, Resource icon) {
		TextField textField = new TextField(text);
		textField.setId(text + "TextField");
		textField.setIcon(icon);
		textField.setValueChangeMode(ValueChangeMode.EAGER);
		return textField;
	}

	private NativeButton createSaveButton(Binder<TUser> binder, TUser user) {
		NativeButton save = new NativeButton("save");
		save.setId("saveBtn");
		save.addClickListener(event -> {
			if (binder.writeBeanIfValid(user)) {
				TUser newUser = new TUser(user.getNickname(), user.getFullName(), user.getEmail(), user.getPassword());
				Pair<Integer, Object> result = Controller.getInstance().action(Commands.CommandCreateUser, newUser);
				if ((boolean) result.getRight()) {
					Notification notif = new Notification("User successfully created.");
					notif.setDelayMsec(10000);
					notif.setPosition(Position.MIDDLE_CENTER);
					notif.show(Page.getCurrent());
					Page.getCurrent().setLocation("my_profile");
				} else {
					Notification notif = new Notification("User with this email or nickname abready exists.");
					notif.setDelayMsec(10000);
					notif.setPosition(Position.MIDDLE_CENTER);
					notif.show(Page.getCurrent());
				}

			} else {
				BinderValidationStatus<TUser> validate = binder.validate();
				String errorText = validate.getFieldValidationStatuses().stream()
						.filter(BindingValidationStatus::isError).map(BindingValidationStatus::getMessage)
						.map(Optional::get).distinct().collect(Collectors.joining(", "));

				Notification notif = new Notification("Please fill all of the fields correctly. Then click save.");
				notif.setDelayMsec(10000);
				notif.setPosition(Position.MIDDLE_CENTER);
				notif.show(Page.getCurrent());
			}
		});
		return save;
	}

	private Image loadImage(String url) { // This method load all images. We should put it in a separate class in the
											// future.
		// reading the image
		// -----------------------------------
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

		// Image as a file resource
		FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/" + url));

		// Show the image
		Image image = new Image("", resource);
		// -----------------------------------

		return image;
	}

	private Panel createPanel(FormLayout form) {
		Panel panel = new Panel();
		final Page.Styles styles = Page.getCurrent().getStyles(); // I put a border to the panel
		String css = ".layout-with-border {\n" + "    border: 3px solid #FF6F61;\n" + "    border-radius: 5px; \n"
				+ "}";
		styles.add(css);
		panel.addStyleName("layout-with-border");
//        panel.setWidth("650px");
//        panel.setHeight("400px");
		panel.setSizeUndefined();
		panel.setContent(form);

		return panel;
	}
}
