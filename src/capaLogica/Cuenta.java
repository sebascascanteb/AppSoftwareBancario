/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaLogica;

/**
 *
 * @author Sebas
 */
public class Cuenta {

    private static int contCuenta = 1;
    private int numCuenta;
    private String password;
    private String passwordCod;
    private String cedula;
    private String nomCompleto;
    private String numTelf;
    private double saldoActual;
    private int contaTransaccion;  // lleva el numero de transacciones

    public Cuenta() {
        this.password = "";
        this.passwordCod = "";
        this.cedula = "";
        this.nomCompleto = "";
        this.numTelf = "";
        this.saldoActual = 0.0;
        this.numCuenta = contCuenta;
        this.contaTransaccion = 0;

    }

    public String crearContra() {  //crea la contraseña
        int randomNum = 0;
        int num = 0;
        for (int i = 1; i <= 4; i++) {
            randomNum = (int) (Math.random() * 10);
            if (randomNum == num) {
                while (randomNum == num) {
                    randomNum = (int) (Math.random() * 10);
                }
            }
            password += randomNum;
            num = randomNum;
        }
        return password;
    }

    public String codificarClave() { //codifica la clave
        char pin = 0;
        int remplazaDigito = 0;

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
        return passwordCod;
    }

    public String toString() {  //toString con todos los datos
        return "Número de Cuenta: " + numCuenta
                + "\n---------------------------------------------------------"
                + "\nNombre: " + nomCompleto
                + "\n---------------------------------------------------------"
                + "\nContraseña: " + password
                + "\n---------------------------------------------------------"
                + "\nLa clave codificada es: " + passwordCod
                + "\n---------------------------------------------------------"
                + "\nCédula: " + cedula
                + "\n---------------------------------------------------------"
                + "\nNúmero de teléfono: " + numTelf
                + "\n---------------------------------------------------------"
                + "\nSaldo actual: ₡" + saldoActual;
    }

    public String toString2() { //toString con todos los datos - la clave normal (sin Codificar)
        return "Número de Cuenta: " + numCuenta
                + "\n---------------------------------------------------------"
                + "\nNombre: " + nomCompleto
                + "\n---------------------------------------------------------"
                + "\nLa clave codificada es: " + passwordCod
                + "\n---------------------------------------------------------"
                + "\nCédula: " + cedula
                + "\n---------------------------------------------------------"
                + "\nNúmero de teléfono: " + numTelf
                + "\n---------------------------------------------------------"
                + "\nSaldo actual: ₡" + saldoActual;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCod() {
        return passwordCod;
    }

    public void setPasswordCod(String passwordCod) {
        this.passwordCod = passwordCod;
    }

    public String getNumTelf() {
        return numTelf;
    }

    public void setNumTelf(String numTelf) {
        this.numTelf = numTelf;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNomCompleto(String nomCompleto) {
        this.nomCompleto = nomCompleto;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public void setNumCuenta() { ////////// Aqui se ejecuta la sumatoria del contador del numero de la cuenta
        this.numCuenta = numCuenta;
        contCuenta++;
    }

    public String getPassword() {
        return password;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNomCompleto() {
        return nomCompleto;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public int getContaTransaccion() {
        return contaTransaccion;
    }

    public void setContaTransaccion(int contaTransaccion) {
        this.contaTransaccion = contaTransaccion;
    }

}
