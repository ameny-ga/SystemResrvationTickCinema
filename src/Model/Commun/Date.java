/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Commun;

import java.util.List;
import java.time.LocalDate;
public class Date implements Comparable<Date> {
    private int annee;
    private int mois;
    private int jour;

    public Date(int annee, int mois, int jour) {
        this.annee = annee;
        this.mois = mois;
        this.jour = jour;
    }
    public int getAnnes() {
        return annee;
    }
    public void setAnnes(int annes) {
        this.annee = annes;
    }
    public int getMois() {
        return mois;
    }
    public void setMois(int mois) {
        this.mois = mois;
    }
    public int getJours() {
        return jour;
    }
    public void setJours(int jours) {
        this.jour = jours;
    }
    @Override
    public String toString() {
        return annee + "-" + mois + "-" + jour;
    }
    @Override
    public int compareTo(Date autreDate) {
        // Comparaison des années
        if (this.annee != autreDate.annee) {
            return this.annee - autreDate.annee;
        }
        // Comparaison des mois
        if (this.mois != autreDate.mois) {
            return this.mois - autreDate.mois;
        }
        // Comparaison des jours
        return this.jour - autreDate.jour;
    }
    @Override
    public boolean equals(Object obj)
    {
        Date date=(Date)obj;
        if(date.getAnnes()==this.getAnnes()){
            if(date.getMois()==this.getMois()){
                if(date.getJours()==this.getJours()){
                    return true;
                }else{return false;}
            }else{return false;}
        }else{return false;}
    }

    public static List<Date> trierDates(List<Date> dates) {
        // Utilisation de .sort() grâce à l'implémentation de Comparable
        dates.sort(null); // null = tri naturel basé sur compareTo
        return dates;
    }
    public static Date getDateActuelle()
    {
        LocalDate today = LocalDate.now();
        return new Date(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
    }



}

