package com.karus.exam;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.vaadin.ui.Table;

@Component
public class ResultTable extends Table{
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	public void build(){
		this.setCaption("Exam results:");
		this.setWidth("80%");
		
		this.addContainerProperty("Words to translate", String.class,  null);
		this.addContainerProperty("Your answer", String.class,  null);
		this.addContainerProperty("Correct answers",  String.class,  null);
		this.addContainerProperty("Result",       Boolean.class, null);
		
//		this.setCellStyleGenerator(new ResultCellStyleGenerator());
	}

	public void addAnswerRow(LastAnswerModel model) {
		Object[] cells = new Object[] { model.getOrginalWordsString(), model.getUserAnswer(),
				model.getCorrectAnswersString(), model.isCorrect() };
		this.addItem(cells, null);
	}

	public void reset() {
		this.removeAllItems();
	}
	
//	private class ResultCellStyleGenerator implements CellStyleGenerator{
//
//		@Override
//		public String getStyle(Table table, Object itemId, Object propertyId) {
//			Boolean isCorrect = (Boolean) table.getItem(itemId).getItemProperty("Result").getValue();
//			return ChameleonTheme.LABEL_COLOR;
//		}
//		
//	}
}
