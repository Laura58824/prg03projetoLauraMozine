/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.ifba.gestaoanimal.enums;

/**
 *
 * @author m
 */
public enum PorteEnum {
    PEQUENO, MEDIO, GRANDE;
    
     @Override    
    public String toString() {
        switch (this) {
            case PEQUENO:
                return "Pequeno";
            case MEDIO:
                return "Médio";
            case GRANDE:
                return "Grande"; 
            default:
                return name();
        }
    }
    
}
