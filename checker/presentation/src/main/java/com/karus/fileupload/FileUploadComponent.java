package com.karus.fileupload;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.vaadin.ui.Upload;
import com.vaadin.ui.themes.ChameleonTheme;

@Component
public class FileUploadComponent extends Upload{
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void build(){
		this.setCaption(null);
		this.setImmediate(true);
		this.setStyleName(ChameleonTheme.BUTTON_BORDERLESS);
		this.setStyleName(ChameleonTheme.BUTTON_WIDE);
		this.setButtonCaption("Select file");
	}
}
