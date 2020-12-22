package de.tobias.pokegame.frontend.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.Menu;
import de.gurkenlabs.litiengine.input.Input;
import de.tobias.pokegame.frontend.enums.SoundControl;

public class KeyboardMenu extends Menu {
	private final Font MENU_FONT = null;
	private final Color BUTTON_RED = new Color(140, 16, 16, 200);
	private final Color BUTTON_BLACK = new Color(0, 0, 0, 200);
	
	private final int MENU_DELAY = 180;

	private final List<Consumer<Integer>> confirmConsumer;
	private int currentFocus = -1;

	private long lastMenuInput;

	protected KeyboardMenu(double x, double y, double width, double height, String... items) {
		super(x, y, width, height, items);
		this.confirmConsumer = new CopyOnWriteArrayList<>();

		Input.keyboard().onKeyReleased(e -> {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (this.isMenuLocked()) {
					return;
				}

				this.confirm();
				lastMenuInput = Game.time().now();
			}
		});

		Input.keyboard().onKeyPressed(KeyEvent.VK_UP, e -> {
			if (this.isMenuLocked()) {
				return;
			}

			decFocus();
		});

		Input.keyboard().onKeyPressed(KeyEvent.VK_DOWN, e -> {
			if (this.isMenuLocked()) {
				return;
			}

			incFocus();
		});
		
		onConfirm(c -> {
			Game.audio().playSound(SoundControl.MenuConfirm);
		});
	}

	@Override
	public void prepare() {
		super.prepare();
		this.setForwardMouseEvents(false);
		this.getCellComponents().forEach(comp -> comp.setForwardMouseEvents(false));

		if (!this.getCellComponents().isEmpty()) {
			this.currentFocus = 0;
			this.getCellComponents().get(0).setHovered(true);
		}

		this.getCellComponents().forEach(comp -> {
			comp.setFont(MENU_FONT);

			comp.getAppearance().setBackgroundColor1(BUTTON_BLACK);
			comp.getAppearance().setTransparentBackground(false);
			comp.getAppearance().setTextAntialiasing(true);
			
			comp.getAppearanceHovered().setBackgroundColor1(BUTTON_RED);
			comp.getAppearanceHovered().setTransparentBackground(false);
			comp.getAppearanceHovered().setTextAntialiasing(true);
		});
	}

	protected void onConfirm(Consumer<Integer> cons) {
		this.confirmConsumer.add(cons);
	}

	private void confirm() {
		for (Consumer<Integer> cons : this.confirmConsumer) {
			cons.accept(this.currentFocus);
		}
	}
	
	private boolean isMenuLocked() {
		if (this.isSuspended() || !this.isVisible() || !this.isEnabled()) {
			return true;
		}

		return Game.time().since(lastMenuInput) < MENU_DELAY;
	}

	private void decFocus() {
		this.currentFocus = Math.floorMod(--this.currentFocus, this.getCellComponents().size());
		this.updateFocus();
	}

	private void incFocus() {
		this.currentFocus = ++this.currentFocus % this.getCellComponents().size();
		this.updateFocus();
	}

	private void updateFocus() {
		this.setCurrentSelection(this.currentFocus);
		for (int i = 0; i < this.getCellComponents().size(); i++) {
			this.getCellComponents().get(i).setHovered(i == this.currentFocus);
		}

		lastMenuInput = Game.time().now();

		if (this.isVisible()) {
			Game.audio().playSound(SoundControl.MenuUpdate);
		}
	}
}
