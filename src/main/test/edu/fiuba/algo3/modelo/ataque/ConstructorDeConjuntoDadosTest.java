package edu.fiuba.algo3.modelo.ataque;

import edu.fiuba.algo3.infraestructura.Randomizador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ConstructorDeConjuntoDadosTest {
    @Test
    public void test01SiRecibe1OMenosAtacantesElevaError() {
        ConstructorDeConjuntoDados constructor = new ConstructorDeConjuntoDados(new Randomizador());

        assertThrows(CantidadInvalidaDeDadosError.class, () -> {
            constructor.obtenerConjuntosDados(0,3);
        });

    }
    @Test
    public void test02SiRecibe0DefensoresElevaError() {
        ConstructorDeConjuntoDados constructor = new ConstructorDeConjuntoDados(new Randomizador());

        assertThrows(CantidadInvalidaDeDadosError.class, () -> {
            constructor.obtenerConjuntosDados(3,0);
        });

    }

    @Test
    public void test03DevuelveConjuntoDadosDe2AtacantesY3DefensoresCorrectamente() {
        ConstructorDeConjuntoDados constructor = new ConstructorDeConjuntoDados(new Randomizador());

        ArrayList<ConjuntoDados> conjuntoDados = constructor.obtenerConjuntosDados(2,3);

        assertEquals(conjuntoDados.get(0).size(), 2);
        assertEquals(conjuntoDados.get(1).size(), 3);
    }

    @Test
    public void test04DevuelveConjuntoDadosDe3AtacantesY1DefensoresCorrectamente() {
        ConstructorDeConjuntoDados constructor = new ConstructorDeConjuntoDados(new Randomizador());

        ArrayList<ConjuntoDados> conjuntoDados = constructor.obtenerConjuntosDados(3, 1);

        assertEquals(conjuntoDados.get(0).size(), 3);
        assertEquals(conjuntoDados.get(1).size(), 1);
    }

}
