package br.com.biblioteca.livraria.controllers;

import br.com.biblioteca.livraria.dto.LivroRecordDto;
import br.com.biblioteca.livraria.model.Livro;
import br.com.biblioteca.livraria.repository.LivroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LivroController {

    @Autowired
    LivroRepository livroRepository;
    //vamos injetar a dependencia livrorRepository, nessa classe ficara as instruções sql

    @PostMapping("/livros")
    public ResponseEntity<Livro> store(@RequestBody LivroRecordDto livroRecordDto) {
        //Response Entity pega o corpo do http e o status da resposta
        Livro livro = new Livro();
        BeanUtils.copyProperties(livroRecordDto, livro);
        //esse metodo copia as propriedades que vieram do request e passa para a classe livro

        return ResponseEntity.status(HttpStatus.CREATED).body(livroRepository.save(livro));
    }
    @GetMapping("/livros")
    public ResponseEntity<List<Livro>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(livroRepository.findAll());
    }

    @GetMapping("/livros/{isbn}")
    public ResponseEntity<Object> getByIsbn(@PathVariable(name = "isbn") String isbn){
        //o path variable é o responsavel por retirar a variavel na uri e colocar de parametro no metodo
        // logo apos passamos o tipo dela e como ela sera nomeada
        Optional<Livro> optionalLivro = livroRepository.findById(isbn);
        if(optionalLivro.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado!");
        }
        //Optional é um tipo de dados que nos oferece uma maneira mais eficiente de trabalhar com dados que tem a possibilidade de ser nulos
        return ResponseEntity.status(HttpStatus.OK).body(optionalLivro.get());
    }

    @PutMapping("/livros/{isbn}")
    public ResponseEntity<Object> update(@PathVariable(name = "isbn") String isbn, @RequestBody LivroRecordDto livroRecordDto){
        Optional<Livro> optionalLivro = livroRepository.findById(isbn);
        if(optionalLivro.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado!");
        }
        Livro livro = optionalLivro.get();
        BeanUtils.copyProperties(livroRecordDto, livro);

        return ResponseEntity.status(HttpStatus.OK).body(livroRepository.save(livro));


    }

    @DeleteMapping("/livros/{isbn}")
    public ResponseEntity<Object> destroy(@PathVariable(name = "isbn") String isbn){
        Optional<Livro> optionalLivro = livroRepository.findById(isbn);
        if(optionalLivro.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado!");
        }
        livroRepository.delete(optionalLivro.get());
        return ResponseEntity.status(HttpStatus.OK).body("Livro Deletado");
    }

}
