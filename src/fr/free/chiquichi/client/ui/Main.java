package fr.free.chiquichi.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class Main extends Composite {

	public Main() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(verticalPanel);
		verticalPanel.setSize("100%", "100%");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		verticalPanel.setCellHorizontalAlignment(horizontalPanel, HasHorizontalAlignment.ALIGN_RIGHT);
		
		DecoratedStackPanel decoratedStackPanel = new DecoratedStackPanel();
		verticalPanel.add(decoratedStackPanel);
		verticalPanel.setCellWidth(decoratedStackPanel, "33%");
		
		Button btnPersonnaldata = new Button("personnalData");
		btnPersonnaldata.setText("Information");
		decoratedStackPanel.add(btnPersonnaldata, "Information", false);
		btnPersonnaldata.setSize("100%", "100%");
		
		Button btnModel = new Button("model");
		btnModel.setText("Mod\u00E8le");
		decoratedStackPanel.add(btnModel, "Mod\u00E8le", false);
		btnModel.setSize("100%", "100%");
		
		Button btnCv = new Button("cv");
		btnCv.setText("CV");
		decoratedStackPanel.add(btnCv, "CV", false);
		btnCv.setSize("100%", "100%");
	}

}
