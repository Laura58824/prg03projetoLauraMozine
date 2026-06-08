/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.ifba.gestaoanimal.enums;

/**
 *
 * @author m
 */
public enum SexoEnum {
    MACHO, FEMEA;
    
     @Override    
    public String toString() {
        switch (this) {
            case MACHO:
                return "Macho";
            case FEMEA:
                return "Fêmea";
            default:
                return name();
        }
    }
}
