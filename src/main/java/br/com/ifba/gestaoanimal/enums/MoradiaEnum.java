/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.ifba.gestaoanimal.enums;

/**
 *
 * @author m
 */
public enum MoradiaEnum {
    CASA, APARTAMENTO, SITIO, CHACARA, OUTRO;
    
     @Override    
    public String toString() {
        switch (this) {
            case CASA:
                return "Casa";
            case APARTAMENTO:
                return "Apartamento";
            case SITIO:
                return "Sítio";
            case CHACARA:
                return "Chácara";
            case OUTRO:
                return "Outro";
            default:
                return name();
        }
    }
    
}
