package edu.fiuba.algo3.util;

import edu.fiuba.algo3.modelo.general.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.lang.*;


public class Parser {
    private static String rutaArchivoPaises = "./src/main/java/edu/fiuba/algo3/util/paises.csv";
    // archivo cartas


    public static ArrayList<ArrayList> leerArchivoPaises(String rutaArchivo) throws IOException {
        /*
        Devuelve una ArrayList con las lineas leidas en listas
         */
        ArrayList<ArrayList> paisesCompleto = new ArrayList<ArrayList>();

        try {
			BufferedReader csvReader = new BufferedReader(new FileReader(rutaArchivo));
			String row = "";

			while ((row = csvReader.readLine()) != null) {
    		    ArrayList<String> linea = new ArrayList<String>();
    			String[] data = row.split(",");

                Collections.addAll(linea, data);
                paisesCompleto.add(linea);
    		}
			csvReader.close();
		}
		catch(Exception e) {
            System.out.println(e);
            throw e;
		}
        return paisesCompleto;
	}

    public static ArrayList parserarPaises(String rutaArchivo) throws IOException {
        /*
        *
        * Devuelve un ArrayList donde en la posicion 0 tiene un ArrayList con la lista de paises
        * y en la posicion 1 una lista de paises
        * * */
        ArrayList<ArrayList> datosPaisesCompleto;
        datosPaisesCompleto = Parser.leerArchivoPaises(rutaArchivo);

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

        ArrayList<Continente> listaContinentes = new ArrayList<Continente>();

        HashMap<String, Integer> ejercitosPorContinentes = new HashMap<String, Integer>();
        ejercitosPorContinentes.put("America del Sur", 3);
        ejercitosPorContinentes.put("America del Norte", 5);
        ejercitosPorContinentes.put("Europa", 5);
        ejercitosPorContinentes.put("Asia", 7);
        ejercitosPorContinentes.put("Africa", 3);
        ejercitosPorContinentes.put("Oceania", 2);

        hashContinentesConPaises.forEach((key,value) -> {

                    listaContinentes.add(new Continente(value, key, ejercitosPorContinentes.get(key)));
                });

        ArrayList<Pais> listaPaises = new ArrayList<Pais>(paises.values());

        ArrayList<ArrayList> devolucion = new ArrayList<ArrayList>();
        devolucion.add(listaPaises);
        devolucion.add(listaContinentes);

        return devolucion;
    }

}