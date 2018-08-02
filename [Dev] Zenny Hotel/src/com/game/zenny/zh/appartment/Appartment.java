package com.game.zenny.zh.appartment;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.game.zenny.zh.Camera;
import com.game.zenny.zh.util.ZennyMouse;

public class Appartment {

	private AppartmentStructure structure;

	//// CONSTRUCTORS

	public Appartment() {
		structure = new AppartmentStructure();
	}

	/**
	 * @param structure
	 */
	public Appartment(AppartmentStructure structure) {
		this.structure = structure;
	}

	//// GETTERS AND SETTERS

	/**
	 * @return the structure
	 */
	public AppartmentStructure getStructure() {
		return structure;
	}

	/**
	 * @param structure
	 *            the structure to set
	 */
	public void setStructure(AppartmentStructure structure) {
		this.structure = structure;
	}

	//// GAME METHODS

	/**
	 * @param gc
	 * @param sbg
	 * @param g
	 * @throws SlickException
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		structure.render(gc, sbg, g);
	}
	
	/**
	 * @param gc
	 * @param sbg
	 * @param delta
	 * @throws SlickException
	 */
	private ResetCamMovement rcm;
	private boolean hasMoved = false;
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (Mouse.isButtonDown(1)) {
			if (!hasMoved) {
				hasMoved = true;
				
				if (rcm != null) {
					rcm.running = false;
					Camera.setResetingRelativePos(false);
					rcm = new ResetCamMovement();
				}
			}
			
			Camera.subRelativeX(Math.round(0.05f * ZennyMouse.getDX() * delta));
			Camera.addRelativeY(Math.round(0.05f * ZennyMouse.getDY() * delta));
		} else {
			if (hasMoved) {
				if (rcm == null)
					rcm = new ResetCamMovement();
				
				hasMoved = false;
				
				rcm.running = true;
				rcm.start();
			}
			
			if (Camera.isResetingRelativePos())
				Camera.resetRelativePos();
		}

		structure.update(gc, sbg, delta);
	}

	class ResetCamMovement extends Thread {
		
		public volatile boolean running = false;
		
		private int c = 1;
		@Override
		public void run() {
			while (running) {
				if (c == 100)
					Camera.resetRelativePos();
				
				c++;
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
