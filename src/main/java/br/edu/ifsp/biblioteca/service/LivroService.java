package br.edu.ifsp.biblioteca.service;

import br.edu.ifsp.biblioteca.domain.Autor;
import br.edu.ifsp.biblioteca.domain.Exemplar;
import br.edu.ifsp.biblioteca.domain.Livro;
import br.edu.ifsp.biblioteca.exception.RegraDeNegocioException;
import br.edu.ifsp.biblioteca.repository.ILivroRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class LivroService {

    private final ILivroRepository livroRepository;

    public LivroService (ILivroRepository repository){
        this.livroRepository = repository;
    }

    public Livro cadastrar(Livro livro) {

            if (livro.getTitulo() == null || livro.getTitulo().isEmpty()) {

                throw new RegraDeNegocioException("O título é obrigatório ao cadastrar um livro");
            }

            if (livro.getIsbn() == null || livro.getIsbn().isEmpty()) {

                throw new RegraDeNegocioException("O ISBN é obrigatório ao cadastrar um livro");
            }

            if (livro.getAnoPublicacao() == null || livro.getAnoPublicacao() <= 0) {

                throw new RegraDeNegocioException("O ano de publicação é obrigatório ao cadastrar um livro");
            }

            Optional<Livro> livroJaCadastrado = this.livroRepository.buscarPorIsbn(livro.getIsbn());

            if(livroJaCadastrado.isPresent())
            {
                throw new RegraDeNegocioException("Já existe um livro cadastrado com o ISBN " + livro.getIsbn());
            }

            return this.livroRepository.salvar(livro);

    }

    public Livro buscarLivro (Long livroId){
        Optional<Livro> livroOptional = livroRepository.buscarPorId(livroId);

        if(livroOptional.isPresent())
            return livroOptional.get();

        throw new RegraDeNegocioException("Livro com id \" + livroId + \" não encontrado");
    }

    public Livro adicionarExemplar(Long livroId, String codigoExemplar){

        Livro livro = buscarLivro(livroId);

        livro.adicionarExemplar(new Exemplar(codigoExemplar, livro));
        return this.livroRepository.salvar(livro);

    }

    public Livro adicionarAutor(Long livroId, String nomeAutor){

        Livro livro = buscarLivro(livroId);

        livro.adicionarAutor(new Autor(nomeAutor));
        return this.livroRepository.salvar(livro);
    }

    public List<Livro> buscarPorTitulo(String trechoDoTitulo){
        return livroRepository.buscarPorTitulo(trechoDoTitulo);
    }

    public List<Livro> listarTodos(){
        return livroRepository.listarTodos();
    }
}
