/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.ifba.gestaoanimal.enums;

/**
 *
 * @author m
 */
public enum UrgenciaEnum {
    BAIXA, MEDIA, ALTA, CRITICA;
    
    @Override    
    public String toString() {
        switch (this) {
            case BAIXA:
                return "Baixa";
            case MEDIA:
                return "Média";
            case ALTA:
                return "Alta";
            case CRITICA:
                return "Crítica";
            default:
                return name();
        }
    }
}
