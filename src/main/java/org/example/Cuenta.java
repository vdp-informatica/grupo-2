package org.example;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

public class Cuenta {

    @XmlAttribute(name = "tipo")
    private String tipo;
    @XmlElement(name = "nombre")
    private String nombre;
    @XmlElement(name = "numero")
    private String numero;
    @XmlElement(name = "saldohaber")
    private String saldohaber;
    @XmlElement(name = "saldodebe")
    private String saldodebe;
    @XmlElement(name = "aportacion")
    private String aportacion;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSaldohaber() {
        return saldohaber;
    }

    public void setSaldohaber(String saldohaber) {
        this.saldohaber = saldohaber;
    }

    public String getSaldodebe() {
        return saldodebe;
    }

    public void setSaldodebe(String saldodebe) {
        this.saldodebe = saldodebe;
    }

    public String getAportacion() {
        return aportacion;
    }

    public void setAportacion(String aportacion) {
        this.aportacion = aportacion;
    }
}
