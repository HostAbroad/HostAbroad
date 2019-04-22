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
import com.vaadin.ui.*;

@Theme("mytheme")
public class RegisterUserUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setMargin(false);
		mainLayout.setSpacing(false);
		VerticalLayout layout = new VerticalLayout(); // Using absolute layout to be able to put the background image
		layout.setStyleName("login-layout");
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

		Button save = this.createSaveButton(binder, user);
		save.setIcon(VaadinIcons.SIGN_IN);
		save.setStyleName("v-button-register");

		// Adding all fields
		registerLayout.addComponent(fullName);
		registerLayout.addComponent(nickname);
		registerLayout.addComponent(email);
		registerLayout.addComponent(password);
		registerLayout.addComponent(save);
		registerLayout.setComponentAlignment(save,Alignment.MIDDLE_CENTER);


		// A layout to put reset and save buttons in a line
		registerLayout.setMargin(true);

		Component panel = createPanel(registerLayout);
		layout.addComponent(panel);
		layout.setComponentAlignment(panel,Alignment.MIDDLE_CENTER);
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

	private Button createSaveButton(Binder<TUser> binder, TUser user) {
		Button save = new Button("Join");
		save.setWidth("160px");
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
					Page.getCurrent().setLocation("login");
				} else {
					Notification.show("User with this email or nickname abready exists.", Notification.Type.ERROR_MESSAGE);
				}

			} else {
				BinderValidationStatus<TUser> validate = binder.validate();
				String errorText = validate.getFieldValidationStatuses().stream()
						.filter(BindingValidationStatus::isError).map(BindingValidationStatus::getMessage)
						.map(Optional::get).distinct().collect(Collectors.joining(", "));

				Notification.show("Please fill all of the fields correctly. Then click Join.", Notification.Type.ERROR_MESSAGE);
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
		HorizontalLayout hl = new HorizontalLayout();
		form.setSizeUndefined();
		Panel panel = new Panel();
		panel.setHeight("80%");
		panel.setWidth("40%");
		hl.addComponent(form);
		hl.setComponentAlignment(form,Alignment.MIDDLE_CENTER);
		hl.setMargin(true);
		hl.setSizeFull();
		panel.setContent(hl);
		return panel;
	}
}
