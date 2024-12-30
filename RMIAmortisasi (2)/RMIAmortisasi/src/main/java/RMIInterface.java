/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
    // Define a remote method to calculate loan amortization
    String calculateAmortization(double principal, double rate, int term) throws RemoteException;
}

