package com.game.zenny.zh.client.scene;

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

import com.game.zenny.zh.client.AppClient;
import com.game.zenny.zh.client.gui.Button;
import com.game.zenny.zh.client.gui.ComponentGroup;
import com.game.zenny.zh.client.gui.Label;
import com.game.zenny.zh.client.gui.TextField;
import com.game.zenny.zh.client.util.ZennyColor;
import com.game.zenny.zh.client.util.ZennyHash;
import com.game.zenny.zh.client.util.ZennyWebQuery;

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
	
	public StartMenu(AppClient app) {
		super(app, AppClient.Scenes.START_MENU.getSceneID());
	}

	@Override
	public void initScene(GameContainer gc, StateBasedGame sbg) {
		/* CONNECTION PANE */
		float connectionPaneCenterX = AppClient.WINDOW_WIDTH / 2 - AppClient.proportionalValueByWidth(200);
		float connectionPaneCenterY = AppClient.WINDOW_HEIGHT / 2 + AppClient.proportionalValueByHeight(50);

		new Label(this, connectionPaneCenterX, 
						connectionPaneCenterY - AppClient.proportionalValueByHeight(140), 
					   	"CONNEXION");
		
		connectUsernameTextField = new TextField(this, connectionPaneCenterX, 
													   connectionPaneCenterY - AppClient.proportionalValueByHeight(75), 
													   AppClient.proportionalValueByWidth(200),
													   AppClient.proportionalValueByHeight(50), 
													   "", 
													   "Nom d'utilisateur");
													   connectUsernameTextField.setCornerRadius(3);
													   connectUsernameTextField.setAcceptSpace(false);
													   connectUsernameTextField.setMaxChars(20);

		connectPasswordTextField = new TextField(this, connectionPaneCenterX, 
													   connectionPaneCenterY,
													   AppClient.proportionalValueByWidth(200), 
													   AppClient.proportionalValueByHeight(50), 
													   "", 
													   "Mot de passe");
													   connectPasswordTextField.setCornerRadius(3);
													   connectPasswordTextField.setPassword(true);
													   connectPasswordTextField.setAcceptSpace(false);
													   connectPasswordTextField.setMaxChars(20);
													  
	   connectInfoLabel = new Label(this, connectionPaneCenterX, 
			   							   Math.round(connectionPaneCenterY + AppClient.proportionalValueByHeight(42)), 
			   							   AppClient.getFont(AppClient.getFonts().OpenSans_REGULAR_ITALIC, AppClient.proportionalValueByWidth(17)), 
			   							   "");
	   									   connectInfoLabel.setColor(ZennyColor.RED3);
													   
	   connectButton = new Button(this, connectionPaneCenterX,
									    connectionPaneCenterY + AppClient.proportionalValueByHeight(85), 
									    AppClient.proportionalValueByWidth(200),
									    AppClient.proportionalValueByHeight(50), 
									    "SE CONNECTER");
									    connectButton.setCornerRadius(3);
									    connectButton.setButtonColorAutomatic(ZennyColor.ORANGE1);
									    connectButton.setDisabled(true);
									    connectButton.setClickAction(new Runnable() {
									   	   @Override
										   public void run() {
									   		connectInfoLabel.setText("");
									   		   
											   String playerUsername = connectUsernameTextField.getText();
											   String password = ZennyHash.hash256(connectPasswordTextField.getText());
											
											   waitingWebServerResponse = true;
											   
											   String query = ZennyWebQuery.query("connect.php?playerUsername="+playerUsername+"&password="+password);
											   
											   waitingWebServerResponse = false;
											   
											   if (query.equals("query-error")) {
													connectInfoLabel.setText("Un problème est survenu");
												} else {
													if (query.equals("error")) {
														connectInfoLabel.setText("Nom d'utilisateur ou mot de passe incorrect");
											        } else {
											        	try {
															JSONObject connectionObject = (JSONObject) new JSONParser().parse(query);
															AppClient.enterScene(new Game(getApp(), connectionObject.get("playerIdentifier").toString()), gc);
														} catch (ParseException e) {
															connectInfoLabel.setText("Un problème est survenu");
														}
											        }
												}
									   	   }
									    });

		ComponentGroup connectGroup = new ComponentGroup(this, connectionPaneCenterX,
												connectionPaneCenterY - AppClient.proportionalValueByHeight(25), 
												AppClient.proportionalValueByWidth(350),
												AppClient.proportionalValueByHeight(300), 
												new Color(255, 255, 255, 0.2f));
												connectGroup.setCornerRadius(3);

		/* CONNECTION PANE */

		/* REGISTER PANE */
												
		float registerPaneCenterX = AppClient.WINDOW_WIDTH / 2 + AppClient.proportionalValueByWidth(200);
		float registerPaneCenterY = AppClient.WINDOW_HEIGHT / 2 + AppClient.proportionalValueByHeight(50);
				
		new Label(this, registerPaneCenterX, 
						registerPaneCenterY - AppClient.proportionalValueByHeight(140), 
				   		"INSCRIPTION");
		
		registerUsernameTextField = new TextField(this, registerPaneCenterX, 
													 	registerPaneCenterY - AppClient.proportionalValueByHeight(75), 
													 	AppClient.proportionalValueByWidth(200),
													 	AppClient.proportionalValueByHeight(50), 
													 	"", 
													 	"Nom d'utilisateur");
													 	registerUsernameTextField.setCornerRadius(3);
													 	registerUsernameTextField.setAcceptSpace(false);
													 	registerUsernameTextField.setMaxChars(20);
													 	
		registerMailTextField = new TextField(this, registerPaneCenterX,
													registerPaneCenterY,
													AppClient.proportionalValueByWidth(200),
													AppClient.proportionalValueByHeight(50),
													"",
													"E-mail");
													registerMailTextField.setCornerRadius(3);
													registerMailTextField.setAcceptSpace(false);
													registerMailTextField.setAcceptAt(true);
													registerMailTextField.setAcceptDot(true);
													
		registerPasswordTextField = new TextField(this, registerPaneCenterX,
													registerPaneCenterY + AppClient.proportionalValueByHeight(75),
													AppClient.proportionalValueByWidth(200),
													AppClient.proportionalValueByHeight(50),
													"",
													"Mot de passe");
													registerPasswordTextField.setCornerRadius(3);
													registerPasswordTextField.setPassword(true);
													registerPasswordTextField.setAcceptSpace(false);
													registerPasswordTextField.setMaxChars(20);
		
		registerPasswordConfirmTextField = new TextField(this, registerPaneCenterX,
														registerPaneCenterY + AppClient.proportionalValueByHeight(150),
														AppClient.proportionalValueByWidth(200),
														AppClient.proportionalValueByHeight(50),
														"",
														"Confirmation");
														registerPasswordConfirmTextField.setCornerRadius(3);
														registerPasswordConfirmTextField.setPassword(true);
														registerPasswordConfirmTextField.setAcceptSpace(false);
														registerPasswordConfirmTextField.setMaxChars(20);
														
		registerInfoLabel = new Label(this, registerPaneCenterX, 
				   						   Math.round(registerPaneCenterY + AppClient.proportionalValueByHeight(192)), 
				   						   AppClient.getFont(AppClient.getFonts().OpenSans_REGULAR_ITALIC, AppClient.proportionalValueByWidth(17)), 
										   "");
										   registerInfoLabel.setColor(ZennyColor.RED3);
													
		registerButton = new Button(this, registerPaneCenterX,
										  registerPaneCenterY + AppClient.proportionalValueByHeight(235), 
										  AppClient.proportionalValueByWidth(200),
										  AppClient.proportionalValueByHeight(50), 
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
												   
												String query = ZennyWebQuery.query("register.php?playerIdentifier="+UUID.randomUUID()+"&playerUsername="+registerUsernameTextField.getText()+"&password="+ZennyHash.hash256(registerPasswordTextField.getText())+"&mail="+registerMailTextField.getText());
												while (query.equals("playerIdentifier already exists")) 
													query = ZennyWebQuery.query("register.php?playerIdentifier="+UUID.randomUUID()+"&playerUsername="+registerUsernameTextField.getText()+"&password="+ZennyHash.hash256(registerPasswordTextField.getText())+"&mail="+registerMailTextField.getText());
												
												if (query.equals("playerUsername already exists")) {
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
			  									registerPaneCenterY + AppClient.proportionalValueByHeight(50), 
			  									AppClient.proportionalValueByWidth(350),
			  									AppClient.proportionalValueByHeight(450), 
			  									new Color(255, 255, 255, 0.2f));
												registerGroup.setCornerRadius(3);								  
										  
		/* REGISTER PANE */

		new ComponentGroup(this, AppClient.WINDOW_WIDTH / 2, 
							     AppClient.WINDOW_HEIGHT / 2, 
							     AppClient.WINDOW_WIDTH + 2,
							     AppClient.WINDOW_HEIGHT + 2, 
							     new GradientFill(0, 0, ZennyColor.DARKGREY1.getColor(), AppClient.WINDOW_WIDTH, AppClient.WINDOW_HEIGHT, ZennyColor.PURPLE2.getColor()));
		
	}

	@Override
	public void leaveScene(GameContainer gc, StateBasedGame sbg) {

	}

	@Override
	public void renderScene(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		/* HEADER */
		
		AppClient.getSprites().logoBig.drawWithNewDimension(0, -AppClient.proportionalValueByHeight(240), 
										 Math.round(AppClient.proportionalValueByWidth(AppClient.getSprites().logoBig.getWidth())), 
										 Math.round(AppClient.proportionalValueByHeight(AppClient.getSprites().logoBig.getHeight())));
		
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
