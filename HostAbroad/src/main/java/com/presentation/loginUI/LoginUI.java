package com.presentation.loginUI;

import com.business.User;
import com.presentation.commands.CommandEnum;
import com.presentation.commands.Pair;
import com.presentation.controller.Controller;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.Position;
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
        TextField name = new TextField("Email");
        name.setIcon(VaadinIcons.USER); //Vaadin Icons for texfield
        form.addComponent(name);
        form.setComponentAlignment(name,Alignment.MIDDLE_CENTER);
        PasswordField pass = new PasswordField("Password");
        pass.setIcon(VaadinIcons.LOCK); //Vaadin Icons for textfield
        form.addComponent(pass);
        form.setComponentAlignment(pass,Alignment.MIDDLE_CENTER);

        // Button allows specifying icon resource in constructor
        Button login = new Button("Login", VaadinIcons.CHECK);
        if(name.getValue().equals("") ||  pass.getValue().equals("")) { //Check if the name and password have any value
        login.setEnabled(false); //Disable the Botton
        }
        else login.setEnabled(true); //Enable if the textfield has value.
        login.addClickListener(event->{


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
