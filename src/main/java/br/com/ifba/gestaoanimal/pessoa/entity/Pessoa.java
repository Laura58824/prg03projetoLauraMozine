/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.gestaoanimal.pessoa.entity;

import br.com.ifba.gestaoanimal.infrastructure.entity.PersistenceEntity;
import br.com.ifba.gestaoanimal.usuario.entity.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author m
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "pessoa")
public class Pessoa extends PersistenceEntity{
    
    @Column(name = "nome", nullable = false)
    private String nome;
 
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;
 
    @Column(name = "email")
    private String email;
 
    @Column(name = "telefone")
    private String telefone;
 
    @Column(name = "endereco")
    private String endereco;
 
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
 
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;
 
    @OneToOne(mappedBy = "pessoa")
    private Usuario usuario;
}
