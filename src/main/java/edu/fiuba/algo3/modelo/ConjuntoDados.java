package edu.fiuba.algo3.modelo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class ConjuntoDados implements IConjuntoDados{
    private ArrayList<Dado> dados;


    private boolean cantidadDeDadosValida(int cantidad){
        return (cantidad >= 1 && cantidad <= 3);
    }

    public void generar(int cantidad, IRandomizador randomizador){
        if (!cantidadDeDadosValida(cantidad))
            throw new CantidadInvalidaDeDadosError("Cantidad inválida de Dados");
        this.dados = new ArrayList<Dado>();
        for(int i =0; i<cantidad; i++) {
            this.dados.add(new Dado(randomizador));
        }
        Collections.sort(dados, Collections.reverseOrder());
    }

    public int size(){
        return this.dados.size();
    }

    public ArrayList<Dado> obtenerDados(){
        return this.dados;
    }

    // El primer valor devuelto son las fichas perdidas por el mismo objeto,
    // el segundo valor devuelto son las fichas perdidas por el segundo objeto
    public ArrayList<Integer> compararCon(IConjuntoDados conjunto2){
        ArrayList<Dado> defensor = conjunto2.obtenerDados();

        int perdidosDefensor = 0;
        int perdidosAtacante = 0;

        for(int i = 0; i < defensor.size() && i < this.dados.size(); i++){
            if(this.dados.get(i).compareTo(defensor.get(i)) == Dado.DADO_GANO){
                perdidosDefensor++;
            }
            else {
                perdidosAtacante++;
            }
        }
        ArrayList<Integer> lista = new ArrayList<Integer>();
        lista.add(perdidosAtacante);
        lista.add(perdidosDefensor);
        return lista;
    }
    
}
