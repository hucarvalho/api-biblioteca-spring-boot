package br.com.biblioteca.livraria.repository;

import br.com.biblioteca.livraria.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, String> //herda o jpa repository, passando nossa classe de model e o tipo da chave primaria, no caso String
{

}
