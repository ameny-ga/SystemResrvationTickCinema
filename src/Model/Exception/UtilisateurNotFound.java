/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Model.Exception;

public class UtilisateurNotFound extends RuntimeException {
    public UtilisateurNotFound(String message) {
        super(message);
    }
}