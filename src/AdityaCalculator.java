import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdityaCalculator extends JFrame implements ActionListener {

    private JTextField display;
    private String currentInput;

    public AdityaCalculator() {
        setTitle("Aditya Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);

        initUI();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initUI() {
        currentInput = "";
        display = new JTextField(10);
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 10, 10));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = createButton(label);
            buttonPanel.add(button);
        }

        setLayout(new BorderLayout(10, 10));
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        button.setFocusPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(230, 230, 230));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(UIManager.getColor("control"));
            }
        });

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("0123456789.".contains(command)) {
            currentInput += command;
        } else if ("÷×−+".contains(command)) {
            if (!currentInput.isEmpty()) {
                currentInput += " " + command + " ";
            }
        } else if ("=".equals(command)) {
            calculateResult();
        }

        updateDisplay();
    }

    private void calculateResult() {
        try {
            String[] tokens = currentInput.split(" ");
            double result = Double.parseDouble(tokens[0]);

            for (int i = 1; i < tokens.length; i += 2) {
                String operator = tokens[i];
                double operand = Double.parseDouble(tokens[i + 1]);

                switch (operator) {
                    case "÷":
                        result /= operand;
                        break;
                    case "×":
                        result *= operand;
                        break;
                    case "−":
                        result -= operand;
                        break;
                    case "+":
                        result += operand;
                        break;
                }
            }

            currentInput = Double.toString(result);
        } catch (Exception ex) {
            currentInput = "Error";
        }
    }

    private void updateDisplay() {
        display.setText(currentInput);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdityaCalculator());
    }
}
