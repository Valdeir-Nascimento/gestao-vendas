package br.com.gvendas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "categoria")
public class Categoria {

    @EqualsAndHashCode.Include
    @Column(name = "codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long codigo;

    @Column(name ="nome", nullable = false)
    private String nome;

}
