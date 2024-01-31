import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI extends JFrame {
    private JComboBox<String> tempTypeCombo;
    private JTextField inputField;
    private JLabel resultLabel, titleLabel;
    private JButton convertButton;

    public TemperatureConverterGUI() {
        setTitle("Advanced Temperature Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);  // Center the window

        // Styling Fonts
        Font titleFont = new Font("Arial", Font.BOLD, 20);
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        // Create components
        titleLabel = new JLabel("Temperature Converter");
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        tempTypeCombo = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        tempTypeCombo.setFont(labelFont);

        inputField = new JTextField(10);
        inputField.setFont(labelFont);

        convertButton = new JButton("Convert");
        convertButton.setFont(buttonFont);

        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(labelFont);
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        add(titleLabel, gbc);
        add(new JLabel("Select Type:"), gbc);
        add(tempTypeCombo, gbc);
        add(new JLabel("Enter Temperature:"), gbc);
        add(inputField, gbc);
        add(convertButton, gbc);
        add(resultLabel, gbc);

        // Action Listener for Convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performConversion();
            }
        });

        setVisible(true);
    }

    private void performConversion() {
        try {
            double temp = Double.parseDouble(inputField.getText());
            String tempType = (String) tempTypeCombo.getSelectedItem();
            String result;
            switch (tempType) {
                case "Celsius":
                    result = String.format("Fahrenheit: %.2f, Kelvin: %.2f", celsiusToFahrenheit(temp), celsiusToKelvin(temp));
                    break;
                case "Fahrenheit":
                    result = String.format("Celsius: %.2f, Kelvin: %.2f", fahrenheitToCelsius(temp), fahrenheitToKelvin(temp));
                    break;
                case "Kelvin":
                    result = String.format("Celsius: %.2f, Fahrenheit: %.2f", kelvinToCelsius(temp), kelvinToFahrenheit(temp));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + tempType);
            }
            resultLabel.setText(result);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid number.");
        }
    }

    // Temperature conversion methods
    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9 + 273.15;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9 / 5 + 32;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TemperatureConverterGUI());
    }
}
