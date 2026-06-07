/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.gestaoanimal.perfilusuario.entity;

import br.com.ifba.gestaoanimal.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "perfil_usuario")

public class PerfilUsuario extends PersistenceEntity {
 
    @Column(name = "descricao", nullable = false)
    private String descricao;
 
    @Column(name = "permissoes")
    private String permissoes; 
 
}

