/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

public class Alumno implements Serializable{
    int id;
    String tec;
    String nombre;
    String apellido;
    String celular;
    String imagen;

    public Alumno() {
    }

    public Alumno(int id, String tec, String nombre, String apellido, String celular, String imagen) {
        this.id = id;
        this.tec = tec;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTec() {
        return tec;
    }

    public void setTec(String tec) {
        this.tec = tec;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


}

