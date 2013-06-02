package com.karus.custom;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class LabelWithValue extends HorizontalLayout {
	private static final long serialVersionUID = 1L;

	private Label label;
	private Label value;

	public LabelWithValue(String labelText) {
		label = new Label(labelText);
		value = new Label();
		this.addComponent(label);
		this.addComponent(value);
	}
	
	public LabelWithValue(String labelText, String valueText) {
		label = new Label(labelText);
		value = new Label(valueText);
		this.addComponent(label);
		this.addComponent(value);
	}

	public String getLabel() {
		return label.getValue();
	}

	public String getValue() {
		return value.getValue();
	}
	
	public Integer getIntValue() {
		return Integer.parseInt(value.getValue());
	}
	
	public void setValueStyle(String styleName) {
		value.setStyleName(styleName);
	}

	public void setValue(String valueText) {
		value.setValue(valueText);
	}

	public void decrement() {
		Integer intValue = getIntValue();
		intValue--;
		value.setValue(intValue.toString());
	}

	public void increment() {
		Integer intValue = getIntValue();
		intValue++;
		value.setValue(intValue.toString());
	}
}
