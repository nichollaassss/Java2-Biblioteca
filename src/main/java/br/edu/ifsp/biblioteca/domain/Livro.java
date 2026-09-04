package br.edu.ifsp.biblioteca.domain;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private Long id;
    private String isbn;
    private String titulo;
    private List<Autor> listaDeAutores;
    private Integer anoPublicacao;
    private List<Exemplar> listaDeExemplares;

    public Livro (String titulo, String isbn, Integer anoPublicacao) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.listaDeExemplares = new ArrayList<>();
        this.listaDeAutores = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void adicionarExemplar(Exemplar exemplar){
        this.listaDeExemplares.add(exemplar);
    }

    public void adicionarAutor(Autor autor){
        this.listaDeAutores.add(autor);
    }

    @Override
    public String toString (){
        return this.titulo;
    }
}
