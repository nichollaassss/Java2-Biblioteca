package br.edu.ifsp.biblioteca;

import br.edu.ifsp.biblioteca.domain.Autor;
import br.edu.ifsp.biblioteca.domain.Livro;
import br.edu.ifsp.biblioteca.repository.ILivroRepository;
import br.edu.ifsp.biblioteca.repository.LivroRepositoryEmMemoria;

import java.util.List;
import java.util.Optional;

public class BibliotecaApplication {

    public static void main(String[] args) {
        Livro livro = new Livro(
                "1984",
                "9788822796028",
                1949);

        Livro livro1 = new Livro(
                "A Revolução dos Bichos",
                "9783257055085",
                1945);

        Livro livro2 = new Livro(
                "Crime E Castigo",
                "9788573266467",
                1866);

        ILivroRepository livroRepository = new LivroRepositoryEmMemoria();

        livroRepository.salvar(livro);
        livroRepository.salvar(livro1);
        livroRepository.salvar(livro2);
        livroRepository.salvar(livro2);

        Optional<Livro> livroOptional = livroRepository.buscarPorId(1L);
        Optional<Livro> livro1Optional = livroRepository.buscarPorId(10L);

        if (livroOptional.isPresent()) {
            Livro l = livroOptional.get();

            System.out.println("Livro com id 1 encontrado, Título: " + l.toString());
        }

        if (livro1Optional.isPresent()) {
            Livro l = livro1Optional.get();

            System.out.println("Livro com id 10 encontrado, Título: " + l.toString());
        }
        else{
            System.out.println("Livro com id 10 não encontrado ");
        }

        List<Livro> encontrados = livroRepository.buscarPorTitulo("Bichos");

        System.out.println(encontrados
        );
        System.out.println(livro);
    }
}
