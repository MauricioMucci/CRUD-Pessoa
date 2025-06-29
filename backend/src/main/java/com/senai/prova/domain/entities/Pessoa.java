package com.senai.prova.domain.entities;

import com.senai.prova.infrastructure.constraints.EmailConstraint;
import com.senai.prova.infrastructure.constraints.NomeConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.proxy.HibernateProxy;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author MauricioMucci
 */
@Getter
@Setter
@ToString(of = {"email"})
@RequiredArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa", nullable = false)
    private Long id;

    @NomeConstraint
    @NotNull
    @Size(max = 255)
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "nascimento")
    private LocalDate nascimento;

    @Size(max = 11)
    @Column(name = "cpf", unique = true, length = 11)
    private String cpf;

    @EmailConstraint
    @Size(max = 255)
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "criacao_registro", nullable = false)
    private Instant criacaoRegistro;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "alteracao_registro", nullable = false)
    private Instant alteracaoRegistro;

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Pessoa pessoa = (Pessoa) o;
        if (this.id != null && Objects.equals(this.id, pessoa.getId())) {
            return true;
        }
        if (this.cpf != null && Objects.equals(this.cpf, pessoa.getCpf())) {
            return true;
        }
        return this.email != null && Objects.equals(this.email, pessoa.getEmail());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
