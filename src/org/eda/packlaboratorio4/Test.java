package org.eda.packlaboratorio4;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        String direccion = System.getProperty("user.dir") + "\\src\\org\\eda\\packlaboratorio4\\FilmsActors20212022-20000-first-lines.txt";
        ListaPeliculas.getListaPeliculas().lectorArchivo(direccion);
        //GraphHash miGraphHash = new GraphHash();
        //Stopwatch reloj = new Stopwatch();
        //miGraphHash.crearGrafo(ListaPeliculas.getListaPeliculas().devolverTodosLosActores());
        //System.out.println(miGraphHash.estanConectados("Castillo, K.C.", "Apacible, John"));
        //double tiempo = reloj.elapsedTime();
        //System.out.println(tiempo);
        // Crear grafo

        Stopwatch timer = new Stopwatch();
        GraphHash miGraphHash = new GraphHash();
        miGraphHash.crearGrafo(ListaPeliculas.getListaPeliculas().devolverTodosLosActores());
        System.out.println(" Time (crearGrafo): " + timer.elapsedTime());

        Stopwatch timer2 = new Stopwatch();
        //System.out.println(miGraphHash.estanConectados("Banderas, Antonio", "DiCaprio, Leonardo"));
        ArrayList<Par> res = miGraphHash.ordenarActoresPorPageRank();
        double tiempo = timer2.elapsedTime();
        System.out.println(" Time (calcularConexiones(50)): " + tiempo);
        System.out.println(" Tiempo medio calcularConexiones: " + tiempo/500);
        //for (Par act : res){
        //    System.out.println(act.actor + " pr: " + act.pageRank);
        //}
    }


}

