/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.ifba.gestaoanimal.enums;

/**
 *
 * @author m
 */
public enum StatusAnimalEnum {
    DISPONIVEL, ADOTADO, EM_TRATAMENTO, OBITO, APADRINHADO;
    
     @Override    
    public String toString() {
        switch (this) {
            case DISPONIVEL:
                return "Disponível";
            case ADOTADO:
                return "Adotado";
            case EM_TRATAMENTO:
                return "Em tratamento";
            case OBITO:
                return "Óbito";
            case APADRINHADO:
                return "Apadrinhado";   
            default:
                return name();
        }
    }
}
