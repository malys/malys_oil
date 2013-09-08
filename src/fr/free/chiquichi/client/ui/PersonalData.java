package fr.free.chiquichi.client.ui;

import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.datepicker.client.DateBox;

public class PersonalData extends Composite {

	public PersonalData() {

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		initWidget(horizontalPanel);
		horizontalPanel.setSize("100%", "100%");

		Image image = new Image((String) null);
		image.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {

			}
		});
		horizontalPanel.add(image);

		FlowPanel flowPanel = new FlowPanel();
		horizontalPanel.add(flowPanel);

		Label lblTitle = new Label("Titre");
		flowPanel.add(lblTitle);

		ListBox comboBox = new ListBox();
		comboBox.addItem("Mr");
		comboBox.addItem("Mme");
		comboBox.addItem("Mlle");
		comboBox.addItem("Dr");
		comboBox.setName("title");
		flowPanel.add(comboBox);

		Label lblNom = new Label("Nom");
		flowPanel.add(lblNom);

		TextBox txtbxLName = new TextBox();
		flowPanel.add(txtbxLName);
		txtbxLName.setName("LName");

		Label lblPrnom = new Label("Pr\u00E9nom");
		flowPanel.add(lblPrnom);

		TextBox txtbxFName = new TextBox();
		flowPanel.add(txtbxFName);
		txtbxFName.setName("FName");

		Label lblDateDeNaissance = new Label("Date de naissance");
		flowPanel.add(lblDateDeNaissance);

		DateBox dateBoxAge = new DateBox();
		flowPanel.add(dateBoxAge);

		Label lblAdresse = new Label("Adresse");
		flowPanel.add(lblAdresse);

		TextBox txtbxAdresse = new TextBox();
		txtbxAdresse.setName("address");
		flowPanel.add(txtbxAdresse);

		Label lblCodePostal = new Label("Code Postal");
		flowPanel.add(lblCodePostal);

		TextBox textBoxZip = new TextBox();
		textBoxZip.setName("zipcode");
		flowPanel.add(textBoxZip);

		Label lblVille = new Label("Ville");
		flowPanel.add(lblVille);

		TextBox textBoxCity = new TextBox();
		textBoxCity.setName("city");
		flowPanel.add(textBoxCity);

		Label lblNationnalit = new Label("Nationnalit\u00E9");
		flowPanel.add(lblNationnalit);

		TextBox textBoxNationality = new TextBox();
		textBoxNationality.setName("nationality");
		flowPanel.add(textBoxNationality);

		Label lblTlphone = new Label("T\u00E9l\u00E9phone");
		flowPanel.add(lblTlphone);

		TextBox textBoxPhone = new TextBox();
		textBoxPhone.setAlignment(TextAlignment.LEFT);
		textBoxPhone.setName("phoneNumber");
		flowPanel.add(textBoxPhone);

		Label lblMobile = new Label("Mobile");
		flowPanel.add(lblMobile);

		TextBox textBoxMobile = new TextBox();
		textBoxMobile.setName("mobile");
		flowPanel.add(textBoxMobile);

		Label lblEmail = new Label("Email");
		flowPanel.add(lblEmail);

		TextBox textBoxEmail = new TextBox();
		textBoxEmail.setName("email");
		flowPanel.add(textBoxEmail);
	}

}
