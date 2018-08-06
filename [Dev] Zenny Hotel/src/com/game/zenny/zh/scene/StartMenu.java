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
	
	private ComponentGroup background;

	public StartMenu(App app) {
		super(app, App.Scenes.START_MENU.getSceneID());
	}

	@Override
	public void initScene(GameContainer gc, StateBasedGame sbg) {
		/* HEADER */
		
		// TODO [HOTEL] HEADER IMAGE
		
		/* HEADER */
		
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

		connectPasswordTextField = new TextField(this, connectionPaneCenterX, 
													   connectionPaneCenterY,
													   App.proportionalValueByWidth(200), 
													   App.proportionalValueByHeight(50), 
													   "", 
													   "Mot de passe");
													   connectPasswordTextField.setCornerRadius(3);

		connectButton = new Button(this, connectionPaneCenterX,
										 connectionPaneCenterY + App.proportionalValueByHeight(75), 
										 App.proportionalValueByWidth(200),
										 App.proportionalValueByHeight(50), 
										 "SE CONNECTER");
										 connectButton.setCornerRadius(3);
										 connectButton.setButtonColorAutomatic(ZennyColor.ORANGE2);

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
													 	
		registerMailTextField = new TextField(this, registerPaneCenterX,
													registerPaneCenterY,
													App.proportionalValueByWidth(200),
													App.proportionalValueByHeight(50),
													"",
													"E-mail");
													registerMailTextField.setCornerRadius(3);
													
		registerPasswordTextField = new TextField(this, registerPaneCenterX,
													registerPaneCenterY + App.proportionalValueByHeight(75),
													App.proportionalValueByWidth(200),
													App.proportionalValueByHeight(50),
													"",
													"Mot de passe");
													registerPasswordTextField.setCornerRadius(3);
		
		registerPasswordConfirmTextField = new TextField(this, registerPaneCenterX,
														registerPaneCenterY + App.proportionalValueByHeight(150),
														App.proportionalValueByWidth(200),
														App.proportionalValueByHeight(50),
														"",
														"Confirmation");
														registerPasswordConfirmTextField.setCornerRadius(3);
													
		registerButton = new Button(this, registerPaneCenterX,
										  registerPaneCenterY + App.proportionalValueByHeight(225), 
										  App.proportionalValueByWidth(200),
										  App.proportionalValueByHeight(50), 
										  "S'INSCRIRE");
										  registerButton.setCornerRadius(3);
										  registerButton.setButtonColorAutomatic(ZennyColor.PURPLE1);

	    ComponentGroup registerGroup = new ComponentGroup(this, registerPaneCenterX,
			  									registerPaneCenterY + App.proportionalValueByHeight(50), 
			  									App.proportionalValueByWidth(300),
			  									App.proportionalValueByHeight(450), 
			  									new Color(255, 255, 255, 0.2f));
												registerGroup.setCornerRadius(3);								  
										  
		/* REGISTER PANE */

		background = new ComponentGroup(this, App.WINDOW_WIDTH / 2, 
											  App.WINDOW_HEIGHT / 2, 
											  App.WINDOW_WIDTH,
											  App.WINDOW_HEIGHT, 
											  new GradientFill(0, App.WINDOW_HEIGHT / 2, ZennyColor.ORANGE3.getColor(), App.WINDOW_WIDTH, App.WINDOW_HEIGHT / 2, ZennyColor.PURPLE1.getColor()));
		background.setCornerRadius(3);
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
