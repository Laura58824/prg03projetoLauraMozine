/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.ifba.gestaoanimal.enums;

import static br.com.ifba.gestaoanimal.enums.StatusSolicitacaoEnum.APROVADA;
import static br.com.ifba.gestaoanimal.enums.StatusSolicitacaoEnum.PENDENTE;
import static br.com.ifba.gestaoanimal.enums.StatusSolicitacaoEnum.RECUSADA;

/**
 *
 * @author m
 */
public enum TipoDoacaoEnum {
    FINANCEIRA, MATERIAL, ALIMENTO, MEDICAMENTO;
    
    @Override    
    public String toString() {
        switch (this) {
            case FINANCEIRA:
                return "Financeira";
            case MATERIAL:
                return "Material";
            case ALIMENTO:
                return "Alimento";
            case MEDICAMENTO:
                return "Medicamento";   
            default:
                return name();
        }
    }
}
