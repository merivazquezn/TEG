package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.infraestructura.Parser;
import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.general.*;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class IntegracionTest {

    @Test
    public void test01(){
        HashMap<String, Continente> continentes;
        HashMap<String, Pais> paises;
        try {
            String ruta = "./src/main/java/edu/fiuba/algo3/infraestructura/paises.csv";
            ArrayList<HashMap> listaParser = Parser.parsearPaisesParaTablero(ruta);
            continentes = listaParser.get(1);
            paises = listaParser.get(0);
            HashMap<Pais, int[]> vistaPaises = Parser.parsearPaisesParaVista(ruta, paises);
            Tablero tablero = new Tablero(continentes, new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
            ListaJugadores listaJugadores = new ListaJugadores(2, new Randomizador());
            Ronda ronda = new Ronda(tablero, listaJugadores);

        }
        catch(IOException e){
            fail();
        }


    }

}
