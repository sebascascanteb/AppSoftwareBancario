/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaLogica;

import capaInterfaz.PrincipalSoftwareBancario;
import capaLogica.Cuenta;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Sebas
 */
public class Transaccion {

    Cuenta cuenta1;

    private static int contTransaccion = 101;
    private int numTransaccion;
    private String fechaTransaccion;
    private TipoTransaccion tipoTransaccion;
    private double montoTransaccion;
    public static Cajero cajero1 = null;
    public double saldoActual = 0;
    
    
    public Transaccion() {
        this.numTransaccion = contTransaccion;
        contTransaccion++;
        this.fechaTransaccion = "";
        this.tipoTransaccion = null;
        this.montoTransaccion = 0;
        this.saldoActual = 0;
    }

    @Override
    public String toString() {
        return "Transacción: "
                + "\n---------------------------------------------------------"
                + "\nNúmero de cuenta: " + PrincipalSoftwareBancario.getNumUser()
                + "\n---------------------------------------------------------"
                + "\nNúmero de transacción: " + numTransaccion
                + "\n---------------------------------------------------------"
                + "\nFecha: " + fechaTransaccion
                + "\n---------------------------------------------------------"
                + "\nTipo de transacción: " + tipoTransaccion
                + "\n---------------------------------------------------------"
                + "\nMonto: ₡" + montoTransaccion
                + "\n---------------------------------------------------------"
                + "\nSaldo actual: ₡" + saldoActual;
    }

    public static int getContTransaccion() {
        return contTransaccion;
    }

    public static void setContTransaccion(int contTransaccion) {
        Transaccion.contTransaccion = contTransaccion;
    }

    public int getNumTransaccion() {
        return numTransaccion;
    }

    public void setNumTransaccion(int numtransaccion) {
        this.numTransaccion = numtransaccion;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public double getMontoTransaccion() {
        return montoTransaccion;
    }

    public void setMontoTransaccion(double montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }

    public static Cajero getCajero1() {
        return cajero1;
    }

    public static void setCajero1(Cajero cajero1) {
        Transaccion.cajero1 = cajero1;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

}
