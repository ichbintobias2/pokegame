package de.tobias.pokegame.frontend.enums;

import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.sound.Sound;

public class SoundControl {
	/*
	 * TODO this is a placeholder string which should be replaced when the correct sounds
	 * are added.
	 * The file test.wav is empty and only used to prevent Exceptions that occur when a
	 * sound is missing.
	 */
	private static final String placeholder = "sounds/test.wav";
	
	public static final Sound MenuMusic = Resources.sounds().get(placeholder);
	
	public static final Sound MenuOpen = Resources.sounds().get(placeholder);
	public static final Sound MenuClose = Resources.sounds().get(placeholder);
	
	public static final Sound MenuUpdate = Resources.sounds().get(placeholder);
	public static final Sound MenuConfirm = Resources.sounds().get(placeholder);
	
	public static final Sound Dialog = Resources.sounds().get(placeholder);
}
