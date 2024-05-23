package nidal.lahlou.m03_uf3_pp_lahlou_vera.Classes;

import ion.Vera.Fitxers;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ion Vera i Nidal Lahlou
 */
public class Producte implements Serializable {

    static private final Fitxers f = new Fitxers();

    static private final String fitxerProductes = "productes.dat";

    String nomProducte;
    String preuProducte;
    String descProducte;
    String dataProducte;
    String codiBarras;

    //constructors

    public Producte() {
    }

    public Producte(String nomProducte, String preuProducte, String descProducte, String dataProducte, String codiBarras) {
        this.nomProducte = nomProducte;
        this.preuProducte = preuProducte;
        this.descProducte = descProducte;
        this.dataProducte = dataProducte;
        this.codiBarras = codiBarras;
    }

    //getters
    public String getNomProducte() {
        return nomProducte;
    }

    public String getPreuProducte() {
        return preuProducte;
    }

    public String getDescProducte() {
        return descProducte;
    }

    public String getDataProducte() {
        return dataProducte;
    }

    public String getCodiBarras() {
        return codiBarras;
    }

    public String getFitxerProductes(){
        return fitxerProductes;
    }

    @Override
    public String toString() {
        return "Producte{" +
                "nomProducte='" + nomProducte + '\'' +
                ", preuProducte=" + preuProducte +
                ", descProducte='" + descProducte + '\'' +
                ", dataProducte='" + dataProducte + '\'' +
                ", codiBarras='" + codiBarras + '\'' +
                '}';
    }

    //metodes


    /**
     * Guarda el producte (objecte) en un fitxer
     * @param fitxer el fitxer on vols guardar la producte
     * @throws IOException
     */
    public void guardaProducte(String fitxer) throws IOException {
        f.escriuObjecteFitxer(this,fitxer,true);
    }


    /**
     * Retornar una llista de productes
     * @param fitxer fitxer al que li vols extreure la llista
     * @return llista de productes
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<Producte> retornaProducte(String fitxer) throws IOException, ClassNotFoundException {
        List<Object> objecte = f.retornaFitxerObjecteEnLlista(fitxer);
        List<Producte> llistaProductes = converteixAProducte(objecte);
        return llistaProductes;
    }


    /**
     * A partir d'una llista d'Objectes la pasa a llista del objecte que tu vulguis
     * @param lObjectes llista d'objectes
     * @return llista de l'objecte que vulguis
     */
    public List<Producte> converteixAProducte(List<Object> lObjectes){
        List<Producte> lProductes=new ArrayList<>();
        Producte pr=new Producte();
        for (Object obj:lObjectes){
            pr= (Producte) obj;
            lProductes.add(pr);
        }
        return lProductes;
    }

    @Deprecated
    public static void provaJDOC(){
        System.out.println("És una prova");
    }



}

