package me.dilan.game.util;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class SoundPlayer {

	private static Clip clip;
	private static FloatControl volume;
	
	public static void playEffect(String name) {
		AudioInputStream audioIn;
		try {
			audioIn = AudioSystem.getAudioInputStream(new File("res/sounds/" + name + ".wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public static void musicPlay(String name) {


		AudioInputStream audioIn;
		try {
			audioIn = AudioSystem.getAudioInputStream(new File("res/sounds/" + name + ".wav"));
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		

	}
	
	public static void musicVolume(float vol) {
        volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        if (volume.getMinimum() + vol <= volume.getMaximum()) {
            volume.setValue(volume.getMinimum());
            volume.setValue(volume.getMinimum() + vol);
        }
    }
	
	public static void musicLoop(int count) {
		clip.loop(count);
	}

}
