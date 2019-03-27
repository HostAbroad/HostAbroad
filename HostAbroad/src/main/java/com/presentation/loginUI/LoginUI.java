package com.presentation.loginUI;


import com.business.TUser;
import com.business.User;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.Position;
import com.vaadin.ui.*;

import javax.persistence.*;
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

    private TUser authenticate(String email, String pass){
        TUser tUser = new TUser();

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tr = em.getTransaction();
            tr.begin();


            // Checks if the email is valid and execute the query
            if (checkEmail(email)) {
                String consult = "SELECT user FROM USER user WHERE user.email = :email AND user.password = :pass";
                Query query = em.createQuery(consult);
                query.setParameter("email", email);
                User user = null;
                try {
                    user =  (User) query.getSingleResult();
                }
                catch (NoResultException ex) {
                    System.out.println(ex.getMessage());
                }
                if(user != null){
                    Notification correct = new Notification( "!!!Correct!!!");
                    correct.setDelayMsec(2000);
                    correct.setPosition(Position.MIDDLE_CENTER);
                    correct.show(Page.getCurrent());
                    tUser.setDescription(user.getDescription());
                    tUser.setHost(user.getHost());
                    tUser.setNickname(user.getNickname());
                    tUser.setRating(user.getRating());

                }
                else {
                    Notification wrong = new Notification( "Invalid Password.");
                    wrong.setDelayMsec(2000);
                    wrong.setPosition(Position.MIDDLE_CENTER);
                    wrong.show(Page.getCurrent());
                }
            } else {
                throw new Exception("Invalid Email");
            }
            em.close();
            emf.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tUser;
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
            TUser tUser = authenticate(email.getValue(),pass.getValue());
            if(tUser != null){
                   //New view with tUser
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
