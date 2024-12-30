import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class RMIClientGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Amortization Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 500);
            frame.setLayout(new GridLayout(6, 2));

            // Input fields
            JLabel principalLabel = new JLabel("Principal:");
            JTextField principalField = new JTextField();

            JLabel rateLabel = new JLabel("Interest Rate (%):");
            JTextField rateField = new JTextField();

            JLabel termLabel = new JLabel("Term (Years):");
            JTextField termField = new JTextField();

            JButton calculateButton = new JButton("Calculate");
            JTextArea resultArea = new JTextArea();
            resultArea.setEditable(false);

            // Add components to the frame
            frame.add(principalLabel);
            frame.add(principalField);
            frame.add(rateLabel);
            frame.add(rateField);
            frame.add(termLabel);
            frame.add(termField);
            frame.add(new JLabel()); // Empty cell
            frame.add(calculateButton);
            frame.add(new JLabel("Amortization Schedule:"));
            frame.add(new JScrollPane(resultArea));

            // Button action
            calculateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        // Get inputs
                        double principal = Double.parseDouble(principalField.getText());
                        double rate = Double.parseDouble(rateField.getText());
                        int term = Integer.parseInt(termField.getText());

                        // Lookup the RMI service
                        RMIInterface service = (RMIInterface) Naming.lookup("rmi://172.125.1.195:1099/AmortizationService");

                        // Call the remote method
                        String result = service.calculateAmortization(principal, rate, term);

                        // Display the result
                        resultArea.setText(result);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            frame.setVisible(true);
        });
    }
}
