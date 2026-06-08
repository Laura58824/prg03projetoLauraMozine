/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.ifba.gestaoanimal.enums;

/**
 *
 * @author m
 */
public enum TipoOcorrenciaEnum {
    MAUS_TRATOS, ABANDONO, ACIDENTE, RESGATE, OUTRO;
    
     @Override    
    public String toString() {
        switch (this) {
            case MAUS_TRATOS:
                return "Maus tratos";
            case ABANDONO:
                return "Abandono";
            case ACIDENTE:
                return "Acidente";
            case RESGATE:
                return "Resgate";
            case OUTRO:
                return "Outro";
            default:
                return name();
        }
    }
}
