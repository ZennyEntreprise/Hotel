package com.game.zenny.zh.scene;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.App;
import com.game.zenny.zh.gui.Button;
import com.game.zenny.zh.gui.TextField;
import com.game.zenny.zh.util.ZennyColor;

public class StartMenu extends Scene {

	private TextField connectUsernameTextField;
	private TextField connectPasswordTextField;
	private Button connectButton;
	private Button registerButton;

	public StartMenu(App app) {
		super(app, App.Scenes.START_MENU.getSceneID());
	}

	@Override
	public void initScene(GameContainer gc, StateBasedGame sbg) {
		connectUsernameTextField = new TextField(this, App.WINDOW_WIDTH / 2 - App.proportionalValueByWidth(200),
				App.WINDOW_HEIGHT / 2 - App.proportionalValueByHeight(75), App.proportionalValueByWidth(200),
				App.proportionalValueByHeight(50), "", "Nom d'utilisateur");
		connectUsernameTextField.setCornerRadius(3);

		connectPasswordTextField = new TextField(this, App.WINDOW_WIDTH / 2 - App.proportionalValueByWidth(200),
				App.WINDOW_HEIGHT / 2, App.proportionalValueByWidth(200), App.proportionalValueByHeight(50), "",
				"Mot de passe");
		connectPasswordTextField.setCornerRadius(3);

		connectButton = new Button(this, App.WINDOW_WIDTH / 2 - App.proportionalValueByWidth(200),
				App.WINDOW_HEIGHT / 2 + App.proportionalValueByHeight(75), App.proportionalValueByWidth(200),
				App.proportionalValueByHeight(50), "SE CONNECTER");
		connectButton.setCornerRadius(3);
		connectButton.setButtonColorAutomatic(ZennyColor.ORANGE2);

		registerButton = new Button(this, App.WINDOW_WIDTH / 2 + App.proportionalValueByWidth(200),
				App.WINDOW_HEIGHT / 2 + App.proportionalValueByHeight(75), App.proportionalValueByWidth(200),
				App.proportionalValueByHeight(50), "S'INSCRIRE");

		registerButton.setCornerRadius(3);
		registerButton.setButtonColorAutomatic(ZennyColor.RED1);
	}

	@Override
	public void leaveScene(GameContainer gc, StateBasedGame sbg) {

	}

	@Override
	public void renderScene(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

	}

	@Override
	public void updateScene(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

	}

}
