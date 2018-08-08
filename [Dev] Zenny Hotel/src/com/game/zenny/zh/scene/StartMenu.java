package com.game.zenny.zh.scene;

import java.util.UUID;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import com.game.zenny.zh.util.ZennyHash;
import com.game.zenny.zh.util.ZennyWebQuery;

public class StartMenu extends Scene {

	private TextField connectUsernameTextField;
	private TextField connectPasswordTextField;
	private Label connectInfoLabel;
	private Button connectButton;
	
	private TextField registerUsernameTextField;
	private TextField registerMailTextField;
	private TextField registerPasswordTextField;
	private TextField registerPasswordConfirmTextField;
	private Label registerInfoLabel;
	private Button registerButton;
	
	private boolean waitingWebServerResponse = false;
	
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
													  
	   connectInfoLabel = new Label(this, connectionPaneCenterX, 
			   							   Math.round(connectionPaneCenterY + App.proportionalValueByHeight(42)), 
			   							   App.getFont(App.getFonts().OpenSans_REGULAR_ITALIC, App.proportionalValueByWidth(17)), 
			   							   "");
	   									   connectInfoLabel.setColor(ZennyColor.RED3);
													   
	   connectButton = new Button(this, connectionPaneCenterX,
									    connectionPaneCenterY + App.proportionalValueByHeight(85), 
									    App.proportionalValueByWidth(200),
									    App.proportionalValueByHeight(50), 
									    "SE CONNECTER");
									    connectButton.setCornerRadius(3);
									    connectButton.setButtonColorAutomatic(ZennyColor.ORANGE1);
									    connectButton.setDisabled(true);
									    connectButton.setClickAction(new Runnable() {
									   	   @Override
										   public void run() {
									   		connectInfoLabel.setText("");
									   		   
											   String username = connectUsernameTextField.getText();
											   String password = ZennyHash.hash256(connectPasswordTextField.getText());
											
											   waitingWebServerResponse = true;
											   
											   String query = ZennyWebQuery.query("connect.php?username="+username+"&password="+password);
											   
											   waitingWebServerResponse = false;
											   
											   if (query.equals("query-error")) {
													connectInfoLabel.setText("Un problème est survenu");
												} else {
													if (query.equals("error")) {
														connectInfoLabel.setText("Nom d'utilisateur ou mot de passe incorrect");
											        } else {
											        	try {
															JSONObject connectionObject = (JSONObject) new JSONParser().parse(query);
															App.enterScene(new Game(getApp(), connectionObject.get("uuid").toString()), gc);
														} catch (ParseException e) {
															connectInfoLabel.setText("Un problème est survenu");
														}
											        }
												}
									   	   }
									    });

		ComponentGroup connectGroup = new ComponentGroup(this, connectionPaneCenterX,
												connectionPaneCenterY - App.proportionalValueByHeight(25), 
												App.proportionalValueByWidth(350),
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
														
		registerInfoLabel = new Label(this, registerPaneCenterX, 
				   						   Math.round(registerPaneCenterY + App.proportionalValueByHeight(192)), 
				   						   App.getFont(App.getFonts().OpenSans_REGULAR_ITALIC, App.proportionalValueByWidth(17)), 
										   "");
										   registerInfoLabel.setColor(ZennyColor.RED3);
													
		registerButton = new Button(this, registerPaneCenterX,
										  registerPaneCenterY + App.proportionalValueByHeight(235), 
										  App.proportionalValueByWidth(200),
										  App.proportionalValueByHeight(50), 
										  "S'INSCRIRE");
										  registerButton.setCornerRadius(3);
										  registerButton.setButtonColorAutomatic(ZennyColor.RED2);
										  registerButton.setDisabled(true);
										  registerButton.setClickAction(new Runnable() {
											@Override
											public void run() {
												registerInfoLabel.setText("");
												registerInfoLabel.setColor(ZennyColor.RED3);
												
												waitingWebServerResponse = true;
												   
												String query = ZennyWebQuery.query("register.php?uuid="+UUID.randomUUID()+"&username="+registerUsernameTextField.getText()+"&password="+ZennyHash.hash256(registerPasswordTextField.getText())+"&mail="+registerMailTextField.getText());
												while (query.equals("uuid already exists")) 
													query = ZennyWebQuery.query("register.php?uuid="+UUID.randomUUID()+"&username="+registerUsernameTextField.getText()+"&password="+ZennyHash.hash256(registerPasswordTextField.getText())+"&mail="+registerMailTextField.getText());
												
												if (query.equals("username already exists")) {
													registerInfoLabel.setText("Nom d'utilisateur déjà utilisé");
												} else if (query.equals("mail already exists")) {
													registerInfoLabel.setText("E-mail déjà utilisé");
												} else if (query.equals("valid")) {
													registerInfoLabel.setColor(ZennyColor.GREEN1);
													registerInfoLabel.setText("Compte créé avec succès !");
												}
												
												waitingWebServerResponse = false;
											}
										  });
										  
	    ComponentGroup registerGroup = new ComponentGroup(this, registerPaneCenterX,
			  									registerPaneCenterY + App.proportionalValueByHeight(50), 
			  									App.proportionalValueByWidth(350),
			  									App.proportionalValueByHeight(450), 
			  									new Color(255, 255, 255, 0.2f));
												registerGroup.setCornerRadius(3);								  
										  
		/* REGISTER PANE */

		new ComponentGroup(this, App.WINDOW_WIDTH / 2, 
							     App.WINDOW_HEIGHT / 2, 
							     App.WINDOW_WIDTH + 2,
							     App.WINDOW_HEIGHT + 2, 
							     new GradientFill(0, 0, ZennyColor.DARKGREY1.getColor(), App.WINDOW_WIDTH, App.WINDOW_HEIGHT, ZennyColor.PURPLE2.getColor()));
		
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
		 && !connectPasswordTextField.getText().equals("")
		 && !waitingWebServerResponse) {
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
