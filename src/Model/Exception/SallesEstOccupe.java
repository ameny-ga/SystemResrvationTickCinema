/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Model.Exception;
public class SallesEstOccupe extends RuntimeException {
    public SallesEstOccupe() {
        super("la salle est deja Occupe pour cette date");
    }
}