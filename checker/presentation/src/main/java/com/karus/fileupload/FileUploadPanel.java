package com.karus.fileupload;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ChameleonTheme;

@Component
public class FileUploadPanel extends Panel {

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void build() {
		this.setCaption("File upload");
		this.setStyleName(ChameleonTheme.PANEL_BORDERLESS);
		this.setWidth("50%");
	}

	
}
