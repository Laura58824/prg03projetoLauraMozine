/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.ifba.gestaoanimal.enums;

/**
 *
 * @author m
 */
public enum TipoPessoaEnum {
    FISICA, JURIDICA;
    
    @Override    
    public String toString() {
        switch (this) {
            case FISICA:
                return "Física";
            case JURIDICA:
                return "Jurídica";
            default:
                return name();
        }
    }
}
