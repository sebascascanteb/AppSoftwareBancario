/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaLogica;

import capaInterfaz.PrincipalSoftwareBancario;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Sebas
 */
public class Cajero {

    Cuenta cuenta1;
    Transaccion transaccion1;
    Transaccion transaccion2;

    private Cuenta[] cuenta;
    private Transaccion[][] transaccion;
    private int indice;
    private int columnaTransaccion;
    private String fechaTransaccion;

    public Cajero() {
        this.cuenta = new Cuenta[20];
        this.transaccion = new Transaccion[20][10];
        this.indice = 0;
        this.columnaTransaccion = 0;

    }

    public String crearCuenta(Cuenta cuenta1) { //crea las cuentas  //FrmAdmin
        if (indice < cuenta.length) {
            cuenta[indice] = cuenta1;
            indice++;
            return "Cuenta creada:\n" + cuenta1.toString();
        } else {
            return "Alcanzaste el número máximo de cuentas";
        }
    }

    public String buscarCuenta(String pin) {  // Para buscar una cuenta    //FrmAdmin
        String result = "";
        for (int i = 0; i < indice; i++) {
            if (cuenta[i].getPasswordCod().equals(pin)) {
                result = cuenta[i].toString();
            } else {
                result = "Error\nCuenta no existente";
            }
        }
        return result;
    }

    public boolean iniciarSesion(int usuario, String pin) {  //Para poder iniciar sesión   //FrmMenu
        for (int i = 0; i < indice; i++) {
            if (cuenta[i].getNumCuenta() == usuario && cuenta[i].getPasswordCod().equals(pin)) {
                return true;
            }
        }
        return false;
    }

    public String datosCuenta() { //Datos sin el pin normal
        for (int i = 0; i < indice; i++) {
            if (cuenta[i].getNumCuenta() == PrincipalSoftwareBancario.getNumUser() && cuenta[i].getPasswordCod().equals(PrincipalSoftwareBancario.getPinUser())) {
                return cuenta[i].toString2();
            }
        }
        return "ERROR";
    }

    //////////////
    //////////////
    ////////////// Métodos para el Frm Usuario
    public double consultaSaldo() {    //Da el saldo      //FrmUsuario
        for (int i = 0; i < indice; i++) {
            if (cuenta[i].getPasswordCod().equals(PrincipalSoftwareBancario.getPinUser()) && cuenta[i].getNumCuenta() == PrincipalSoftwareBancario.getNumUser()) {
                return cuenta[i].getSaldoActual();
            }
        }
        return -2;
    }

    public String Fecha() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
        fechaTransaccion = String.format(dtf.format(LocalDateTime.now()));
        return fechaTransaccion;
    }

    public String deposito(double deposito) { 
        transaccion1 = new Transaccion();
        double saldo = PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].getSaldoActual();  // el valor del saldo actual
        String voucher = "Error\nEl depósito no se puede realizar\nSe alcanzó el número máximo de movimientos";
        if (PrincipalSoftwareBancario.getCajero1().columnaTransaccion < PrincipalSoftwareBancario.getCajero1().transaccion[indice].length) {  // comprueba el numero de transacciones
            PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].setSaldoActual(saldo + deposito); // modifico el saldo actual
            transaccion1.setMontoTransaccion(deposito);  //set del monto
            transaccion1.setTipoTransaccion(TipoTransaccion.DEP);   //set del tipo 
            transaccion1.setFechaTransaccion(Fecha());   //set de fecha
            transaccion1.setSaldoActual(saldo + deposito);
            PrincipalSoftwareBancario.getCajero1().transaccion[PrincipalSoftwareBancario.getNumUser() - 1][columnaTransaccion] = transaccion1;  //creo el objeto
            voucher = PrincipalSoftwareBancario.getCajero1().transaccion[PrincipalSoftwareBancario.getNumUser() - 1][columnaTransaccion].toString(); // le asigno el toString
            columnaTransaccion++;
        }
        return voucher;
    }

    public String retiro(double montoRetiro) {
        transaccion1 = new Transaccion();
        String desgloseBilletes = "";
        int numBilletes = 0;
        double saldo = PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].getSaldoActual();   // el valor del saldo actual
        String voucher = "Error\nEl depósito no se puede realizar\nSe alcanzó el número máximo de movimientos";
        if (saldo >= 1250) {
            if (PrincipalSoftwareBancario.getCajero1().columnaTransaccion < PrincipalSoftwareBancario.getCajero1().transaccion[indice].length) {  // comprueba el numero de transacciones
                if (montoRetiro % 1000 == 0 && montoRetiro <= 100000) {
                    if ((saldo - montoRetiro) >= 250 && saldo >= 1250) {
                        PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].setSaldoActual(saldo - montoRetiro - 250); // modifico el saldo actual
                        transaccion1.setMontoTransaccion(montoRetiro * -1);  //set del monto
                        transaccion1.setTipoTransaccion(TipoTransaccion.RET);   //set del tipo 
                        transaccion1.setFechaTransaccion(Fecha());   //set de fecha
                        transaccion1.setSaldoActual(saldo - montoRetiro - 250);
                        do {
                            numBilletes = (int) montoRetiro / 50000;
                            montoRetiro = montoRetiro - (numBilletes * 50000);
                            desgloseBilletes += "\n" + numBilletes + " billetes de ₡50.000";
                        } while (montoRetiro > 50000);
                        do {
                            numBilletes = (int) montoRetiro / 20000;
                            montoRetiro = montoRetiro - (numBilletes * 20000);
                            desgloseBilletes += "\n" + numBilletes + " billetes de ₡20.000";
                        } while (montoRetiro > 20000);
                        do {
                            numBilletes = (int) montoRetiro / 10000;
                            montoRetiro = montoRetiro - (numBilletes * 10000);
                            desgloseBilletes += "\n" + numBilletes + " billetes de ₡10.000";
                        } while (montoRetiro > 10000);
                        do {
                            numBilletes = (int) montoRetiro / 5000;
                            montoRetiro = montoRetiro - (numBilletes * 5000);
                            desgloseBilletes += "\n" + numBilletes + " billetes de ₡5.000";
                        } while (montoRetiro > 5000);
                        do {
                            numBilletes = (int) montoRetiro / 2000;
                            montoRetiro = montoRetiro - (numBilletes * 2000);
                            desgloseBilletes += "\n" + numBilletes + " billetes de ₡2.000";
                        } while (montoRetiro > 2000);
                        do {
                            numBilletes = (int) montoRetiro / 1000;
                            montoRetiro = montoRetiro - (numBilletes * 1000);
                            desgloseBilletes += "\n" + numBilletes + " billetes de ₡1.000";
                        } while (montoRetiro > 1000);
                        PrincipalSoftwareBancario.getCajero1().transaccion[PrincipalSoftwareBancario.getNumUser() - 1][columnaTransaccion] = transaccion1;  //creo el objeto
                        voucher = PrincipalSoftwareBancario.getCajero1().transaccion[PrincipalSoftwareBancario.getNumUser() - 1][columnaTransaccion].toString()
                                + "\nSe desconto del saldo de su cuenta:\nComisión por retiro: ₡250" + "\n" + desgloseBilletes; // le asigno el toString
                        columnaTransaccion++;
                    } else {
                        voucher = "Su saldo es insuficiente para realizar el retiro";
                    }
                } else {
                    voucher = "ERROR\nLa cantidad ingresada no es valida par un retiro bancario\nIntentalo nuevamente";
                }

            }

        } else {
            voucher = "Su saldo es insuficiente para realizar el retiro";
        }
        return voucher;
    }

    public String trasnferencia(int numCuenta, double montoATransferir) {
        String aviso = "";
        transaccion1 = new Transaccion();
        transaccion2 = new Transaccion();
        String voucher = "Error\nEl movimiento no se puede realizar\nSe alcanzó el número máximo de movimientos";
        double saldoCuentaIniciada = PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].getSaldoActual();  // el valor del saldo actualde la cuenta iniciada

        if (PrincipalSoftwareBancario.getCajero1().columnaTransaccion < PrincipalSoftwareBancario.getCajero1().transaccion[indice].length) {  // comprueba el numero de transacciones
            if (PrincipalSoftwareBancario.getCajero1().cuenta[numCuenta - 1].getNumCuenta() == numCuenta) {
                if (saldoCuentaIniciada >= montoATransferir) {
                    double saldoCuentaATransferir = PrincipalSoftwareBancario.getCajero1().cuenta[numCuenta - 1].getSaldoActual();  // el valor del saldo actualde la cuenta iniciada
                    PrincipalSoftwareBancario.getCajero1().cuenta[numCuenta - 1].setSaldoActual(saldoCuentaATransferir + montoATransferir); // modifico el saldo actual
                    transaccion2.setMontoTransaccion(montoATransferir);  //set del monto
                    transaccion2.setTipoTransaccion(TipoTransaccion.DTR);   //set del tipo 
                    transaccion2.setFechaTransaccion(Fecha());   //set de fecha
                    transaccion2.setSaldoActual(saldoCuentaATransferir + montoATransferir);
                    PrincipalSoftwareBancario.getCajero1().transaccion[numCuenta - 1][columnaTransaccion] = transaccion2;  //creo el objeto
                    PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].setSaldoActual(saldoCuentaIniciada - montoATransferir); // modifico el saldo actual
                    transaccion1.setMontoTransaccion(montoATransferir * -1);  //set del monto
                    transaccion1.setTipoTransaccion(TipoTransaccion.DTR);   //set del tipo 
                    transaccion1.setFechaTransaccion(Fecha());   //set de fecha
                    transaccion1.setSaldoActual(saldoCuentaIniciada - montoATransferir);
                    PrincipalSoftwareBancario.getCajero1().transaccion[PrincipalSoftwareBancario.getNumUser() - 1][columnaTransaccion] = transaccion1;  //creo el objeto
                    voucher = "\nCuenta número: " + numCuenta + "\nSe le transfirió: " + montoATransferir;
                    aviso = PrincipalSoftwareBancario.getCajero1().transaccion[PrincipalSoftwareBancario.getNumUser() - 1][columnaTransaccion].toString() + voucher; // le asigno el toString
                    columnaTransaccion++;

                } else {
                    aviso = "ERROR\nSu cuenta no tiene el saldo suficiente para realizar la transferencia";
                }
            } else {
                aviso = "ERROR\nLa cuenta a la que se desea transferir no es existente";
            }

        }
        return aviso;
    }

    public String pagoDeElectricidad(String NISE, double montoPago) {
        transaccion1 = new Transaccion();
        double saldo = PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].getSaldoActual();  // el valor del saldo actual
        String voucher = "Error\nEl movimiento no se puede realizar\nSe alcanzó el número máximo de movimientos";
        if (PrincipalSoftwareBancario.getCajero1().columnaTransaccion < PrincipalSoftwareBancario.getCajero1().transaccion[indice].length) {  // comprueba el numero de transacciones
            if (PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].getCedula().equals(NISE)) {
                if (saldo > montoPago) {
                    PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].setSaldoActual(saldo - montoPago); // modifico el saldo actual
                    transaccion1.setMontoTransaccion(montoPago * -1);  //set del monto
                    transaccion1.setTipoTransaccion(TipoTransaccion.ELT);   //set del tipo 
                    transaccion1.setFechaTransaccion(Fecha());   //set de fecha
                    transaccion1.setSaldoActual(saldo - montoPago);
                    PrincipalSoftwareBancario.getCajero1().transaccion[PrincipalSoftwareBancario.getNumUser() - 1][columnaTransaccion] = transaccion1;  //creo el objeto
                    voucher = PrincipalSoftwareBancario.getCajero1().transaccion[PrincipalSoftwareBancario.getNumUser() - 1][columnaTransaccion].toString();
                    columnaTransaccion++;

                } else {
                    voucher = "El saldo de su cuenta es insuficiente para la cancelación del pago";
                }
            } else {
                voucher = "Su NISE no coincide";
            }
        }
        return voucher;
    }

    public String pagoDeTelefono(String numTelf, double montoPago) {
        transaccion1 = new Transaccion();
        double saldo = PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].getSaldoActual();  // el valor del saldo actual
        String voucher = "Error\nEl movimiento no se puede realizar\nSe alcanzó el número máximo de movimientos";
        if (PrincipalSoftwareBancario.getCajero1().columnaTransaccion < PrincipalSoftwareBancario.getCajero1().transaccion[indice].length) {  // comprueba el numero de transacciones
            if (PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].getNumTelf().equals(numTelf)) {
                if (saldo > montoPago) {
                    PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].setSaldoActual(saldo - montoPago); // modifico el saldo actual
                    transaccion1.setMontoTransaccion(montoPago * -1);  //set del monto
                    transaccion1.setTipoTransaccion(TipoTransaccion.TEL);   //set del tipo 
                    transaccion1.setFechaTransaccion(Fecha());   //set de fecha
                    transaccion1.setSaldoActual(saldo - montoPago);
                    PrincipalSoftwareBancario.getCajero1().transaccion[PrincipalSoftwareBancario.getNumUser() - 1][columnaTransaccion] = transaccion1;  //creo el objeto
                    voucher = PrincipalSoftwareBancario.getCajero1().transaccion[PrincipalSoftwareBancario.getNumUser() - 1][columnaTransaccion].toString();
                    columnaTransaccion++;

                } else {
                    voucher = "El saldo de su cuenta es insuficiente para la cancelación del pago";
                }
            } else {
                voucher = "Su número de teléfono no coincide";
            }
        }
        return voucher;
    }

    public String pagoDeServiciosMunicipales(double montoPago) {
        transaccion1 = new Transaccion();
        double saldo = PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].getSaldoActual();  // el valor del saldo actual
        String voucher = "Error\nEl movimiento no se puede realizar\nSe alcanzó el número máximo de movimientos";
        if (PrincipalSoftwareBancario.getCajero1().columnaTransaccion < PrincipalSoftwareBancario.getCajero1().transaccion[indice].length) {  // comprueba el numero de transacciones
            if (saldo > montoPago) {
                PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].setSaldoActual(saldo - montoPago); // modifico el saldo actual
                transaccion1.setMontoTransaccion(montoPago * -1);  //set del monto
                transaccion1.setTipoTransaccion(TipoTransaccion.SMP);   //set del tipo 
                transaccion1.setFechaTransaccion(Fecha());   //set de fecha
                transaccion1.setSaldoActual(saldo - montoPago);
                PrincipalSoftwareBancario.getCajero1().transaccion[PrincipalSoftwareBancario.getNumUser() - 1][columnaTransaccion] = transaccion1;  //creo el objeto
                voucher = PrincipalSoftwareBancario.getCajero1().transaccion[PrincipalSoftwareBancario.getNumUser() - 1][columnaTransaccion].toString();
                columnaTransaccion++;

            } else {
                voucher = "El saldo de su cuenta es insuficiente para la cancelación del pago";
            }

        }
        return voucher;
    }

    public String pagoSINPE(String numTelf, double montoPago) {
        transaccion1 = new Transaccion();
        double saldo = PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].getSaldoActual();  // el valor del saldo actual
        String voucher = "Error\nEl movimiento no se puede realizar\nSe alcanzó el número máximo de movimientos";
        if (PrincipalSoftwareBancario.getCajero1().columnaTransaccion < PrincipalSoftwareBancario.getCajero1().transaccion[indice].length) {  // comprueba el numero de transacciones
            if (PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].getNumTelf().equals(numTelf)) {
                if (saldo > montoPago) {
                    PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].setSaldoActual(saldo - montoPago); // modifico el saldo actual
                    transaccion1.setMontoTransaccion(montoPago * -1);  //set del monto
                    transaccion1.setTipoTransaccion(TipoTransaccion.SIM);   //set del tipo 
                    transaccion1.setFechaTransaccion(Fecha());   //set de fecha
                    transaccion1.setSaldoActual(saldo - montoPago);
                    PrincipalSoftwareBancario.getCajero1().transaccion[PrincipalSoftwareBancario.getNumUser() - 1][columnaTransaccion] = transaccion1;  //creo el objeto
                    voucher = PrincipalSoftwareBancario.getCajero1().transaccion[PrincipalSoftwareBancario.getNumUser() - 1][columnaTransaccion].toString();
                    columnaTransaccion++;

                } else {
                    voucher = "El saldo de su cuenta es insuficiente para realizar el SINPE";
                }
            } else {
                voucher = "Su número de teléfono no coincide";
            }
        }
        return voucher;
    }

    public String reporteMovimientos() {
        String voucher = "";
        for (int i = 0; i < PrincipalSoftwareBancario.getCajero1().columnaTransaccion; i++) {
            voucher += "\n\n" + PrincipalSoftwareBancario.getCajero1().transaccion[PrincipalSoftwareBancario.getNumUser() - 1][i].toString();
        }
        return voucher;
    }

    public String cambioPin(String nuevoPin, String pinAnterior) {
        String resultado = "";
        if (nuevoPin.length() == 4) {
            if (PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].getPasswordCod().equals(pinAnterior)) {
                PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].setPassword(nuevoPin);
                char pin = 0;
                int remplazaDigito = 0;
                String password = PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].getPassword();
                String passwordCod = "";
                for (int i = 0; i < 4; i++) {
                    pin = password.charAt(i);
                    remplazaDigito = Character.getNumericValue(pin);
                    remplazaDigito = (remplazaDigito + 7) % 10;
                    passwordCod += remplazaDigito;
                }
                char p1 = passwordCod.charAt(0);
                char p2 = passwordCod.charAt(1);
                char p3 = passwordCod.charAt(2);
                char p4 = passwordCod.charAt(3);
                passwordCod = String.valueOf("" + p3 + p4 + p1 + p2);
                PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].setPasswordCod(passwordCod);
                resultado = "El nuevo PIN es : " + PrincipalSoftwareBancario.getCajero1().cuenta[PrincipalSoftwareBancario.getNumUser() - 1].getPasswordCod();
            } else {
                resultado = "ERROR\nLos pines no coinciden\nIntentelo nuevamente";
            }
        } else {
            resultado = "ERROR\nDebe digitar 4 valores númericos";
        }
        return resultado;
    }
}
