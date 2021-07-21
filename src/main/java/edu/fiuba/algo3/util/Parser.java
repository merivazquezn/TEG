package edu.fiuba.algo3.util;

import edu.fiuba.algo3.modelo.general.*;
import edu.fiuba.algo3.modelo.jugador.Signo;
import edu.fiuba.algo3.modelo.jugador.SignoComodin;
import edu.fiuba.algo3.modelo.jugador.Tarjeta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.SwitchPoint;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.lang.*;
import java.util.Locale;


public class Parser {
    private static String rutaArchivoPaises = "./src/main/java/edu/fiuba/algo3/util/paises.csv";
    // archivo cartas


    public static ArrayList<ArrayList> leerArchivo(String rutaArchivo) throws IOException {
        /*
        Devuelve una ArrayList con las lineas leidas en listas
         */
        ArrayList<ArrayList> lineasArchivo = new ArrayList<ArrayList>();

        try {
			BufferedReader csvReader = new BufferedReader(new FileReader(rutaArchivo));
			String row = "";

			while ((row = csvReader.readLine()) != null) {
    		    ArrayList<String> linea = new ArrayList<String>();
    			String[] data = row.split(",");

                Collections.addAll(linea, data);
                lineasArchivo.add(linea);
    		}
			csvReader.close();
		}
		catch(Exception e) {
            System.out.println(e);
            throw e;
		}
        return lineasArchivo;
	}

    public static ArrayList<HashMap> parserarPaises(String rutaArchivo) throws IOException {
        /*
        *
        * Devuelve un ArrayList donde en la posicion 0 tiene un Hash con los paises
        * y en la posicion 1 una Hash de los continentes
        * * */
        ArrayList<ArrayList> datosPaisesCompleto;
        datosPaisesCompleto = Parser.leerArchivo(rutaArchivo);

        HashMap<String, Pais> paises = new HashMap<String, Pais>();

        HashMap<String, ArrayList<Pais>> hashContinentesConPaises = new HashMap<String, ArrayList<Pais>>();
        hashContinentesConPaises.put("America del Sur", new ArrayList());
        hashContinentesConPaises.put("Europa", new ArrayList());
        hashContinentesConPaises.put("America del Norte", new ArrayList());
        hashContinentesConPaises.put("Asia", new ArrayList());
        hashContinentesConPaises.put("Oceania", new ArrayList());
        hashContinentesConPaises.put("Africa", new ArrayList());


        for(ArrayList<String> linea : datosPaisesCompleto) {

            Pais pais = new Pais(linea.get(0));

            paises.putIfAbsent(linea.get(0), pais);
            hashContinentesConPaises.get(linea.get(1)).add(pais);
        }


        for(ArrayList<String> linea : datosPaisesCompleto) {

            String stringLimitrofesConComillas = linea.get(2);
            String stringLimitrofes = stringLimitrofesConComillas.substring(1, stringLimitrofesConComillas.length() - 1);

            //Genera la lista limitrofes que contiene el nombre de los paises limitrofes
            String[] data = stringLimitrofes.split(";");
            ArrayList<String> limitrofes = new ArrayList<String>();
            Collections.addAll(limitrofes, data);

            String paisKey = linea.get(0);
            for(String nombreLimitrofe : limitrofes) {
                Pais paisAAgregarLimitrofe = paises.get(paisKey);
                Pais limitrofe = paises.get(nombreLimitrofe);

                paisAAgregarLimitrofe.agregarLimitrofe(limitrofe);
            }
        }

        HashMap<String, Continente> hashContinentes = new HashMap<String, Continente>();

        HashMap<String, Integer> ejercitosPorContinentes = new HashMap<String, Integer>();
        ejercitosPorContinentes.put("America del Sur", 3);
        ejercitosPorContinentes.put("America del Norte", 5);
        ejercitosPorContinentes.put("Europa", 5);
        ejercitosPorContinentes.put("Asia", 7);
        ejercitosPorContinentes.put("Africa", 3);
        ejercitosPorContinentes.put("Oceania", 2);

        hashContinentesConPaises.forEach((key,value) -> {
                    String nombreContinente = key;
                    ArrayList<Pais> listaPaisesEnContinente = value;

                    hashContinentes.put(nombreContinente, new Continente(listaPaisesEnContinente, nombreContinente, ejercitosPorContinentes.get(nombreContinente)));
                });


        //ArrayList<Pais> listaPaises = new ArrayList<Pais>(paises.values());

        ArrayList<HashMap> devolucion = new ArrayList<HashMap>();
        devolucion.add(paises);
        devolucion.add(hashContinentes);

        return devolucion;
    }

    public static Signo obtenerSigno(String stringSigno) {
        Signo signo;

        switch (stringSigno) {
            case "barco":
                return new Signo(0);

            case "globo":
                return new Signo(1);

            case "cañon":
                return new Signo(2);

            case "comodin":
                return new SignoComodin();
        }

        return new Signo(-10);
    }


    public static ArrayList parsearTarjetas(String rutaArchivoTarjetas, HashMap<String, Pais> paises) throws IOException {
        ArrayList<ArrayList> datosTarjetrasCompleto = Parser.leerArchivo(rutaArchivoTarjetas);

        ArrayList<Tarjeta> arrayTarjetas = new ArrayList<Tarjeta>();

        for (ArrayList<String> linea : datosTarjetrasCompleto) {
            Pais paisActual = paises.get(linea.get(0));
            Signo signoActual = Parser.obtenerSigno(linea.get(1).toLowerCase());


            arrayTarjetas.add(new Tarjeta(paisActual, signoActual));
        }

        return arrayTarjetas;
    }

}