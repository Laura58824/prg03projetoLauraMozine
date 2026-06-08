/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.ifba.gestaoanimal.enums;

/**
 *
 * @author m
 */
public enum StatusOcorrenciaEnum {
    ABERTA, EM_ATENDIMENTO, CONCLUIDA, CANCELADA;
    
     @Override    
    public String toString() {
        switch (this) {
            case ABERTA:
                return "Aberta";
            case EM_ATENDIMENTO:
                return "Em atendimento";
            case CONCLUIDA:
                return "Concluída";
            case CANCELADA:
                return "Cancelada";
            default:
                return name();
        }
    }
    
}
