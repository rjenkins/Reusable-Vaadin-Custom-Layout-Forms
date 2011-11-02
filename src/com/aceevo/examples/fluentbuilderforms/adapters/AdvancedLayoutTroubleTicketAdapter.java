package com.aceevo.examples.fluentbuilderforms.adapters;

import java.util.Arrays;

import com.aceevo.examples.fluentbuilderforms.item.BuildableBeanItem;
import com.aceevo.examples.fluentbuilderforms.model.TroubleTicket;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.terminal.Sizeable;

public class AdvancedLayoutTroubleTicketAdapter extends BuildableBeanItem<TroubleTicket> {

	private static final long serialVersionUID = -4966215760997408782L;
	private static final String TICKET_NUMBER = "ticketNumber";
	private static final String CREATED_DATE = "createdDate";
	private static final String STATUS = "status";
	private static final String CONTACT_NAME = "contactName";
	private static final String PHONE_NUMBER = "phoneNumber";
	private static final String PROBLEM_TYPE = "problemType";
	private static final String DETAIL = "detail";

	private static final String[] visibleFields = new String[] { TICKET_NUMBER, CREATED_DATE,
			STATUS, CONTACT_NAME, PHONE_NUMBER, PROBLEM_TYPE, DETAIL };

	public AdvancedLayoutTroubleTicketAdapter(TroubleTicket troubleTicket) {
		super(troubleTicket, visibleFields);
	}

	@Override
	protected void initializeItemProps() {

		setAdvancedLayout(createGridLayout(2, 5));

		setVisibleItemProperties(Arrays.asList(visibleFields));
		
		getItemProperty(TICKET_NUMBER).setDisplayName("Ticket Number").setPosition(0, 0).setReadOnly(true);
		getItemProperty(CREATED_DATE).setDisplayName("Date Created").setPosition(0, 1).setReadOnly(true);
		getItemProperty(STATUS).setDisplayName("Status").setOptions(
				Arrays.asList(new Object[] { "Open", "Resolved" })).setRadio(true, true).setPosition(1, 2);
		getItemProperty(CONTACT_NAME).setDisplayName("Contact Name").setPosition(0, 3);
		getItemProperty(PHONE_NUMBER).setDisplayName("Phone Number").addValidator(
				new RegexpValidator("(\\d{3}-)?\\d{3}-\\d{4}",
						"Phone Number must follow the form xxx-xxx-xxxx")).setPosition(1, 3);
		getItemProperty(PROBLEM_TYPE).setDisplayName("Problem Type").setOptions(
				Arrays.asList(new Object[] { "System Failure", "RMA", "Performance Problems",
						"General Question" })).setPosition(0, 2);
		getItemProperty(DETAIL).setDisplayName("Additional Information").setRichText(true)
				.setWidth(100, Sizeable.UNITS_PERCENTAGE).setPosition(0, 4, 2);
	}
}
