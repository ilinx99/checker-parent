package com.karus.fileupload;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.karus.home.HomeView;
import com.karus.services.exam.ExamService;
import com.karus.services.exam.dto.BadEntryParseException;
import com.karus.services.exam.dto.DictionaryEntriesModel;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

@Component
@Scope("singleton")
public class FileUploadPresenter implements Receiver, SucceededListener {
	private static final String UPLOAD_DIR_PATH = "/tmp/uploads/";

	private static final long serialVersionUID = 1L;

	private File file;

	@Autowired
	private FileUploadView view;
	
	@Autowired
	@Qualifier("examService")
	private ExamService examService;

	@PostConstruct
	public void build() {
		view.addReceiver(this);
		view.addSuccedListener(this);
	}

	@Override
	public OutputStream receiveUpload(String filename, String mimeType) {

		FileOutputStream fos = null;
		try {
			file = new File(UPLOAD_DIR_PATH + filename);
			file.getParentFile().mkdirs();
			file.createNewFile();
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			Notification.show("Could not open file<br/>");
			return null;
		} catch (IOException e) {
			Notification.show("Could not create file<br/>");
		}

		return fos;
	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		try {
			DictionaryEntriesModel model = new DictionaryEntriesModel();
			model.setExamName(file.getName());
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				model.parseLine(strLine);
			}
			examService.save(model);
			
			Page.getCurrent().setFragment("!" + HomeView.NAME);
			Notification.show("Exam created successfuly");
			in.close();
		} catch (IOException e) {
			Notification.show("Could not read file");
		} catch (BadEntryParseException e) {
			Notification.show("Error while parsing file, please check your file and try again");
		}
	}
}
