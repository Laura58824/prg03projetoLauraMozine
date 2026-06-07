/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.gestaoanimal.usuario.entity;

import br.com.ifba.gestaoanimal.infrastructure.entity.PersistenceEntity;
import br.com.ifba.gestaoanimal.perfilusuario.entity.PerfilUsuario;
import br.com.ifba.gestaoanimal.pessoa.entity.Pessoa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "usuario")

public class Usuario extends PersistenceEntity{
    
    
    @Column(name = "login", nullable = false, unique = true)
    private String login;
 
    @Column(name = "senha", nullable = false)
    private String senha;
    
    @Column(name = "ativo", nullable = false)
    private boolean ativo; 
    
    @Column(name = "ultimo_acesso")
    private LocalDateTime ultimoAcesso;
    
    @OneToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;
    
    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private PerfilUsuario perfil;
}
