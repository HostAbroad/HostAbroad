package com.presentation.searchUI;

import java.io.File;
import java.util.ArrayList;

import com.business.enums.SearchEnum;
import com.business.transfers.TUser;
import com.presentation.card.Card;
import com.presentation.commands.CommandEnum.Commands;
import com.presentation.commands.Pair;
import com.presentation.controller.Controller;
import com.presentation.headerAndFooter.Footer;
import com.presentation.headerAndFooter.Header;
import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class SearchUI extends UI{
	
	private ArrayList<TUser> results;
	private GridLayout secondaryLayout;
	private GridLayout resultsLayout;

	@Override
	protected void init(VaadinRequest request) {
		//main layout
		HorizontalLayout mainLayout = new HorizontalLayout();
		mainLayout.setSizeFull();
			
		//main helper
		VerticalLayout mainVertical = new VerticalLayout();
		Panel panel = new Panel();
		panel.setHeight("100%");
		panel.setContent(mainVertical);
		mainLayout.addComponent(panel);
		
		
		
		//navbar
		
		mainVertical.addComponent(new Header());
		//if that's false the navbar is on the top of the screen, else - there is a margin
		mainVertical.setMargin(false);
		//secondary layout for the 2 parts
		
		secondaryLayout = new GridLayout(3, 1);
		secondaryLayout.setSizeUndefined();
		
		mainVertical.addComponentsAndExpand(secondaryLayout);
		
		
		this.resultsLayout = new GridLayout();
		this.resultsLayout.setSizeFull();
		this.resultsLayout.setWidthUndefined();
		
		
		//search options layout
		VerticalLayout searchOptionsLayout = new VerticalLayout();
		searchOptionsLayout.setSizeFull();
		searchOptionsLayout.setWidthUndefined();
		searchOptionsLayout.addComponentsAndExpand(this.createSearchOptions());
		searchOptionsLayout.setMargin(false);
		
		final Styles styles = Page.getCurrent().getStyles();
		String css = ".layout-with-border {\n" 
											+ "    border: 2px solid #000000;\n" 
											+ "    border-radius: 5px; \n"
											+ "    box-shadow: 3px 5px 5px #b3b3b3; \n"
											+ "}";
		styles.add(css);
		searchOptionsLayout.addStyleName("layout-with-border");
		secondaryLayout.addComponent(searchOptionsLayout);
		Label gap = new Label();
		gap.setWidth("15em");
		secondaryLayout.addComponent(gap);
		secondaryLayout.addComponent(resultsLayout);
		//resultsLayout.setSizeFull();
		mainVertical.addComponent(new Footer());
		mainLayout.setHeight("100%");
		this.setContent(mainLayout);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Panel createSearchOptions() {
		Panel optionsPanel = new Panel();
		optionsPanel.setSizeFull();
		//optionsPanel.setWidth("20%");
	
		//checkbox
		CheckBox hostCheckbox = new CheckBox("Host");
		hostCheckbox.setIcon(FontAwesome.HOME);
		hostCheckbox.setId("checkBoxHost");
		CheckBox travelerCheckbox = new CheckBox("Traveler");
		travelerCheckbox.setIcon(FontAwesome.PAPER_PLANE);
		travelerCheckbox.setId("checkBoxtraveler");
		//Button Accept
		Button accept = new Button("Search");
		accept.setIcon(FontAwesome.SEARCH);
		accept.setId("acceptButton");
		accept.addClickListener(event->{
			SearchUI.this.secondaryLayout.removeComponent(2, 0);
			ArrayList<SearchEnum> selectedOptions = new ArrayList();
			
			if(hostCheckbox.getValue())
				selectedOptions.add(SearchEnum.isHost);
			if(travelerCheckbox.getValue())
				selectedOptions.add(SearchEnum.isTraveler);

			Pair<Integer, Object> filtered = Controller.getInstance().action(Commands.CommandSearch, selectedOptions);
			if(filtered.getLeft() == 0) {
				Notification notif = new Notification( "There are no users matching your criteria.");
				notif.setDelayMsec(10000);
				notif.setPosition(Position.MIDDLE_CENTER);
				notif.show(Page.getCurrent());
			}
			else {
				SearchUI.this.results = (ArrayList)filtered.getRight();
				resultsLayout = createResultPanel(results);
				SearchUI.this.secondaryLayout.addComponent(resultsLayout,2,0);
				SearchUI.this.secondaryLayout.setComponentAlignment(resultsLayout, Alignment.TOP_CENTER);
			}
			
		});
		accept.setVisible(true);
		
		VerticalLayout v = new VerticalLayout();
		HorizontalLayout h = new HorizontalLayout();
		h.addComponent(hostCheckbox);
		h.addComponent(travelerCheckbox);
		v.addComponent(h);
		v.addComponent(accept);
		v.setComponentAlignment(accept, Alignment.BOTTOM_CENTER);
		
		optionsPanel.setContent(v);
		
		return optionsPanel;
	}

	private GridLayout createResultPanel(ArrayList<TUser> users) {
		GridLayout result = new GridLayout(1, users.size());
		result.setMargin(true);
		result.setSpacing(true);
		result.setSizeFull();
		result.setStyleName("v-scrollable");
		result.removeAllComponents();
		int counter = 1;
		for(TUser u: users) {
			Card card = new Card(u.getNickname(), u.getDescription());
			card.setId("card" + counter++);
			result.addComponent(card);
			result.setComponentAlignment(card, Alignment.TOP_CENTER);
		}
		result.setWidth("100%");
		return result;
	}
	
}
