package xyz.chengzi.halma.model;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
public class Bgm {
    static URL url;
    static AudioClip ac;
    private static AudioClip ac1;
    private static URL url1;


    public static void Music_bgm() {
        File f2 = new File("bgm\\bgm.wav");
        try {
            url1 = f2.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ac1 = Applet.newAudioClip(url1);
        ac1.play();
        ac1.loop();
    }

    public static void Music_bgmstop() {
        ac1.stop();
    }


    public static void Music_pa() {
        File f1 = new File("bgm\\pa.wav");
        try {
            url = f1.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ac = Applet.newAudioClip(url);
        ac.play();
    }
    public static void Music_button() {
        File f1 = new File("bgm\\button.wav");
        try {
            url = f1.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ac = Applet.newAudioClip(url);
        ac.play();
    }
    public static void Music_win() {
        File f1 = new File("bgm\\win.wav");
        try {
            url = f1.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ac = Applet.newAudioClip(url);
        ac.play();
    }
    public static void Music_start() {
        File f1 = new File("bgm\\start.wav");
        try {
            url = f1.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ac = Applet.newAudioClip(url);
        ac.play();
    }



}


