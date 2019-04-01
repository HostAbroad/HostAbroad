package com.presentation.loginUI;


import java.io.File;

import com.business.transfers.TUser;
import com.presentation.commands.CommandEnum;
import com.presentation.commands.Pair;
import com.presentation.controller.Controller;
import com.presentation.headerAndFooter.Footer;
import com.presentation.headerAndFooter.Header;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class LoginUI extends UI {

	@Override
	protected void init(VaadinRequest request){
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setMargin(false);
        mainLayout.setSpacing(false);
        AbsoluteLayout layout = new AbsoluteLayout(); //Use absolute layout to be able to put the background image
        layout.setStyleName("login-layout");
        //layout.addComponent(loadImage("wallpaper.jpg"));
        Component panel = createPanel();
        layout.addComponent(panel,"top: 25%; left: 30%;"); //The centered form
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
        final Page.Styles styles = Page.getCurrent().getStyles(); //I put a border to the panel
        String css = ".layout-with-border {\n" + "    border: 3px solid #FF6F61;\n" + "    border-radius: 5px; \n"
                + "}";
        styles.add(css);
        panel.addStyleName("layout-with-border");
        panel.setWidth("650px");
        panel.setHeight("400px");
        panel.setContent(createForm());

        return panel;
    }


    private Component createForm(){
        VerticalLayout form = new VerticalLayout();
        Label title  = new Label("Sign in");
        final Page.Styles styles = Page.getCurrent().getStyles();
        String css = ".v-label-stylename {\n" +   //CSS Syle to Title "Sign in"
                "    font-size: 35px;\n" +
                "    line-height: normal;\n" +
                "}";
        styles.add(css);
        title.setStyleName("v-label-stylename");
        form.addComponent(title);
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

        // Button allows specifying icon resource in constructor
        Button login = new Button("Login", VaadinIcons.CHECK);
        login.setId("loginBtn");
        login.addClickListener(event->{
            if(!email.getValue().equals("") && !pass.getValue().equals("")){
                TUser tUser = new TUser(email.getValue(), pass.getValue());
                Pair<Integer, Object> filtered = Controller.getInstance().action(CommandEnum.Commands.CommandLogin, tUser);
                if(filtered.getLeft() == 1){
                    LoginUI.this.getUI().getPage().setLocation("search");
                }
                else {
                    Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
                }
            }

        });
        form.addComponent(login);
        form.setComponentAlignment(login,Alignment.MIDDLE_CENTER);
        form.setMargin(true);
        this.setContent(form);
        this.setSizeUndefined();
        this.setSizeFull();
        return form;
    }

}
