package fr.free.chiquichi.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;

import fr.free.chiquichi.client.StockService;
import fr.free.chiquichi.client.StockServiceAsync;

public class Login extends Composite {


	@UiTemplate("Login.ui.xml")
	interface LoginUiBinder extends UiBinder<Panel, Login> {
	}
	
	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);


	private final StockServiceAsync loginService = GWT
			.create(StockService.class);
	
	

	//@UiField(provided = true)
	//final LoginRessource res;

	//private static boolean stylesInjected = false;

	public Login() {

	/*	this.res = GWT.create(LoginRessource.class);

		// Inject only once.
		if (!stylesInjected) {
			StyleInjector.injectStylesheet(res.style().getText());
			stylesInjected = true;
		}*/

		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	TextBox loginBox;

	@UiField
	TextBox passwordBox;

	@UiField
	Label completionLabel1;

	@UiField
	Label completionLabel2;

	private Boolean tooShort = false;

	/*
	 * Method name is not relevant, the binding is done according to the class
	 * of the parameter.
	 */
	@UiHandler("buttonSubmit")
	void doClickSubmit(ClickEvent event) {
		if (tooShort) {
			loginService.login(loginBox.getValue(), passwordBox.getValue(),
					new AsyncCallback<String>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Server communication error");
						}

						@Override
						public void onSuccess(String result) {
							Window.alert(result);
						}
					});
		} else {
			Window.alert("Login or Password is too short");
		}
	}

	@UiHandler("loginBox")
	void lalala(ValueChangeEvent<String> event) {
		if (event.getValue().length() < 3) {
			completionLabel1.setText("Login too short (Size must be >3)");
			tooShort = false;
		} else {
			tooShort = true;
			completionLabel1.setText("");
		}
	}

	@UiHandler("passwordBox")
	void lalalaa(ValueChangeEvent<String> event) {
		if (event.getValue().length() < 3) {
			tooShort = false;
			completionLabel2.setText("Password too short (Size must be >3)");
		} else {
			tooShort = true;
			completionLabel2.setText("");
		}
	}

}
