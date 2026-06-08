/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.gestaoanimal.animal.entity;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import br.com.ifba.gestaoanimal.enums.*;
import br.com.ifba.gestaoanimal.infrastructure.entity.PersistenceEntity;
import br.com.ifba.gestaoanimal.ocorrencia.entity.Ocorrencia;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;
 

    @Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "animal")
public class Animal extends PersistenceEntity {
 
    @Column(name = "nome")
    private String nome;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "especie", nullable = false)
    private EspecieEnum especie;
 
    @Column(name = "raca")
    private String raca;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false)
    private SexoEnum sexo;
 
    @Column(name = "idade_estimada")
    private Integer idadeEstimada;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "porte")
    private PorteEnum porte;
 
    @Column(name = "temperamento")
    private String temperamento;
 
    @Column(name = "necessidades_especiais")
    private String necessidadesEspeciais;
 
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusAnimalEnum status;
 
    @Column(name = "data_entrada")
    private LocalDate dataEntrada;
 
    @ManyToOne
    @JoinColumn(name = "ocorrencia_id")
    private Ocorrencia ocorrencia;
    
    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;
}
