package com.bezkoder.spring.oracle.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "USUARIO")
public class Usuario  {

    @Id
    @Column(name = "LOGIN", length = 20)
    private String login;

    @Column(name = "PASSWORD", length = 100)
    private String password;

    @Column(name = "NOMBRE", length = 50)
    private String nombre;

    @Column(name = "CLIENTE")
    private Float cliente;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "FECHAALTA")
    private Date fechaAlta;

    @Column(name = "FECHABAJA")
    private Date fechaBaja;

    @Column(name = "STATUS", length = 1)
    private String status;

    @Column(name = "INTENTOS")
    private Float intentos;

    @Column(name = "FECHAREVOCADO")
    private Date fechaRevocado;

    @Column(name = "FECHA_VIGENCIA")
    private Date fechaVigencia;

    @Column(name = "NO_ACCESO")
    private Integer noAcceso;

    @Column(name = "APELLIDO_PATERNO", length = 50)
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO", length = 50)
    private String apellidoMaterno;

    @Column(name = "AREA")
    private Integer area;

    @Column(name = "FECHAMODIFICACION")
    private Date fechaModificacion;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getCliente() {
        return cliente;
    }

    public void setCliente(Float cliente) {
        this.cliente = cliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getIntentos() {
        return intentos;
    }

    public void setIntentos(Float intentos) {
        this.intentos = intentos;
    }

    public Date getFechaRevocado() {
        return fechaRevocado;
    }

    public void setFechaRevocado(Date fechaRevocado) {
        this.fechaRevocado = fechaRevocado;
    }

    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public Integer getNoAcceso() {
        return noAcceso;
    }

    public void setNoAcceso(Integer noAcceso) {
        this.noAcceso = noAcceso;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}

