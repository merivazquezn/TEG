package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.infraestructura.Parser;
import edu.fiuba.algo3.modelo.general.Continente;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.general.Pais;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TestArchivoPaises {
    @Test
    public void test01CadaParDePaisesSonONoLimitrofesEntreSi() {
        String rutaPaises = "./src/data/paises.csv";
        String rutaObjetivos = "./src/data/objetivos.csv";
        String rutatarjetas = "./src/data/cartas.csv";

        Parser parser = new Parser(rutaPaises, rutaObjetivos, rutatarjetas);
        HashMap<String, Pais> paises = parser.getPaisesParaTablero();

        boolean pasoCorrecto = true;

        for (Pais pais1 : paises.values()) {
            for (Pais pais2 : paises.values()) {

                if (pais1.sonLimitrofes(pais2) != pais2.sonLimitrofes(pais1)) {
                    pasoCorrecto = false;
                }
            }
        }

        assertTrue(pasoCorrecto);
    }

}
