package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Signo;
import edu.fiuba.algo3.modelo.general.SignoComodin;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SignoComodinTest {

    @Test
    public void test01comodinEsIgualAOtroComodin() {
        Signo unSigno = new SignoComodin();
        Signo otroSigno = new SignoComodin();

        assertTrue(unSigno.mismoSignoQue(otroSigno));
        assertTrue(otroSigno.mismoSignoQue(unSigno));
    }

    @Test
    public void test02comodinEsIgualABarco() {
        Signo signoComodin = new SignoComodin();
        Signo signoNormal = new Signo(0);
        assertTrue(signoComodin.mismoSignoQue(signoNormal));
        assertTrue(signoNormal.mismoSignoQue(signoComodin));
    }

    @Test
    public void test03comodinEsIgualAGlobo() {
        Signo unSigno = new SignoComodin();
        Signo otroSigno = new Signo(1);
        assertTrue(unSigno.mismoSignoQue(otroSigno));
        assertTrue(otroSigno.mismoSignoQue(unSigno));
    }

}
