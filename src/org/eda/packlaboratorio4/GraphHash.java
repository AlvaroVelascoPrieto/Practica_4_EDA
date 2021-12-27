package org.eda.packlaboratorio4;

import java.util.*;

public class GraphHash {
    HashMap<String, ArrayList<String>> g;

    public void main(String[] args) {

        this.crearGrafo(ListaPeliculas.getListaPeliculas().devolverTodosLosActores());
    }

    public void crearGrafo(ListaActores lActores) {
        // Post: crea el grafo desde la lista de actores
        // Los nodos son nombres de actores
        // COMPLETAR CÓDIGO
        this.g = new HashMap<String, ArrayList<String>>();
        HashMap<String, Actor> actores = lActores.devolverActores();
        for (Map.Entry<String, Actor> entryAct : actores.entrySet()) {
            HashMap<String, String> colegas = new HashMap<String, String>();
            HashMap<String, Pelicula> peliculas = ListaPeliculas.getListaPeliculas().devolverPeliculas(entryAct.getKey());
            for (Map.Entry<String, Pelicula> entryPel : peliculas.entrySet()) {
                HashMap<String, Actor> actoresPeli = entryPel.getValue().devolverActores();
                for (Map.Entry<String, Actor> entryActPel : actoresPeli.entrySet()) {
                    if (!(colegas.containsKey(entryActPel.getKey())) && !Objects.equals(entryActPel.getKey(), entryAct.getKey())) {
                        colegas.put(entryActPel.getKey(), entryActPel.getKey());
                    }
                }
                g.put(entryAct.getKey(), new ArrayList<String>(colegas.values()));
            }
        }

    }


    public void print() {
        int i = 1;
        for (String s : g.keySet()) {
            System.out.print("Element: " + i++ + " " + s + " --> ");
            for (String k : g.get(s)) {
                System.out.print(k + " ### ");
            }
            System.out.println();
        }
    }

    public boolean estanConectados(String a1, String a2) {
        Stack<String> pila = new Stack<>();
        HashMap<String, String> visitado = new HashMap<String, String>();
        pila.push(a1);
        visitado.put(a1, a1);
        while (!pila.isEmpty()) {
            String aux = pila.pop();
            if (aux.equals(a2)) {
                return true;
            } else {
                for (String act : g.get(aux)) {
                    if (!visitado.containsKey(act)) {
                        pila.push(act);
                        visitado.put(act, act);
                    }
                }
            }
        }
        return false;
    }

    public void calcularConexiones(int n) {
        Random randomGenerator = new Random();
        Object[] a = g.keySet().toArray();

        for (int i = 1; i <= n; i++) {
            int x = randomGenerator.nextInt(g.size());
            int y = randomGenerator.nextInt(g.size());
            System.out.println("Prueba: " + i + " ");
            System.out.println(estanConectados((String) a[x], (String) a[y]));
        }
    }

    public HashMap<String, Double> pageRank() {
    // Post: el resultado es el valor del algoritmo PageRank para cada actor del grafo
        HashMap<String, HashSet<String>> estructura = new HashMap<String, HashSet<String>>();
        for (String nodo : g.keySet()){
            estructura.put(nodo, new HashSet<String>(g.get(nodo)));
        }
        HashMap<String, Double> pGList = new HashMap<String, Double>();
        Double sumAct = 1.0;
        int n = estructura.size();
        Double dampingFactor = 0.85;
        for (String act: estructura.keySet()){
            pGList.put(act, 1.0/n);
        }
        Double target = 0.0001;
        System.out.println(1);
        int itr = 0;
        while (sumAct> target){
            sumAct=0.0;
            System.out.println(itr);
            for (String evaluadoActual : pGList.keySet()){
                double sumatorio = 0.0;
                for (String nodo : estructura.get(evaluadoActual)){
                    sumatorio = sumatorio + (pGList.get(nodo) / (estructura.get(nodo).size()));
                }
                Double nuevoPR = ((1-dampingFactor)/n)+(dampingFactor*sumatorio);
                sumAct = sumAct + Math.abs(pGList.get(evaluadoActual)-nuevoPR);
                pGList.put(evaluadoActual,nuevoPR);
            }
            System.out.println(sumAct);
            itr++;
        }
        return pGList;
    }

    public ArrayList<Par> ordenarActoresPorPageRank() {
        HashMap<String, Double> pr = this.pageRank();
        ArrayList<Par> arrayActores = new ArrayList<Par>();
        for (String actor: pr.keySet()){
            Par act = new Par();
            act.actor = actor;
            act.pageRank = pr.get(actor);
            arrayActores.add(act);
        }
        return this.ordenarPorMezcla(arrayActores);
    }


    private ArrayList<Par> ordenarPorMezcla(ArrayList<Par> pArrayActores){
        ArrayList<Par> izq = new ArrayList<Par>();
        ArrayList<Par> der = new ArrayList<Par>();

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

    private void mezcla(ArrayList<Par> izq, ArrayList<Par> der, ArrayList<Par> pArrayActores) {
        int indIzq = 0;
        int indDer = 0;
        int indGrande = 0;

        while ((indIzq < izq.size()) && (indDer < der.size())) {
            if (izq.get(indIzq).pageRank>der.get(indDer).pageRank) {
                pArrayActores.set(indGrande, izq.get(indIzq));
                indIzq++;
            } else {
                pArrayActores.set(indGrande, der.get(indDer));
                indDer++;
            }
            indGrande++;
        }

        ArrayList<Par> resto;
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