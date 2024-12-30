/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author muham
 */
public class RMIServer extends UnicastRemoteObject implements RMIInterface {

    // Constructor
    protected RMIServer() throws RemoteException {
        super();
    }

    // Implementasi metode perhitungan amortisasi
    public String calculateAmortization(double principal, double rate, int term) throws RemoteException {
        double monthlyRate = rate / 12 / 100;
        int months = term * 12;
        double monthlyPayment = (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));

        StringBuilder amortizationSchedule = new StringBuilder();
        amortizationSchedule.append("Month\tPrincipal\tInterest\tBalance\n");

        double balance = principal;
        for (int i = 1; i <= months; i++) {
            double interest = balance * monthlyRate;
            double principalPaid = monthlyPayment - interest;
            balance -= principalPaid;

            amortizationSchedule.append(i).append("\t").append(String.format("%.2f", principalPaid)).append("\t")
                    .append(String.format("%.2f", interest)).append("\t").append(String.format("%.2f", Math.max(balance, 0))).append("\n");
        }

        return amortizationSchedule.toString();
    }

    public static void main(String[] args) {
        try {
            // Membuat dan mengikat server RMI
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            RMIServer server = new RMIServer();
            java.rmi.Naming.rebind("rmi://172.125.1.195:1099/AmortizationService", server);
            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

