package com.presentation.headerAndFooter;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class Footer extends Panel {

	public Footer() {
		VerticalLayout footer = new VerticalLayout();

		footer.setStyleName("footer-color-gray");

		footer.setWidth("100%");
		footer.setMargin(true);
		footer.setSpacing(false);
		Label title = new Label("Host Abroad ©");
		title.setStyleName("title-label");
		footer.addComponent(title);
		footer.setComponentAlignment(title, Alignment.TOP_CENTER);

		GridLayout authors = new GridLayout(3, 3);
		authors.setSpacing(true);
		authors.addComponent(new Label("Iván Miguel Alba Vázquez"));
		authors.addComponent(new Label("Adrián de Andrés Alonso"));
		authors.addComponent(new Label("Marcos Avilés Camarmas"));
		authors.addComponent(new Label("Sergio García Rodríguez"));
		authors.addComponent(new Label("Gasan Mohamad Nazer"));
		authors.addComponent(new Label("Cristobal Saraiba Torres"));
		authors.addComponent(new Label("Roberto Torres Prensa"));
		authors.addComponent(new Label("Ernesto Vivar Laviña"));
		authors.addComponent(new Label("Veronika Borislavova Yankova"));

		footer.addComponent(authors);
		footer.setComponentAlignment(authors, Alignment.MIDDLE_CENTER);
		
		this.setContent(footer);
	}
	
}
