/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.ifba.gestaoanimal.enums;

/**
 *
 * @author m
 */
public enum PerfilEnum {
    ADMIN, USUARIO, VOLUNTARIO, ADOTANTE, DOADOR;
    
     @Override    
    public String toString() {
        switch (this) {
            case ADMIN:
                return "Admin";
            case USUARIO:
                return "Usuário";
            case VOLUNTARIO:
                return "Voluntário";
            case ADOTANTE:
                return "Adotante";
            case DOADOR:
                return "Doador";
            default:
                return name();
        }
    }
    
}
