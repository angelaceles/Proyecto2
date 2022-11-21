package com.example.Inico_P.controllers;

import com.example.Inico_P.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.example.Inico_P.dao.UsuarioDao;
import com.example.Inico_P.models.Usuario;

@RestController
@SuppressWarnings("ALL")
public class UsuarioController{
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUser(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Lucas");
        usuario.setApellido("Moy");
        usuario.setEmail("lucas@gmail.com");
        usuario.setTelefono("987654321");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsers(@RequestHeader(value = "Authorization")String token){
        String usuarioId = jwtUtil.getKey(token);
        if(usuarioId == null){
            return  new ArrayList<>();
        }
        return usuarioDao.getUsers();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void postUsers(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash =argon2.hash(1, 1024,1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.postUsers(usuario);
    }

    @RequestMapping(value = "usuario45")
    public Usuario getEdit(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Lucas");
        usuario.setApellido("Moy");
        usuario.setEmail("lucas@gmail.com");
        usuario.setTelefono("987654321");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios/{id}", method=RequestMethod.DELETE)
    public void getDelete(@PathVariable Long id){
        usuarioDao.getDelete(id);
    }

    @RequestMapping(value = "usuario123")
    public Usuario getSearch(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Lucas");
        usuario.setApellido("Moy");
        usuario.setEmail("lucas@gmail.com");
        usuario.setTelefono("987654321");
        return usuario;
    }
}
