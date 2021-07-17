package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.general.Continente;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.util.Parser;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void test01leerArchivoPaisesDevuelveFileNotFoundExceptionSiNoExisteElArchivo() {
        assertThrows(FileNotFoundException.class, () -> {
            Parser.leerArchivoPaises("archivoInexistente.csv");
        });

    }


    @Test
    public void test02leeArchivoDePaisesSinElevarExcepcion() {
        try {

            Parser.leerArchivoPaises("./src/main/test/edu/fiuba/algo3/modelo/test02parser.csv");
            assertTrue(true);
        } catch (Exception e) {
            assertFalse(true);
        }
    }


    @Test
    public void test03leeArchivoPaisesCorrectamente() throws IOException {
        ArrayList<ArrayList> devolucion = new ArrayList<ArrayList>();
        try {
            devolucion = Parser.leerArchivoPaises("./src/main/test/edu/fiuba/algo3/modelo/test1parser.csv");
        } catch (Exception e) {
            fail();
        }

        ArrayList<ArrayList> esperado = new ArrayList();
        ArrayList<String> linea1 = new ArrayList();
        ArrayList<String> linea2 = new ArrayList();
        ArrayList<String> linea3 = new ArrayList();

        linea1.add("Argentina");
        linea1.add("America del Sur");
        linea1.add("\"Uruguay;Brasil\"");

        linea2.add("Uruguay");
        linea2.add("America del Sur");
        linea2.add("\"Argentina;Brasil\"");

        linea3.add("Brasil");
        linea3.add("America del Sur");
        linea3.add("\"Argentina;Uruguay\"");

        esperado.add(linea1);
        esperado.add(linea2);
        esperado.add(linea3);

        if (devolucion.size() != esperado.size())
            fail();

        for (int i = 0; i < esperado.size(); i++) {
            for (int j = 0; j < 3; j++) {
                if (!(devolucion.get(i).get(j).equals(esperado.get(i).get(j)))) {
                    fail();
                }

                if (devolucion.get(i).size() != 3) {
                    for (int k = 0; k < 4; k++) {
                        System.out.println(devolucion.get(i).get(k));
                    }
                    System.out.println(devolucion.get(i));
                    fail();


                }
            }

            assertTrue(true);
        }

    }

    @Test
    public void test04ParsearPaisesDevuelveFileNotFoundExceptionSiNoExisteElArchivo() {
        assertThrows(FileNotFoundException.class, () -> {
            Parser.parserarPaises("archivoInexistente.csv");
        });

    }


    @Test
    public void test05parseaArchivoDelJuegoSinElevarExcepcion() {
        try {
            Parser.parserarPaises("./src/main/test/edu/fiuba/algo3/modelo/test02parser.csv");
            assertTrue(true);
        } catch (Exception e) {
            assertFalse(true);
        }
    }


    @Test
    public void test06ParsearPaisesleeArchivoPaisesCorrectamente() {
        ArrayList<ArrayList> devolucion = new ArrayList<ArrayList>();
        try {
            devolucion = Parser.parserarPaises("./src/main/test/edu/fiuba/algo3/modelo/test02parser.csv");
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }

        ArrayList<Pais> paises = devolucion.get(0);
        ArrayList<Continente> continentes = devolucion.get(1);

        String[] nombresPaises = {"Francia", "Tartaria", "Etiopia", "Australia", "Egipto", "Argentina",
                "China", "Alemania", "Mexico", "Sudafrica", "Japon", "Sumatra"};


        for (String nombrePais : nombresPaises) {
            boolean encontro = false;

            for (Pais unPais : paises) {
                if (unPais.getNombre().equals(nombrePais)) {
                    encontro = true;
                }
            }
            if (!encontro)
                fail();
        }

        assertTrue(true);
    }

}



