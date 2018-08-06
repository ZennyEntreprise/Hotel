package com.game.zenny.zh.scene;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.App;
import com.game.zenny.zh.gui.Button;
import com.game.zenny.zh.gui.ComponentGroup;
import com.game.zenny.zh.gui.Label;
import com.game.zenny.zh.gui.TextField;
import com.game.zenny.zh.util.ZennyColor;

public class StartMenu extends Scene {

	private TextField connectUsernameTextField;
	private TextField connectPasswordTextField;
	private Button connectButton;
	
	private TextField registerUsernameTextField;
	private TextField registerMailTextField;
	private TextField registerPasswordTextField;
	private TextField registerPasswordConfirmTextField;
	private Button registerButton;
	
	public StartMenu(App app) {
		super(app, App.Scenes.START_MENU.getSceneID());
	}

	@Override
	public void initScene(GameContainer gc, StateBasedGame sbg) {
		/* CONNECTION PANE */
		float connectionPaneCenterX = App.WINDOW_WIDTH / 2 - App.proportionalValueByWidth(200);
		float connectionPaneCenterY = App.WINDOW_HEIGHT / 2 + App.proportionalValueByHeight(50);

		new Label(this, connectionPaneCenterX, 
						connectionPaneCenterY - App.proportionalValueByHeight(140), 
					   	"CONNEXION");
		
		connectUsernameTextField = new TextField(this, connectionPaneCenterX, 
													   connectionPaneCenterY - App.proportionalValueByHeight(75), 
													   App.proportionalValueByWidth(200),
													   App.proportionalValueByHeight(50), 
													   "", 
													   "Nom d'utilisateur");
													   connectUsernameTextField.setCornerRadius(3);
													   connectUsernameTextField.setAcceptSpace(false);
													   connectUsernameTextField.setMaxChars(20);

		connectPasswordTextField = new TextField(this, connectionPaneCenterX, 
													   connectionPaneCenterY,
													   App.proportionalValueByWidth(200), 
													   App.proportionalValueByHeight(50), 
													   "", 
													   "Mot de passe");
													   connectPasswordTextField.setCornerRadius(3);
													   connectPasswordTextField.setPassword(true);
													   connectPasswordTextField.setAcceptSpace(false);
													   connectPasswordTextField.setMaxChars(20);
													   
		connectButton = new Button(this, connectionPaneCenterX,
										 connectionPaneCenterY + App.proportionalValueByHeight(75), 
										 App.proportionalValueByWidth(200),
										 App.proportionalValueByHeight(50), 
										 "SE CONNECTER");
										 connectButton.setCornerRadius(3);
										 connectButton.setButtonColorAutomatic(ZennyColor.ORANGE1);
										 connectButton.setDisabled(true);

		ComponentGroup connectGroup = new ComponentGroup(this, connectionPaneCenterX,
												connectionPaneCenterY - App.proportionalValueByHeight(25), 
												App.proportionalValueByWidth(300),
												App.proportionalValueByHeight(300), 
												new Color(255, 255, 255, 0.2f));
												connectGroup.setCornerRadius(3);

		/* CONNECTION PANE */

		/* REGISTER PANE */
												
		float registerPaneCenterX = App.WINDOW_WIDTH / 2 + App.proportionalValueByWidth(200);
		float registerPaneCenterY = App.WINDOW_HEIGHT / 2 + App.proportionalValueByHeight(50);
				
		new Label(this, registerPaneCenterX, 
						registerPaneCenterY - App.proportionalValueByHeight(140), 
				   		"INSCRIPTION");
		
		registerUsernameTextField = new TextField(this, registerPaneCenterX, 
													 	registerPaneCenterY - App.proportionalValueByHeight(75), 
													 	App.proportionalValueByWidth(200),
													 	App.proportionalValueByHeight(50), 
													 	"", 
													 	"Nom d'utilisateur");
													 	registerUsernameTextField.setCornerRadius(3);
													 	registerUsernameTextField.setAcceptSpace(false);
													 	registerUsernameTextField.setMaxChars(20);
													 	
		registerMailTextField = new TextField(this, registerPaneCenterX,
													registerPaneCenterY,
													App.proportionalValueByWidth(200),
													App.proportionalValueByHeight(50),
													"",
													"E-mail");
													registerMailTextField.setCornerRadius(3);
													registerMailTextField.setAcceptSpace(false);
													registerMailTextField.setAcceptAt(true);
													registerMailTextField.setAcceptDot(true);
													
		registerPasswordTextField = new TextField(this, registerPaneCenterX,
													registerPaneCenterY + App.proportionalValueByHeight(75),
													App.proportionalValueByWidth(200),
													App.proportionalValueByHeight(50),
													"",
													"Mot de passe");
													registerPasswordTextField.setCornerRadius(3);
													registerPasswordTextField.setPassword(true);
													registerPasswordTextField.setAcceptSpace(false);
													registerPasswordTextField.setMaxChars(20);
		
		registerPasswordConfirmTextField = new TextField(this, registerPaneCenterX,
														registerPaneCenterY + App.proportionalValueByHeight(150),
														App.proportionalValueByWidth(200),
														App.proportionalValueByHeight(50),
														"",
														"Confirmation");
														registerPasswordConfirmTextField.setCornerRadius(3);
														registerPasswordConfirmTextField.setPassword(true);
														registerPasswordConfirmTextField.setAcceptSpace(false);
														registerPasswordConfirmTextField.setMaxChars(20);
													
		registerButton = new Button(this, registerPaneCenterX,
										  registerPaneCenterY + App.proportionalValueByHeight(225), 
										  App.proportionalValueByWidth(200),
										  App.proportionalValueByHeight(50), 
										  "S'INSCRIRE");
										  registerButton.setCornerRadius(3);
										  registerButton.setButtonColorAutomatic(ZennyColor.RED2);
										  registerButton.setClickAction(new Runnable() {
											@Override
											public void run() {
												System.out.println(registerUsernameTextField.getText());
												System.out.println(registerMailTextField.getText());
												System.out.println(registerPasswordTextField.getText());
												System.out.println(registerPasswordConfirmTextField.getText());
											}
										  });
										  registerButton.setDisabled(true);
										  
	    ComponentGroup registerGroup = new ComponentGroup(this, registerPaneCenterX,
			  									registerPaneCenterY + App.proportionalValueByHeight(50), 
			  									App.proportionalValueByWidth(300),
			  									App.proportionalValueByHeight(450), 
			  									new Color(255, 255, 255, 0.2f));
												registerGroup.setCornerRadius(3);								  
										  
		/* REGISTER PANE */

		new ComponentGroup(this, App.WINDOW_WIDTH / 2, 
							     App.WINDOW_HEIGHT / 2, 
							     App.WINDOW_WIDTH + 2,
							     App.WINDOW_HEIGHT + 2, 
							     new GradientFill(0, 0, ZennyColor.ORANGE2.getColor(), App.WINDOW_WIDTH, App.WINDOW_HEIGHT, ZennyColor.PURPLE2.getColor()));
		
	}

	@Override
	public void leaveScene(GameContainer gc, StateBasedGame sbg) {

	}

	@Override
	public void renderScene(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		/* HEADER */
		
		App.getSprites().logoBig.draw(0, -App.proportionalValueByHeight(240), 
										 Math.round(App.proportionalValueByWidth(App.getSprites().logoBig.getWidth())), 
										 Math.round(App.proportionalValueByHeight(App.getSprites().logoBig.getHeight())), 
										 ZennyColor.WHITE);
		
		/* HEADER */
	}

	@Override
	public void updateScene(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (!connectUsernameTextField.getText().equals("") 
		 && !connectPasswordTextField.getText().equals("")) {

			connectButton.setDisabled(false);
		
		} else {
			
			connectButton.setDisabled(true);
			
		}
		
		if (!registerUsernameTextField.getText().equals("") && !registerMailTextField.getText().equals("") 
		 && !registerPasswordTextField.getText().equals("") && !registerPasswordConfirmTextField.getText().equals("")) {
			
			if (isValidMail(registerMailTextField.getText()) && isValidPasswords(registerPasswordTextField.getText(), registerPasswordConfirmTextField.getText())) {
				registerButton.setDisabled(false);
			} else {
				registerButton.setDisabled(true);
			}
		} else {
			registerButton.setDisabled(true);
		}
	}

	/**
	 * @param mail
	 * @return if mail is valid
	 */
	private boolean isValidMail(String mail) {
		if (mail.contains("@") && mail.contains(".")) {
			if (!(mail.charAt(mail.indexOf(".") - 1)+"").equals("@") && !mail.endsWith(".") && !mail.startsWith("@")) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * @param password1
	 * @param password2
	 * @return passwords are valid
	 */
	private boolean isValidPasswords(String password1, String password2) {
		if (password1.equals(password2))
			return true;
		return false;
	}
}
