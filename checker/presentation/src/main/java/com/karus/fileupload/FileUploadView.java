package com.karus.fileupload;

import com.vaadin.navigator.View;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededListener;

public interface FileUploadView extends View{
	String NAME = "fileUpload";
	
	void addReceiver(Receiver receiver);
	void addSuccedListener(SucceededListener listener);
}
