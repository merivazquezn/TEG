package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.modelo.ataque.Ataque;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import java.util.*;



public class Tablero extends Observable {

    private final HashMap<String, Continente> continentes;
    private final ConstructorDeConjuntoDados constructorDeConjuntoDados;
    private final Mazo mazo;

    private Ataque ultimoAtaque;

    public Tablero(HashMap<String, Continente> continentes, ConstructorDeConjuntoDados constructorDeConjuntoDados, Mazo mazo) {
        this.continentes = continentes;
        this.constructorDeConjuntoDados = constructorDeConjuntoDados;
        this.mazo = mazo;
    }

    public boolean conquisto(Pais atacante, Pais defensor, int cantidad) {
        ultimoAtaque = new Ataque(this.constructorDeConjuntoDados, atacante, defensor, cantidad);
        boolean seProdujoConquista = ultimoAtaque.conquisto();
        setChanged();
        return seProdujoConquista;
    }

    public ArrayList<Integer> getUltimosDadosAtacante(){
        return this.ultimoAtaque.devolverValoresDadosAtacante();
    }

    public ArrayList<Integer> getUltimosDadosDefensor(){
        return this.ultimoAtaque.devolverValoresDadosDefensor();
    }

    public int cantidadEjercitosPorContinente(Jugador jugador) {
        int cantidadEjercitos = 0;
        for (Continente actual : this.continentes.values()){
            cantidadEjercitos += actual.ejercitosParaJugador(jugador);
        }

        return cantidadEjercitos;
    }
    public int cantidadPaisesDeJugadorEn(Jugador jugador, String continente){
        //Continente continente = this.continentes.get(continente);

        return this.continentes.get(continente).cantidadPaisesDeJugador(jugador);
    }

    public boolean continenteOcupadoPorJugador(Jugador jugador, String continente){
        return this.continentes.get(continente).ocupadoPorJugador(jugador);
    }

    public Mazo obtenerMazo() { return this.mazo; }

    

}
