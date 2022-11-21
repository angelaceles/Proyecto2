package com.example.Inico_P.dao;

import com.example.Inico_P.models.Usuario;
import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsers();

    void getDelete(Long id);

    void postUsers(Usuario usuario);

    Usuario obtener(Usuario usuario);
}
