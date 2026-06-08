/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.ifba.gestaoanimal.enums;

/**
 *
 * @author m
 */
public enum TipoProcedimentoEnum {
    VACINA, CASTRACAO, CONSULTA, CIRURGIA, EXAME, VERMIFUGACAO;
    
    @Override    
    public String toString() {
        switch (this) {
            case VACINA:
                return "Vacina";
            case CASTRACAO:
                return "Castração";
            case CONSULTA:
                return "Consulta";
            case CIRURGIA:
                return "Cirurgia";
            case EXAME:
                return "Exame";
            case VERMIFUGACAO:
                return "Vermifugação";
            default:
                return name();
        }
    }
}
