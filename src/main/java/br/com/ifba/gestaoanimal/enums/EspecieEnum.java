/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.ifba.gestaoanimal.enums;

/**
 *
 * @author m
 */
public enum EspecieEnum {
    CACHORRO, GATO, CAVALO, REPTIL, OUTRO;
    
    @Override    
    public String toString() {
        switch (this) {
            case CACHORRO:
                return "Cachorro";
            case GATO:
                return "Gato";
            case CAVALO:
                return "Cavalo";
            case REPTIL:
                return "Réptil";
            case OUTRO:
                return "Outro";
            default:
                return name();
        }
    }
}
