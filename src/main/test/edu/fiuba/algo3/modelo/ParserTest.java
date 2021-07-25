package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.general.Continente;
import edu.fiuba.algo3.modelo.general.Pais;
import edu.fiuba.algo3.modelo.jugador.IdentificadorInvalidoException;
import edu.fiuba.algo3.modelo.general.Signo;
import edu.fiuba.algo3.modelo.general.SignoComodin;
import edu.fiuba.algo3.modelo.general.Tarjeta;
import edu.fiuba.algo3.infraestructura.Parser;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void test01leerArchivoPaisesDevuelveFileNotFoundExceptionSiNoExisteElArchivo() {
        assertThrows(FileNotFoundException.class, () -> {
            Parser.leerArchivo("archivoInexistente.csv");
        });

    }

    @Test
    public void test02leeArchivoDePaisesSinElevarExcepcion() {
        try {

            Parser.leerArchivo("./src/main/test/edu/fiuba/algo3/modelo/test02parser.csv");
            assertTrue(true);
        } catch (Exception e) {
            assertFalse(true);
        }
    }


    @Test
    public void test03leeArchivoPaisesCorrectamente() throws IOException {
        ArrayList<ArrayList> devolucion = new ArrayList<ArrayList>();
        try {
            devolucion = Parser.leerArchivo("./src/main/test/edu/fiuba/algo3/modelo/test1parser.csv");
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
            Parser.parsearPaisesParaTablero("archivoInexistente.csv");
        });

    }


    @Test
    public void test05parseaArchivoDePaisesSinElevarExcepcion() {
        try {
            Parser.parsearPaisesParaTablero("./src/main/test/edu/fiuba/algo3/modelo/test02parser.csv");
            assertTrue(true);
        } catch (Exception e) {
            assertFalse(true);
        }
    }


    @Test
    public void test06ParsearPaisesleeArchivoPaisesCorrectamente() {
        ArrayList<HashMap> devolucion = new ArrayList<HashMap>();
        try {
            devolucion = Parser.parsearPaisesParaTablero("./src/main/test/edu/fiuba/algo3/modelo/test02parser.csv");
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }

        HashMap<String, Pais> paises = devolucion.get(0);
        HashMap<String, Continente> continentes = devolucion.get(1);

        String[] nombresPaises = {"Francia", "Tartaria", "Etiopia", "Australia", "Egipto", "Argentina",
                "China", "Alemania", "Mexico", "Sudafrica", "Japon", "Sumatra"};


        for (String nombrePais : nombresPaises) {

            assertTrue(paises.containsKey(nombrePais));

            Pais paisEncontrado = paises.get(nombrePais);

            assertEquals(paisEncontrado.getNombre(), nombrePais);

        }
    }

    @Test
    public void test07ParsearPaisesAsignaCorrectamenteLosPaisesALosContinentes() {
        ArrayList<HashMap> devolucion = new ArrayList<HashMap>();
        try {
            devolucion = Parser.parsearPaisesParaTablero("./src/main/test/edu/fiuba/algo3/modelo/test02parser.csv");
        } catch (Exception e) {
            fail(e.getMessage());
        }

        HashMap<String, Continente> hashContinentes = devolucion.get(1);

        ArrayList<Pais> paisesOceania = hashContinentes.get("Oceania").getListaPaises();

        HashSet<String> setNombrePaises = new HashSet<String>();

        setNombrePaises.add("Australia");
        setNombrePaises.add("Java");
        setNombrePaises.add("Borneo");
        setNombrePaises.add("Sumatra");

        for (Pais pais : paisesOceania) {
            assertTrue(setNombrePaises.contains(pais.getNombre()));

            setNombrePaises.remove(pais.getNombre());
        }

        assertTrue(setNombrePaises.isEmpty());
    }



    @Test
    public void test08ObtenerSignoDevuelveSignoConElIdCorrectoSegunString() {
        String string1 = "barco";
        String string2 = "globo";
        String string3 = "cañon";
        String string4 = "comodin";


        assertEquals(Parser.obtenerSigno(string1).getIdentificador(), 0);
        assertEquals(Parser.obtenerSigno(string2).getIdentificador(), 1);
        assertEquals(Parser.obtenerSigno(string3).getIdentificador(), 2);
        assertEquals(Parser.obtenerSigno(string4).getIdentificador(), -1);
    }

    @Test
    public void test09ObtenerSignoElevaIdentificadorInvalidoSiRecibeStringInvalido() {
        assertThrows(IdentificadorInvalidoException.class, () -> {
            Signo signo = Parser.obtenerSigno("Saracatunga");
        });
    }


    @Test
    public void test10ParsearTarjetasDevuelveFileNotFoundExceptionSiNoExisteElArchivo() {
        assertThrows(FileNotFoundException.class, () -> {
            Parser.parsearPaisesParaTablero("archivoInexistente.csv");
        });

    }

    @Test
    public void test11parseaArchivoDelJuegoSinElevarExcepcion() {
        HashMap<String, Pais> hashPaises = new HashMap<String, Pais>();

        hashPaises.put("Terranova", new Pais("Terranova"));
        hashPaises.put("Argentina", new Pais("Argentina"));
        hashPaises.put("Iran", new Pais("Iran"));
        hashPaises.put("Madagascar", new Pais("Madagascar"));


        try {
            Parser.parsearTarjetas("./src/main/test/edu/fiuba/algo3/modelo/test3parsertarjetas.csv", hashPaises);
            assertTrue(true);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test12leerArchivoDePruebaTarjetasGeneraLasTarjetasCorrectas() {
        HashMap<String, Pais> hashPaises = new HashMap<String, Pais>();

        hashPaises.put("Terranova", new Pais("Terranova"));
        hashPaises.put("Argentina", new Pais("Argentina"));
        hashPaises.put("Iran", new Pais("Iran"));
        hashPaises.put("Madagascar", new Pais("Madagascar"));

        HashMap<String, Signo> signoDePais = new HashMap<String, Signo>();

        signoDePais.put("Argentina", new SignoComodin());
        signoDePais.put("Madagascar", new Signo(0));
        signoDePais.put("Iran", new Signo(1));
        signoDePais.put("Terranova", new Signo(2));

        ArrayList<Tarjeta> arrayTarjetas = new ArrayList<Tarjeta>();

        try {
            arrayTarjetas = Parser.parsearTarjetas("./src/main/test/edu/fiuba/algo3/modelo/test3parsertarjetas.csv", hashPaises);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        for (Tarjeta tarjeta : arrayTarjetas) {
            String nombrePais = tarjeta.getPais().getNombre();
            assertTrue(tarjeta.obtenerSigno().compararIdentificador(signoDePais.get(nombrePais)));
        }
    }
}



