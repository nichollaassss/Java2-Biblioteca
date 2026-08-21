package br.edu.ifsp.biblioteca.domain;

public class Livro {
    private Long id;
    private String isbn;
    private String titulo;
    private Integer anoPublicacao;

    public Livro (String titulo, String isbn, Long id, Integer anoPublicacao) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.id = id;
        this.anoPublicacao = anoPublicacao;
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

    @Override
    public String toString (){
        return this.titulo;
    }
}
