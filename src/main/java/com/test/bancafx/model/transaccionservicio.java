package com.test.bancafx.model;


public class transaccionservicio {
    private double pago;
    private String tipo_servicio;

    public transaccionservicio(int pago, String tipo_servicio) {
        this.pago = pago;
        this.tipo_servicio = tipo_servicio;
    }

    public double getPago() {
        return pago;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    @Override
    public String toString() {
        return String.format("Transaccion [pago=%d, tipo_servicio=%s]"
                , pago, tipo_servicio);
    }
}
