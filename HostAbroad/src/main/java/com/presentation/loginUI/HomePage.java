package com.presentation.loginUI;



import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

import java.io.File;

public class HomePage extends Panel {

    public HomePage(String nickname) {
        GridLayout grid = new GridLayout(3,3);
        //Locating the inner layouts
        grid.addComponent(createLeftPartOfLogin());
        grid.addComponent(createCenterPartOfLogin());
        grid.addComponent(createRightPartOfLogin());
        grid.setSizeFull();
        this.setContent(grid);
        this.setSizeUndefined();
        this.setSizeFull();
    }


    private Image loadImage(String url) {
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


    private Component createLeftPartOfLogin() {
        VerticalLayout Traveller = new VerticalLayout();
        Label titulo = new Label("Traveler");
        final Page.Styles styles = Page.getCurrent().getStyles();
        String css = ".v-label-stylename {\n" +
                "    font-size: 35px;\n" +
                "    font-weight: bold;\n" +
                "    line-height: normal;\n" +
                "}";
        styles.add(css);
        titulo.setStyleName("v-label-stylename");
        Label description = new Label(
                "Traveling around the world is your passion? \n" +
                        "Are you ready for new adventures?\n" +
                        "Do you want to pay with your knowledge?\n" +
                        "Come and join us now, Traveler.\n", ContentMode.PREFORMATTED);
        Traveller.addComponent(titulo);
        Traveller.addComponent(description);
        Traveller.addComponent(this.loadImage("traveller.png"));
        Traveller.setComponentAlignment(titulo, Alignment.TOP_CENTER);
        Traveller.setSizeUndefined();
        Traveller.setWidth("100%");
        Traveller.setHeightUndefined();
        Traveller.setMargin(true);

        return Traveller;


    }

    private Component createCenterPartOfLogin() {
        VerticalLayout Logo = new VerticalLayout();
        //later the loadImage will be called with the photo that correspond to the user
        Image image = this.loadImage("logoIcon.png");
        Logo.addComponent(image); //TODO LUEGO SE PONDRA ESTE METODO EN UNA CLASE AUXILIAR
        Logo.setComponentAlignment(image,Alignment.TOP_CENTER);
        Component panel = createBottomPanel();
        Logo.addComponent(panel);
        Logo.setComponentAlignment(panel,Alignment.BOTTOM_CENTER);
        Logo.setSizeFull();
        Logo.setMargin(true);
        return Logo;
    }


    private Component createRightPartOfLogin() {
        VerticalLayout Host = new VerticalLayout();
        Label title = new Label("Host");
        final Page.Styles styles = Page.getCurrent().getStyles();
        String css = ".v-label-stylename {\n" +
                "    font-size: 35px;\n" +
                "    font-weight: bold;\n" +
                "    line-height: normal;\n" +
                "}";
        styles.add(css);
        title.setStyleName("v-label-stylename");
        Label description = new Label(
                "Do you like meeting new people and learning \n" +
                        "about different cultures?\n" +
                        "Do you want to gain new knowledge?\n" +
                        "Join us as Host and feel at home.\n", ContentMode.PREFORMATTED);

        Host.addComponent(title);
        Host.addComponent(description);
        Host.addComponent(this.loadImage("host.png")); //TODO LUEGO SE PONDRA ESTE METODO EN UNA CLASE AUXILIAR
        Host.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
        Host.setSizeUndefined();
        Host.setHeightUndefined();
        Host.setWidth("100%");
        Host.setMargin(true);

        return Host;
    }

    private Component createBottomPanel(){
        HorizontalLayout panel = new HorizontalLayout();
        Button join = new Button("Join");
        join.addClickListener(event->{
            //This is a example
            //LoginUI form = new LoginUI();
            //setContent(form);
        });
        panel.addComponent(join);
        panel.setComponentAlignment(join,Alignment.MIDDLE_CENTER);


        Button login = new Button("Sign in");
        login.addClickListener(event->{
                LoginUI form = new LoginUI();
                setContent(form);
        });
        panel.addComponent(login);
        panel.setComponentAlignment(login,Alignment.MIDDLE_CENTER);
        panel.setWidth("100%");
        return panel;
    }



}
