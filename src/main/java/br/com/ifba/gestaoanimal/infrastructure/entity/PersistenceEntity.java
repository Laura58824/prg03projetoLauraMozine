package br.com.ifba.gestaoanimal.infrastructure.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public class PersistenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

}