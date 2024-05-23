package nidal.lahlou.m03_uf3_pp_lahlou_vera.Classes;

import ion.Vera.Fitxers;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @since v.11
 */
public class Usuari implements Serializable {
    static private final Fitxers f = new Fitxers();
    static private final String fitxerAdministradors = "administradors.dat";
    static private final String fitxerUsuaris = "usuaris.dat";


    String nom;
    String cognom;
    String nomUsuari;

    String password;
    int id;
    boolean isAdmin;

    public Usuari() {
    }

    public Usuari(String nom, String cognom, String nomUsuari, String password) {
        this.nom = nom;
        this.cognom = cognom;
        this.nomUsuari = nomUsuari;
        this.password = password;
        this.id = System.identityHashCode(this);
    }

    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return cognom;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getFitxerAdministradors() {
        return fitxerAdministradors;
    }

    public String getFitxerUsuaris() {
        return fitxerUsuaris;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuari{" +
                "nom='" + nom + '\'' +
                ", cognom='" + cognom + '\'' +
                ", nomUsuari='" + nomUsuari + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    /**
     * Guarda la persona (objecte) en un fitxer
     * @param fitxer el fitxer on vols guardar la persona
     * @throws IOException
     * @throws InterruptedException
     */
    public void guardaPersona(String fitxer) throws IOException, InterruptedException {
        f.escriuObjecteFitxer(this, fitxer, true);
    }

    /**
     * Retorna una llista de persones
     * @param fitxer fitxer al que li vols extreure la llista
     * @return llista de persones
     * @see #converteixAUsuari(List) 
     * @throws IOException
     * @throws InterruptedException
     * @throws ClassNotFoundException
     */
    public List<Usuari> retornaPersona(String fitxer) throws IOException, InterruptedException, ClassNotFoundException {
        List<Object> objectes=f.retornaFitxerObjecteEnLlista(fitxer);
        List<Usuari>llistaUsuaris=converteixAUsuari(objectes);
        return llistaUsuaris;
    }

    /**
     * A partir d'una llista d'Objectes la pasa a llista del objecte que tu vulguis
     * @param lObjectes llista d'objectes
     * @return llista de l'objecte que vulguis
     */
    public List<Usuari> converteixAUsuari(List<Object> lObjectes){
        List<Usuari> lUsuaris=new ArrayList<>();
        Usuari us=new Usuari();
        for (Object obj:lObjectes){
            us= (Usuari) obj;
            lUsuaris.add(us);
        }
        return lUsuaris;
    }



}
