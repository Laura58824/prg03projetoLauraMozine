/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.ifba.gestaoanimal.enums;

/**
 *
 * @author m
 */
public enum StatusAdocaoEnum {
    ABERTA, EM_ANALISE, APROVADA, CONCLUIDA, RECUSADA, CANCELADA;
    
     @Override    
    public String toString() {
        switch (this) {
            case ABERTA:
                return "Aberta";
            case EM_ANALISE:
                return "Em análise";
            case APROVADA:
                return "Aprovada";
            case CONCLUIDA:
                return "Concluída";
            case RECUSADA:
                return "Recusada";
            case CANCELADA:
                return "Cancelada";
            default:
                return name();
        }
    }
}
