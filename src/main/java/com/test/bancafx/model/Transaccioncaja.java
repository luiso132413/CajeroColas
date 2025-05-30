package com.test.bancafx.model;

import java.time.LocalDateTime;

public class Transaccioncaja {
    private int numeroCuenta;
    private String tipo;
    private double monto;
    private String descripcion;
    private LocalDateTime fechaHora;

    public Transaccioncaja(int numeroCuenta, String tipo, double monto, String descripcion) {
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.fechaHora = LocalDateTime.now();
    }

    // Getters
    public int getNumeroCuenta() { return numeroCuenta; }
    public String getTipo() { return tipo; }
    public double getMonto() { return monto; }
    public String getDescripcion() { return descripcion; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    @Override
    public String toString() {
        return String.format("Transacción [Cuenta: %d, Tipo: %s, Monto: %.2f, Desc: %s]",
                numeroCuenta, tipo, monto, descripcion);
    }
}