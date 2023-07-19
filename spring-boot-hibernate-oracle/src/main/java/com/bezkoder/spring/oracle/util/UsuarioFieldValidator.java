package com.bezkoder.spring.oracle.util;

import com.bezkoder.spring.oracle.model.Usuario;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class UsuarioFieldValidator {

    public static Usuario updateUsuarioFields(Usuario existingUsuario, Usuario updatedUsuario) {
        if (StringUtils.isNotBlank(updatedUsuario.getNombre())) {
            existingUsuario.setNombre(updatedUsuario.getNombre());
        }
        if (StringUtils.isNotBlank(updatedUsuario.getEmail())) {
            existingUsuario.setEmail(updatedUsuario.getEmail());
        }
        if (StringUtils.isNotBlank(updatedUsuario.getStatus())) {
            existingUsuario.setStatus(updatedUsuario.getStatus());
        }
        if (StringUtils.isNotBlank(updatedUsuario.getApellidoPaterno())) {
            existingUsuario.setApellidoPaterno(updatedUsuario.getApellidoPaterno());
        }
        if (StringUtils.isNotBlank(updatedUsuario.getApellidoMaterno())) {
            existingUsuario.setApellidoMaterno(updatedUsuario.getApellidoMaterno());
        }
        if (updatedUsuario.getFechaVigencia() != null) {
            existingUsuario.setFechaVigencia(updatedUsuario.getFechaVigencia());
        }
        if (updatedUsuario.getFechaRevocado() != null) {
            existingUsuario.setFechaRevocado(updatedUsuario.getFechaRevocado());
        }

        existingUsuario.setFechaModificacion(new Date());


        return existingUsuario;
    }
}

