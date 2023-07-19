package com.bezkoder.spring.oracle.controller;


import com.bezkoder.spring.oracle.model.LoginRequest;
import com.bezkoder.spring.oracle.model.Usuario;
import com.bezkoder.spring.oracle.repository.UsuarioRepository;
import com.bezkoder.spring.oracle.util.PasswordUtil;
import com.bezkoder.spring.oracle.util.UsuarioFieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import org.springframework.security.crypto.password.PasswordEncoder;

@CrossOrigin(origins = "http://localhost:8081")
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

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            usuario.setFechaVigencia(calendar.getTime());

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

            // Validate if the password is correct
            if (PasswordUtil.encryptPassword(password).equals(storedPassword) && usuario.getStatus().equals("A")) {
                Date today = new Date();

                // Check if FechaVigencia is greater than or equal to today
                if (usuario.getFechaVigencia() != null && usuario.getFechaVigencia().compareTo(today) >= 0) {
                    usuario.setNoAcceso(usuario.getNoAcceso() + 1);
                    usuarioRepository.save(usuario);
                    return ResponseEntity.ok("Login successful");
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User account has expired");
                }
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }





    @PutMapping("/update/{login}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("login") String login, @RequestBody Usuario usuario) {
        try {
            Optional<Usuario> optionalUsuario = usuarioRepository.findById(login);

            if (optionalUsuario.isPresent()) {
                Usuario existingUsuario = optionalUsuario.get();
                Usuario updatedUsuario = UsuarioFieldValidator.updateUsuarioFields(existingUsuario, usuario);
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

