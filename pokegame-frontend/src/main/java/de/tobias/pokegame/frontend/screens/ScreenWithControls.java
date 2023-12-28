package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.input.Input;
import de.tobias.pokegame.frontend.entities.Player;

import java.awt.event.KeyEvent;

/**
 * This is intended to be a super class to any {@code GameScreen} implementation that needs handlers for {@code KeyEvent}s that are only
 * used while the screen is active.
 */
class ScreenWithControls extends GameScreen {
	
	protected long lastMenuInput;
	protected int MENU_DELAY = 180;
	
	protected int currentIndex = 0;
	
	protected ScreenWithControls(String name) {
		super(name);
	}
	
	/**
	 * Handle an up key press with a given method (only if the screen is enabled/visible/etc.)
	 * @param highestMenuIndex the highest menu index that is allowed before it gets reset to zero again
	 * @param handlerMethod void method to execute when the key is pressed
	 */
	protected void handleUpKeyPressed(int highestMenuIndex, Runnable handlerMethod) {
		handleKeyPressed(KeyEvent.VK_UP, () -> {
			if (currentIndex == 0) {
				currentIndex = highestMenuIndex;
			} else {
				currentIndex--;
			}
			handlerMethod.run();
		});
	}
	
	/**
	 * Handle a down key press with a given method (only if the screen is enabled/visible/etc.)
	 * @param highestMenuIndex the highest menu index that is allowed before it gets reset to zero again
	 * @param handlerMethod void method to execute when the key is pressed
	 */
	protected void handleDownKeyPressed(int highestMenuIndex, Runnable handlerMethod) {
		handleKeyPressed(KeyEvent.VK_DOWN, () -> {
			if (currentIndex == highestMenuIndex) {
				currentIndex = 0;
			} else {
				currentIndex++;
			}
			handlerMethod.run();
		});
	}
	
	/**
	 * Handle any {@code KeyEvent} with a given method (only if the screen is enabled/visible/etc.)
	 * @param key {@code KeyEvent} to respond to
	 * @param handlerMethod void method to execute when the key is pressed
	 */
	protected void handleKeyPressed(int key, Runnable handlerMethod) {
		Input.keyboard().onKeyPressed(key, e -> {
			if (this.isMenuLocked()) {
				return;
			}
			handlerMethod.run();
			lastMenuInput = Game.time().now();
		});
	}
	
	/**
	 * Checks if the menu is currently locked. If the screen is invisible/disabled/etc. or if the method cooldown hasn't passed yet
	 * no methods can be executed.
	 * @return {@code true} if the menu is locked or {@code false} if not
	 */
	protected boolean isMenuLocked() {
		if (this.isSuspended() || !this.isVisible() || !this.isEnabled()) {
			return true;
		}
		return Game.time().since(lastMenuInput) < MENU_DELAY;
	}
}
