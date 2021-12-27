package org.eda.packlaboratorio4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ListaActores {
    private HashMap<String,Actor> actores;

    //Constructora
    public ListaActores(){
        this.actores = new HashMap<String,Actor>();
    }

    //Otros métodos
    public Actor buscarActor(String pNombre){
            return actores.get(pNombre);
    }

    public void insertarActor(Actor pActor) {
            actores.put(pActor.getNombre(), pActor);
    }


    public void borrarActor(String pNombre){
        actores.remove(pNombre);
    }

    public HashMap<String,Actor> devolverActores(){
        return actores;
    }


    public boolean contiene(String pActor){
        return actores.containsKey(pActor);
    }


    public ArrayList<Actor> ordenarActores() {
        Collection<Actor> values = actores.values();
        ArrayList<Actor> arrayActores = new ArrayList<Actor>(values);
        return this.ordenarPorMezcla(arrayActores);
    }


    private ArrayList<Actor> ordenarPorMezcla(ArrayList<Actor> pArrayActores){
        ArrayList<Actor> izq = new ArrayList<Actor>();
        ArrayList<Actor> der = new ArrayList<Actor>();

        if (pArrayActores.size() == 1) {
            return pArrayActores;
        }
        else {
            int centro = pArrayActores.size()/2;
            for (int i=0; i<centro; i++) {
                izq.add(pArrayActores.get(i));
            }

            for (int j=centro; j<pArrayActores.size(); j++) {
                der.add(pArrayActores.get(j));
            }

            izq  = ordenarPorMezcla(izq);
            der = ordenarPorMezcla(der);

            mezcla(izq, der, pArrayActores);
        }
        return pArrayActores;

    }


    private void mezcla(ArrayList<Actor> izq, ArrayList<Actor> der, ArrayList<Actor> pArrayActores) {
        int indIzq = 0;
        int indDer = 0;
        int indGrande = 0;

        while ((indIzq < izq.size()) && (indDer < der.size())) {
            if ( (izq.get(indIzq).getNombre().compareToIgnoreCase(der.get(indDer).getNombre())) < 0) {
                pArrayActores.set(indGrande, izq.get(indIzq));
                indIzq++;
            } else {
                pArrayActores.set(indGrande, der.get(indDer));
                indDer++;
            }
            indGrande++;
        }

        ArrayList<Actor> resto;
        int indResto;
        if (indIzq >= izq.size()) {
            resto = der;
            indResto = indDer;
        } else {
            resto = izq;
            indResto = indIzq;
        }

        for (int k=indResto; k<resto.size(); k++) {
            pArrayActores.set(indGrande, resto.get(k));
            indGrande++;
        }
    }
}
