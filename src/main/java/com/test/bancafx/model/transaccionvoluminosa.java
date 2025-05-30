package com.test.bancafx.model;

public class transaccionvoluminosa {
    private int numero_cuenta;
    private String tipo_dep;
    private double monto;
    private String cajero;
    private String descripcion;
    private int n_autorizacion;

    public transaccionvoluminosa(int numero_cuenta, int n_autorizacion, String descripcion,
                                 String cajero, double monto, String tipo_dep) {
        this.numero_cuenta = numero_cuenta;
        this.n_autorizacion = n_autorizacion;
        this.descripcion = descripcion;
        this.cajero = cajero;
        this.monto = monto;
        this.tipo_dep = tipo_dep;
    }

    public int getNumero_cuenta() {
        return numero_cuenta;
    }

    public int getN_autorizacion() {
        return n_autorizacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCajero() {
        return cajero;
    }

    public double getMonto() {
        return monto;
    }

    public String getTipo_dep() {
        return tipo_dep;
    }

    @Override
    public String toString() {
        return String.format(
                "Transacción Voluminosa[Cuenta: %d, Autorización: %d, Tipo: %s, Monto: %.2f, Cajero: %s, Descripción: %s]",
                numero_cuenta, n_autorizacion, tipo_dep, monto, cajero, descripcion
        );
    }
}