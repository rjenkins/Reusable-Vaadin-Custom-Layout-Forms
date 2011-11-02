package com.aceevo.examples.fluentbuilderforms.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.terminal.Sizeable;

public class BuildableObjectProperty implements Property {

	private static final long serialVersionUID = -2112159691038583343L;
	private Property property;
	private String displayName;
	private int width = 200;
	private boolean radio = false;
	private boolean horizontalRadio = false;
	private boolean richText = false;
	private boolean required = false;
	private List<Validator> validators;
	private int column, row, colspan;
	private int units = Sizeable.UNITS_PIXELS;

	private List<Object> options = new ArrayList<Object>();

	public BuildableObjectProperty(Property property) {
		this.property = property;
	}

	@Override
	public String toString() {
		return property.toString();
	}

	public Class<?> getType() {
		return property.getType();
	}

	public Object getValue() {
		return property.getValue();
	}

	public boolean isReadOnly() {
		return property.isReadOnly();
	}

	public void setReadOnly(boolean newStatus) {
		property.setReadOnly(newStatus);
	}

	public void setValue(Object newValue) throws ReadOnlyException, ConversionException {
		property.setValue(newValue);
	}

	public BuildableObjectProperty setWidth(int width) {
		this.width = width;
		return this;
	}

	public BuildableObjectProperty setWidth(int width, int units) {
		this.width = width;
		this.units = units;
		return this;
	}

	public int getWidth() {
		return this.width;
	}

	public int getSizeable() {
		return this.units;
	}

	public List<Object> getOptions() {
		return options;
	}

	public BuildableObjectProperty setOptions(List<Object> options) {
		this.options = options;
		return this;
	}

	public String getDisplayName() {
		return displayName;
	}

	public BuildableObjectProperty setDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}

	public boolean isRadio() {
		return radio;
	}

	public BuildableObjectProperty setRadio(boolean radio) {
		this.radio = radio;
		return this;
	}

	public BuildableObjectProperty setRadio(boolean radio, boolean horizontalRadio) {
		this.radio = radio;
		this.horizontalRadio = horizontalRadio;
		return this;
	}

	public boolean isHorizontalRadio() {
		return horizontalRadio;
	}

	public boolean isRichText() {
		return richText;
	}

	public BuildableObjectProperty setRichText(boolean richText) {
		this.richText = richText;
		return this;
	}

	public BuildableObjectProperty setRequired(boolean required) {
		this.required = required;
		return this;
	}

	public boolean isRequired() {
		return required;
	}

	public BuildableObjectProperty addValidator(Validator validator) {
		if (this.validators == null)
			this.validators = new ArrayList<Validator>();
		validators.add(validator);

		return this;
	}

	public List<Validator> getValidators() {
		if (validators == null)
			return Collections.<Validator> emptyList();

		return validators;
	}

	public BuildableObjectProperty setPosition(int column, int row) {
		this.column = column;
		this.row = row;
		return this;
	}

	public BuildableObjectProperty setPosition(int column, int row, int colspan) {
		this.column = column;
		this.row = row;
		this.colspan = colspan;
		return this;
	}

	public BuildableObjectProperty setColSpan(int colspan) {
		this.colspan = colspan;
		return this;
	}

	public int getColSpan() {
		return colspan;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

}
