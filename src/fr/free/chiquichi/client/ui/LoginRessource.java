package fr.free.chiquichi.client.ui;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface LoginRessource extends ClientBundle {

	@Source("Login.css")
	Style style();

	/*
	 * @Source("Logo.jpg") ImageResource logo();
	 */

	public interface Style extends CssResource {
		String greyText();

		String loginButton();

		String box();

		String background();
	}
}
