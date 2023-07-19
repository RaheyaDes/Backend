package com.bezkoder.spring.oracle.controller;


import com.bezkoder.spring.oracle.model.LoginRequest;
import com.bezkoder.spring.oracle.model.Usuario;
import com.bezkoder.spring.oracle.repository.UsuarioRepository;
import com.bezkoder.spring.oracle.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/searchUsersByStatus/{status}", method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> searchUsuariosByStatus(@PathVariable("status") String status) {
        try {
            List<Usuario> users = new ArrayList<Usuario>();

            usuarioRepository.findByStatus(status).forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/searchUsers/all", method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> searchUsuarios() {
        try {
            List<Usuario> users = new ArrayList<Usuario>();
            usuarioRepository.findAll().forEach(users::add);
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        try {
            usuario.setFechaAlta(new Date());

            String encryptedPassword = PasswordUtil.encryptPassword(usuario.getPassword());
            usuario.setPassword(encryptedPassword);
            usuario.setNoAcceso(0);

            Usuario createdUsuario = usuarioRepository.save(usuario);

            return new ResponseEntity<>(createdUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String login = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Usuario usuario = usuarioRepository.findByLogin(login);

        if (usuario != null) {
            String storedPassword = usuario.getPassword();

            if (PasswordUtil.encryptPassword(password).equals(storedPassword)) {
                usuario.setNoAcceso(usuario.getNoAcceso() + 1);
                usuarioRepository.save(usuario);
                return ResponseEntity.ok("Login successful");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }




    @PutMapping("/update/{login}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("login") String login, @RequestBody Usuario usuario) {
        try {
            Optional<Usuario> optionalUsuario = usuarioRepository.findById(login);

            if (optionalUsuario.isPresent()) {
                Usuario updatedUsuario = optionalUsuario.get();
                updatedUsuario.setNombre(usuario.getNombre());
                updatedUsuario.setEmail(usuario.getEmail());
                updatedUsuario.setStatus(usuario.getStatus());
                updatedUsuario.setFechaModificacion(new Date());

                usuarioRepository.save(updatedUsuario);

                return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{login}")
    public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("login") String login) {
        try {
            Optional<Usuario> optionalUsuario = usuarioRepository.findById(login);

            if (optionalUsuario.isPresent()) {
                Usuario usuario = optionalUsuario.get();
                usuario.setStatus("B");
                usuario.setFechaBaja(new Date());
                usuarioRepository.save(usuario);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}

