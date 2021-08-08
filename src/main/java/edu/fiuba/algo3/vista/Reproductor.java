package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.infraestructura.IRandomizador;
import edu.fiuba.algo3.infraestructura.Randomizador;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Reproductor {

    static private Clip musica = null;

    private static void _reproducirNuevaMusica(String rutaMusica) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (musica != null) {
            musica.stop();
        }

        AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(rutaMusica));

        musica = AudioSystem.getClip();
        musica.loop(Clip.LOOP_CONTINUOUSLY);

        musica.open(audioInput);
        musica.start();
    }

    public static void reproducirNuevaMusica(String rutaMusica) {
        try {
            _reproducirNuevaMusica(rutaMusica);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

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
