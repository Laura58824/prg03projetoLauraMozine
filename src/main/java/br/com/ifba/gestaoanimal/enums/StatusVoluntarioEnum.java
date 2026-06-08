/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.ifba.gestaoanimal.enums;

/**
 *
 * @author m
 */
public enum StatusVoluntarioEnum {
    ATIVO, INATIVO, SUSPENSO;
    
    @Override    
    public String toString() {
        switch (this) {
            case ATIVO:
                return "Ativo";
            case INATIVO:
                return "Inativo";
            case SUSPENSO:
                return "Suspenso";
            default:
                return name();
        }
    }
}
