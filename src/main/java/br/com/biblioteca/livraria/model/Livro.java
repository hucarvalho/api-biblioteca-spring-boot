package br.com.biblioteca.livraria.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
//com a notação entity, o spring boot passa a gerenciar a classe e a Jpa criara a classe no banco de dados


@Entity
@Table(name = "livros")
public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;
    //versão da serialização, todo tipo long precisa ter o L no final
    @Id
    //declarando a chave primaria
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //o comando acima é utilizado para fazer ids em auto_increment
    private String isbn;
    private String titulo;

    public Livro() {
    }

    public Livro(String isbn, String titulo) {
        this.isbn = isbn;
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(isbn, livro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }

    @Override
    public String toString() {
        return "Livro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}
