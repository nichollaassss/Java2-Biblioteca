package br.edu.ifsp.biblioteca.service;

import br.edu.ifsp.biblioteca.domain.Autor;
import br.edu.ifsp.biblioteca.domain.Exemplar;
import br.edu.ifsp.biblioteca.domain.Livro;
import br.edu.ifsp.biblioteca.domain.Usuario;
import br.edu.ifsp.biblioteca.exception.RegraDeNegocioException;
import br.edu.ifsp.biblioteca.repository.IUsuarioRepository;
import br.edu.ifsp.biblioteca.repository.IUsuarioRepository;
import br.edu.ifsp.biblioteca.repository.UsuarioRepositoryEmMemoria;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public UsuarioService (IUsuarioRepository repository){
        this.usuarioRepository = repository;
    }

    public Usuario cadastrar(Usuario usuario) {

        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {

            throw new RegraDeNegocioException("O nome é obrigatório ao cadastrar um usuario");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {

            throw new RegraDeNegocioException("O email é obrigatório ao cadastrar um usuario");
        }

        this.validarEmail(usuario.getEmail());

        return this.usuarioRepository.salvar(usuario);

    }

    public Usuario buscarPorId(Long usuarioId){
        Optional<Usuario> usuarioOptional = usuarioRepository.buscarPorId(usuarioId);

        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();

            return usuario;
        }

            throw new RegraDeNegocioException("Usuario com id \" + usuarioId + \" não encontrado");
    }

    public void validarEmail (String email){
        Optional<Usuario> usuarioOptional = usuarioRepository.buscarPorEmail(email);

        if(usuarioOptional.isPresent())
            throw new RegraDeNegocioException("Usuario com email \" + email + \" já cadastrado");
    }

    public List<Usuario> listarTodos(){
        return usuarioRepository.listarTodos();
    }
}
