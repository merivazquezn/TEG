package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.jugador.IdentificadorInvalidoException;
import edu.fiuba.algo3.modelo.jugador.Signo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SignoTest {

    @Test
    public void test01SignoDevuelveIdentificadorInvalidoExceptionSiElIdentificadorNoEstaEntre0y2() {

        assertThrows(IdentificadorInvalidoException.class, () -> {
            Signo signo = new Signo(-2);
        });

        assertThrows(IdentificadorInvalidoException.class, () -> {
            Signo signo2 = new Signo(3);
        });

    }

    @Test
    public void test02dosSignosConIgualIdentificadorSonEfectivamenteIguales() {

        Signo unSigno = new Signo(1);
        Signo otroSigno = new Signo(1);

        assertTrue(unSigno.mismoSignoQue(otroSigno));
        assertTrue(otroSigno.mismoSignoQue(unSigno));
    }

    @Test
    public void test03dosSignosConDistintoIdentificadorDevuelveFalseAMismoSignoQue(){
        Signo unSigno = new Signo(0);
        Signo signoDistinto = new Signo(2);

        assertFalse(unSigno.mismoSignoQue(signoDistinto));
        assertFalse(signoDistinto.mismoSignoQue(unSigno));

    }

}
