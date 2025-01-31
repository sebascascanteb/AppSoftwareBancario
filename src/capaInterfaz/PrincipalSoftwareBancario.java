/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package capaInterfaz;

import capaLogica.Cajero;
import capaLogica.Cuenta;

/**
 *
 * @author Sebas
 */
public class PrincipalSoftwareBancario {

    private static Cajero cajero1 = new Cajero();
    private static Cuenta cuenta1 = new Cuenta();
    private static int numUser = 0;
    private static String pinUser = "";

    public static Cajero getCajero1() {
        return cajero1;
    }

    public static void setCajero1(Cajero cajero1) {
        PrincipalSoftwareBancario.cajero1 = cajero1;
    }

    public static Cuenta getCuenta1() {
        return cuenta1;
    }

    public static void setCuenta1(Cuenta cuenta1) {
        PrincipalSoftwareBancario.cuenta1 = cuenta1;
    }

    public static int getNumUser() {
        return numUser;
    }

    public static void setNumUser(int numUser) {
        PrincipalSoftwareBancario.numUser = numUser;
    }

    public static String getPinUser() {
        return pinUser;
    }

    public static void setPinUser(String pinUser) {
        PrincipalSoftwareBancario.pinUser = pinUser;
    }

    public static void main(String[] args) {
        FrmMenuPrincipal frmMenuPrincipal = new FrmMenuPrincipal();
        frmMenuPrincipal.setVisible(true);
        frmMenuPrincipal.setLocationRelativeTo(null);
    }

}
