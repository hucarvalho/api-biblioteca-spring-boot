package br.com.biblioteca.livraria.dto;
//data transfer object

//aqui os dados serao convertidos de bytes https para objetos java
public record LivroRecordDto(String isbn, String titulo) {
}
