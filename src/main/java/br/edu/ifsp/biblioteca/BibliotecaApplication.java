package br.edu.ifsp.biblioteca;

import br.edu.ifsp.biblioteca.domain.Autor;
import br.edu.ifsp.biblioteca.domain.Livro;

public class BibliotecaApplication {

    public static void main(String[] args) {
        Livro livro = new Livro(
                "1984",
                "9788822796028",
                1L,
                1949);

        Livro livro1 = new Livro(
                "A Revolução dos Bichos",
                "9783257055085",
                2L,
                1945);

        Livro livro2 = new Livro(
                "Crime E Castigo",
                "9788573266467",
                3L,
                1866);

        System.out.println(livro);
        System.out.println(livro1);
        System.out.println(livro2);

        Autor autor = new Autor("George Orwell");
        System.out.println(autor);
        Autor autor1 = new Autor("Fiodor Dostoievski");
        System.out.println(autor1);
    }
}
