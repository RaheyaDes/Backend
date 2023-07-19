package com.bezkoder.spring.oracle.repository;

import com.bezkoder.spring.oracle.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    List<Usuario> findByStatus(String status);

    Usuario findByLogin(String login);
}
