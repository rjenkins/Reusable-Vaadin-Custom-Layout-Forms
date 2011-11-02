package com.aceevo.examples.fluentbuilderforms.item;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.aceevo.examples.fluentbuilderforms.model.PersistentObject;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

public class BuildableBeanItem<T extends PersistentObject> implements Item,
		Item.PropertySetChangeNotifier, FormFieldFactory {

	private static final long serialVersionUID = 677532970926992036L;
	private BeanItem<T> beanItem;
	private Map<Property, BuildableObjectProperty> propertyCache;
	private Layout advancedLayout = null;
	private Collection<?> visibleItemProperties;

	public BuildableBeanItem(T bean) {
		this.beanItem = new BeanItem<T>(bean);
		initializeItemProps();
	}

	public BuildableBeanItem(T bean, String[] properties) {
		this.beanItem = new BeanItem<T>(bean, properties);
		initializeItemProps();
	}

	public BuildableBeanItem(T bean, Collection<?> propertyIds) {
		this.beanItem = new BeanItem<T>(bean, propertyIds);
		initializeItemProps();
	}

	protected void initializeItemProps() {

	}

	public BuildableObjectProperty getItemProperty(Object id) {
		Property property = beanItem.getItemProperty(id);

		if (propertyCache == null)
			propertyCache = new HashMap<Property, BuildableObjectProperty>();

		BuildableObjectProperty buildableObjectProperty = propertyCache.get(property);
		if (buildableObjectProperty == null) {
			buildableObjectProperty = new BuildableObjectProperty(property);
			propertyCache.put(property, buildableObjectProperty);
		}
		return buildableObjectProperty;
	}

	public boolean addItemProperty(Object id, Property property) {
		boolean addPropBool = beanItem.addItemProperty(id, property);
		if (addPropBool == true) {

			if (propertyCache == null)
				propertyCache = new HashMap<Property, BuildableObjectProperty>();

			BuildableObjectProperty buildableObjectProperty = propertyCache.get(property);
			if (buildableObjectProperty == null) {
				buildableObjectProperty = new BuildableObjectProperty(property);
				propertyCache.put(property, buildableObjectProperty);
			}
		}

		return addPropBool;
	}

	public Object getBean() {
		return beanItem.getBean();
	}

	public Collection<?> getItemPropertyIds() {
		return beanItem.getItemPropertyIds();
	}

	public boolean removeItemProperty(Object id) {
		Property property = beanItem.getItemProperty(id);
		propertyCache.remove(property);
		return beanItem.removeItemProperty(id);
	}

	public Collection<?> getVisibleItemProperties() {
		return visibleItemProperties;
	}

	public void setVisibleItemProperties(Collection<?> visibleItemProperties) {
		this.visibleItemProperties = visibleItemProperties;
	}

	public void addListener(PropertySetChangeListener listener) {
		beanItem.addListener(listener);

	}

	public void removeListener(PropertySetChangeListener listener) {
		beanItem.removeListener(listener);
	}

	public void setAdvancedLayout(Layout gridLayout) {
		this.advancedLayout = gridLayout;
	}

	public Layout getAdvancedLayout() {
		return advancedLayout;
	}

	protected Layout createGridLayout(int columns, int rows) {
		GridLayout gridLayout = new GridLayout(columns, rows);
		gridLayout.setSpacing(true);
		gridLayout.setMargin(true, true, true, false);
		return gridLayout;
	}

	public boolean isAdvancedLayout() {
		return advancedLayout != null;
	}

	public Field createField(Item item, Object propertyId, Component uiContext) {
		BuildableObjectProperty property = (BuildableObjectProperty) item
				.getItemProperty(propertyId);

		Field field = null;

		if (property.getOptions().size() > 0) {
			if (property.isRadio() == false) {
				ComboBox comboBox = new ComboBox();
				comboBox.setNullSelectionAllowed(false);
				comboBox.setNewItemsAllowed(false);
				for (Object option : property.getOptions())
					comboBox.addItem(option);
				comboBox.setImmediate(true);
				field = comboBox;
			} else {
				OptionGroup optionGroup = new OptionGroup();
				for (Object option : property.getOptions())
					optionGroup.addItem(option);
				if (property.isHorizontalRadio())
					optionGroup.addStyleName("horizontal");
				field = optionGroup;
			}
		} else {

			if (property.getType() == Date.class) {
				PopupDateField dateField = new PopupDateField();
				dateField.setResolution(PopupDateField.RESOLUTION_DAY);
				field = dateField;
			} else if (property.isRichText()) {
				RichTextArea richTextArea = new RichTextArea();
				field = richTextArea;
			} else {
				TextField textField = new TextField();
				field = textField;
			}
		}

		if (field != null) {
			field.setCaption(property.getDisplayName());
			field.setWidth(property.getWidth(), property.getSizeable());
			field.setRequired(property.isRequired());
			for (Validator validator : property.getValidators())
				field.addValidator(validator);
		}
		return field;
	}

}
