package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.infraestructura.IRandomizador;
import edu.fiuba.algo3.infraestructura.Randomizador;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Reproductor {
    public static void reproducirSonido(ArrayList<String> sonidos) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        IRandomizador randomizador = new Randomizador();

        int numeroAudio = randomizador.generar(0, sonidos.size());
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(sonidos.get(numeroAudio)));

        Clip clip = AudioSystem.getClip();
        clip.open(audioInput);
        clip.start();
    }


    public static void reproducirSonido(String sonido) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(sonido));

        Clip clip = AudioSystem.getClip();
        clip.open(audioInput);
        clip.start();
    }
}
