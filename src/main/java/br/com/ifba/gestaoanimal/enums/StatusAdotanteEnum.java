/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.ifba.gestaoanimal.enums;

/**
 *
 * @author m
 */
public enum StatusAdotanteEnum {
    PENDENTE, APROVADO, REPROVADO, SUSPENSO;
    
     @Override    
    public String toString() {
        switch (this) {
            case PENDENTE:
                return "Pendente";
            case APROVADO:
                return "Aprovado";
            case REPROVADO:
                return "Reprovado";
            case SUSPENSO:
                return "Suspenso"; 
            default:
                return name();
        }
    }
}
