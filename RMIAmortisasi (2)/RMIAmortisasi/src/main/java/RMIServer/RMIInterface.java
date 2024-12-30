package RMIServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
    // Define a remote method to calculate loan amortization
    String calculateAmortization(double principal, double rate, int term) throws RemoteException;
}
