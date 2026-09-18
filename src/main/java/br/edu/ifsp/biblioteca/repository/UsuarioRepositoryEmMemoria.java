package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.Usuario;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("memoria")
public class UsuarioRepositoryEmMemoria implements IUsuarioRepository{

    private final Map<Long, Usuario> usuarios = new HashMap<>();
    private Long sequenciaId = 0L;

    @Override
    public Usuario salvar(Usuario usuario) {

        if (usuario.getId() == null) {
            usuario.setId(++this.sequenciaId);
        }
        this.usuarios.put(usuario.getId(), usuario);

        return usuario;
    }

    @Override
    public Optional<Usuario> buscarPorId (long id) {
        return Optional.ofNullable(this.usuarios.get(id));
    }

    @Override
    public Optional<Usuario> buscarPorEmail (String email) {
        List<Usuario> listaUsuarios = new ArrayList<>(this.usuarios.values());

        for (int i = 0; i < listaUsuarios.size(); i++) {
            Usuario usuario = listaUsuarios.get(i);

            if (usuario.getEmail().equals(email)) {
                return Optional.of(usuario);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Usuario> listarTodos () {
        return new ArrayList<>(this.usuarios.values());
    }

}
