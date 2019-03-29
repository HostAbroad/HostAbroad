package com.presentation.loginUI;


import com.business.TUser;
import com.presentation.commands.CommandEnum;
import com.presentation.commands.Pair;
import com.presentation.controller.Controller;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;

import com.vaadin.ui.*;


import java.io.File;

public class LoginUI extends Panel {
    public LoginUI() {
        AbsoluteLayout layout = new AbsoluteLayout(); //Use absolute layout to be able to put the background image
        layout.addComponent(loadImage("wallpaper.jpg"));
        Component panel = createPanel();
        layout.addComponent(panel,"top: 138.0px; left: 500.0px;"); //The centered form
        layout.setWidth("100%");
        this.setContent(layout);

    }

    private boolean checkEmail(String email) {
        boolean check = false;
        if ((email.length() <= 30) && (email.charAt(2) != '@')) {
            check = true;
        }
        return check;
    }

    private boolean checkPassword(String password) {
        boolean check = false;

        if ((password.length() <= 30) && (password.substring(0, 2).matches("[A-Za-z0-9]"))) {
            check = true;
        }

        return check;
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
        email.setIcon(VaadinIcons.USER); //Vaadin Icons for texfield
        form.addComponent(email);
        form.setComponentAlignment(email,Alignment.MIDDLE_CENTER);
        PasswordField pass = new PasswordField("Password");
        pass.setIcon(VaadinIcons.LOCK); //Vaadin Icons for textfield
        form.addComponent(pass);
        form.setComponentAlignment(pass,Alignment.MIDDLE_CENTER);

        // Button allows specifying icon resource in constructor
        Button login = new Button("Login", VaadinIcons.CHECK);
        login.addClickListener(event->{
            if(!email.getValue().equals("") && !pass.getValue().equals("")){
                TUser tUser = new TUser(email.getValue(), pass.getValue());
                Pair<Integer, Object> filtered = Controller.getInstance().action(CommandEnum.Commands.CommandLogin, tUser);
            }
            else {
                Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
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
