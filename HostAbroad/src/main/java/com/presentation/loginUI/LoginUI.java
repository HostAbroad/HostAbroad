package com.presentation.loginUI;


import com.business.transfers.TUser;
import com.presentation.commands.CommandEnum;
import com.presentation.commands.Pair;
import com.presentation.controller.Controller;
import com.presentation.headerAndFooter.Footer;
import com.presentation.headerAndFooter.Header;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import java.io.File;

@Theme("mytheme")
public class LoginUI extends UI {

	@Override
	protected void init(VaadinRequest request){
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setMargin(false);
        mainLayout.setSpacing(false);
        VerticalLayout layout = new VerticalLayout(); //Use vertical layout to center the form
        layout.setStyleName("login-layout");
        //layout.addComponent(loadImage("wallpaper.jpg"));
        Component panel = createPanel();
        layout.addComponent(panel); //The centered form
        layout.setComponentAlignment(panel,Alignment.MIDDLE_CENTER);
        layout.setWidth("100%");
        mainLayout.addComponent(new Header());
        mainLayout.addComponentsAndExpand(layout);
        mainLayout.addComponent(new Footer());
        this.setContent(mainLayout);
    }


    private Image loadImage(String url) { //This method load all images
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

    private Component createPanel(){
        Panel panel = new Panel();
        panel.setHeight("80%");
        panel.setWidth("45%");
        panel.setContent(createForm());

        return panel;
    }


    private Component createForm(){
	    HorizontalLayout hl = new HorizontalLayout();
        FormLayout form = new FormLayout();
        TextField email = new TextField("Email");
        email.setId("emailTextField");
        email.setIcon(VaadinIcons.USER); //Vaadin Icons for texfield
        form.addComponent(email);
        form.setComponentAlignment(email,Alignment.MIDDLE_CENTER);
        PasswordField pass = new PasswordField("Password");
        pass.setId("passTextField");
        pass.setIcon(VaadinIcons.LOCK); //Vaadin Icons for textfield
        form.addComponent(pass);
        form.setComponentAlignment(pass,Alignment.MIDDLE_CENTER);

        CheckBox rememberMe = new CheckBox("Remember me");
        rememberMe.setId("checkBoxRememberMe");

        // Button allows specifying icon resource in constructor
        Button login = new Button("Log In", VaadinIcons.CHECK);
        login.setId("loginBtn");
        login.setWidth("160px");
        login.setStyleName("v-button-register");
        login.addClickListener(event->{
            if(!email.getValue().equals("") && !pass.getValue().equals("")){
                TUser tUser = new TUser(email.getValue(), pass.getValue());
                if(AuthService.authenticate(tUser, rememberMe.getValue())){
                    Page.getCurrent().setLocation("my_profile");
                }
                else {
                    Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
                }
            }

        });
      
        Button register = new Button("Register", VaadinIcons.SIGN_IN);
        register.setId("registerBtn");
        register.setWidth("160px");
        register.setStyleName("v-button-register");
        register.addClickListener(event->{
            LoginUI.this.getUI().getPage().setLocation("register");
        });
        form.addComponent(rememberMe);
        form.setComponentAlignment(rememberMe, Alignment.MIDDLE_CENTER);
        form.addComponent(login);
        form.setComponentAlignment(login,Alignment.MIDDLE_CENTER);
        form.addComponent(register);
        form.setSizeUndefined();
        hl.addComponent(form);
        hl.setComponentAlignment(form,Alignment.MIDDLE_CENTER);
        hl.setSizeFull();
        this.setContent(hl);
        this.setSizeFull();
        return hl;
    }

}
