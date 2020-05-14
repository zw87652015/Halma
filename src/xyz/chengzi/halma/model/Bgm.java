package xyz.chengzi.halma.model;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
public class Bgm {
    static URL url;
    static AudioClip ac;

    public static void Music_bgm() {
        File f1 = new File("doudizhubgm.wav");
        try {
            url = f1.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ac = Applet.newAudioClip(url);
        ac.play();
        ac.loop();
    }
    public static void Music_pa() {
        File f1 = new File("pa.wav");
        try {
            url = f1.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ac = Applet.newAudioClip(url);
        ac.play();
    }
    public static void Music_button() {
        File f1 = new File("button.wav");
        try {
            url = f1.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ac = Applet.newAudioClip(url);
        ac.play();
    }
    public static void Music_win() {
        File f1 = new File("win.wav");
        try {
            url = f1.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ac = Applet.newAudioClip(url);
        ac.play();
    }
    public static void Music_start() {
        File f1 = new File("start.wav");
        try {
            url = f1.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ac = Applet.newAudioClip(url);
        ac.play();
    }



}


