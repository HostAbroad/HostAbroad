package com.presentation.searchUI;

import java.util.ArrayList;

import com.business.TUser;
import com.presentation.card.Card;
import com.presentation.commands.CommandEnum.Commands;
import com.presentation.commands.Pair;
import com.presentation.controller.Controller;
import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class SearchUI extends UI{
	
	private ArrayList<TUser> results;
	HorizontalLayout secondaryLayout;
	private VerticalLayout resultsLayout;

	@Override
	protected void init(VaadinRequest request) {
		//main layout
		HorizontalLayout mainLayout = new HorizontalLayout();
		mainLayout.setSizeFull();
			
		//main helper
		VerticalLayout mainVertical = new VerticalLayout();
		
		mainLayout.addComponent(mainVertical);
		
		//navbar
		HorizontalLayout navarLayout = new HorizontalLayout();
		
		mainVertical.addComponent(navarLayout);
		
		//secondary layout for the 2 parts
		
		secondaryLayout = new HorizontalLayout();
		
		mainVertical.addComponent(secondaryLayout);
		
		//search options layout
		
		VerticalLayout searchOptionsLayout = new VerticalLayout();
		searchOptionsLayout.addComponent(this.createSearchOptions());
		searchOptionsLayout.setSizeUndefined();
		searchOptionsLayout.setMargin(false);
		
		this.resultsLayout = new VerticalLayout();
		resultsLayout.setMargin(false);
		//resultsLayout.setWidth("70%");
		
		final Styles styles = Page.getCurrent().getStyles();
		String css = ".layout-with-border {\n" 
											+ "    border: 3px solid #FF6F61;\n" 
											+ "    border-radius: 5px; \n"
											+ "}";
		styles.add(css);
		searchOptionsLayout.addStyleName("layout-with-border");
		
		secondaryLayout.addComponent(searchOptionsLayout);
		secondaryLayout.setSizeFull();
		this.setContent(mainLayout);
	}
	
	private Panel createSearchOptions() {
		Panel optionsPanel = new Panel();
		optionsPanel.setHeight("100%");
		optionsPanel.setWidth("20%");
		
		//checkbox
		CheckBox hostCheckbox = new CheckBox("Host");
		
		//Button Accept
		Button accept = new Button("Accept");
		accept.addClickListener(event->{
			if(hostCheckbox.getValue()) {
				Pair<Integer, Object> filtered = Controller.getInstance().action(Commands.CommandSearchHost, null);
				if(filtered.getLeft() == 0) {
					Notification notif = new Notification( "There are no users matching your criteria.");
					notif.setDelayMsec(2000);
					notif.setPosition(Position.MIDDLE_CENTER);
					notif.show(Page.getCurrent());
				}
				else {
					SearchUI.this.resultsLayout.removeAllComponents();
					SearchUI.this.resultsLayout = new VerticalLayout();
					SearchUI.this.results = (ArrayList)filtered.getRight();
					SearchUI.this.resultsLayout = SearchUI.this.createResultPanel(results);
					SearchUI.this.secondaryLayout.addComponent(resultsLayout);
					SearchUI.this.resultsLayout.setMargin(false);
					secondaryLayout.setWidth("50%");
				}
			}
			else if(!hostCheckbox.getValue()){
				Notification notif = new Notification( "There are no criterias for your filters.");
				notif.setDelayMsec(2000);
				notif.setPosition(Position.MIDDLE_CENTER);
				notif.show(Page.getCurrent());
			}
		});
		accept.setVisible(true);
		
		//
		VerticalLayout v = new VerticalLayout();
		v.addComponent(hostCheckbox);
		v.addComponent(accept);
		
		optionsPanel.setContent(v);
		
		return optionsPanel;
	}

	private VerticalLayout createResultPanel(ArrayList<TUser> users) {
		VerticalLayout result = new VerticalLayout();
		result.setMargin(false);
		result.setSizeFull();
		for(TUser u: users) {
			Card card = new Card(u.getNickname(), u.getDescription());
			result.addComponent(card);
			result.setComponentAlignment(card, Alignment.TOP_LEFT);
		}
	//	result.setWidth("70%");
		return result;
	}
}
