package org.eda.packlaboratorio4;

import java.util.HashMap;

public class Pelicula {
    private double recaudacion;
    private ListaActores actores;
    private String nombre;

    //Constructora
    public Pelicula(String pNombre, float pRecaudacion){
        this.actores = new ListaActores();
        this.nombre = pNombre;
        this.recaudacion = pRecaudacion;
    }


    //Métodos
    public void incrementarRecaudacion(double pImporte) {
        this.recaudacion = this.recaudacion + pImporte;
    }

    public void borrarActor(String pActor){
        this.actores.borrarActor(pActor);
    }

    public void insertarActor(Actor pActor) { actores.insertarActor(pActor); }

    public HashMap<String ,Actor> devolverActores (){
        return actores.devolverActores();
    }


    //Metodos para las pruebas
    public double devolverRecaudacion(){
        return this.recaudacion;
    }

    public String getTitulo(){
        return this.nombre;
    }
}
