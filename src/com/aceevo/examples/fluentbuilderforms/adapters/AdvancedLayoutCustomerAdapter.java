package com.aceevo.examples.fluentbuilderforms.adapters;

import java.util.Arrays;

import com.aceevo.examples.fluentbuilderforms.item.BuildableBeanItem;
import com.aceevo.examples.fluentbuilderforms.model.Customer;
import com.vaadin.terminal.Sizeable;

public class AdvancedLayoutCustomerAdapter extends BuildableBeanItem<Customer> {

	private static final long serialVersionUID = -7697639101155277546L;
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String DOB = "dob";
	private static final String GENDER = "gender";
	private static final String CUSTOMER_TYPE = "customerType";
	private static final String ADDITIONAL_DATA = "additionalData";

	public static final String[] visibleFields = new String[] { FIRST_NAME, LAST_NAME, DOB, GENDER,
			CUSTOMER_TYPE, ADDITIONAL_DATA };

	public AdvancedLayoutCustomerAdapter(Customer object) {
		super(object, visibleFields);
	}

	@Override
	protected void initializeItemProps() {

		setVisibleItemProperties(Arrays.asList(visibleFields));
		setAdvancedLayout(createGridLayout(2, 4));

		getItemProperty(FIRST_NAME).setDisplayName("First Name").setRequired(true)
				.setPosition(0, 0);
		getItemProperty(LAST_NAME).setDisplayName("Last Name").setRequired(true).setPosition(1, 0);
		getItemProperty(DOB).setDisplayName("Date of Birth").setPosition(0, 1);
		getItemProperty(GENDER).setOptions(Arrays.asList(new Object[] { "Male", "Female" }))
				.setDisplayName("Gender").setRadio(true, true).setPosition(1, 1);
		getItemProperty(CUSTOMER_TYPE).setOptions(
				Arrays.asList(new Object[] { "Platinum", "Gold", "Silver" })).setDisplayName(
				"Customer Status").setPosition(0, 2, 2).setWidth(100, Sizeable.UNITS_PERCENTAGE);
		getItemProperty(ADDITIONAL_DATA).setDisplayName("Additional Information").setRichText(true)
				.setPosition(0, 3, 2).setWidth(100, Sizeable.UNITS_PERCENTAGE);

	}

}
