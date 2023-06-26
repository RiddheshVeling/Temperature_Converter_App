import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TemperatureConverterApp extends Application {
    private TextField inputField;
    private ComboBox<String> fromUnitComboBox;
    private ComboBox<String> toUnitComboBox;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Temperature Converter");

        // Create the main layout
        BorderPane root = new BorderPane();

        // Create the input area
        GridPane inputPane = new GridPane();
        inputPane.setAlignment(Pos.CENTER);
        inputPane.setHgap(10);
        inputPane.setVgap(10);
        inputPane.setPadding(new Insets(20));

        Label inputLabel = new Label("Enter Temperature:");
        inputLabel.setFont(Font.font("Arial", 18));

        inputField = new TextField();
        inputField.setPromptText("Temperature");
        inputField.setFont(Font.font("Arial", 18));

        // Create the unit selection dropdowns
        Label fromUnitLabel = new Label("From:");
        fromUnitLabel.setFont(Font.font("Arial", 18));
        fromUnitComboBox = new ComboBox<>(FXCollections.observableArrayList("Celsius", "Fahrenheit", "Kelvin"));
        fromUnitComboBox.setValue("Celsius");
        fromUnitComboBox.setPrefWidth(150);
        fromUnitComboBox.setStyle("-fx-font-size: 16;");

        Label toUnitLabel = new Label("To:");
        toUnitLabel.setFont(Font.font("Arial", 18));
        toUnitComboBox = new ComboBox<>(FXCollections.observableArrayList("Celsius", "Fahrenheit", "Kelvin"));
        toUnitComboBox.setValue("Fahrenheit");
        toUnitComboBox.setPrefWidth(150);
        toUnitComboBox.setStyle("-fx-font-size: 16;");

        Button convertButton = new Button("Convert");
        convertButton.setFont(Font.font("Arial", 18));
        convertButton.setOnAction(event -> convertTemperature());

        inputPane.add(inputLabel, 0, 0);
        inputPane.add(inputField, 1, 0);
        inputPane.add(fromUnitLabel, 0, 1);
        inputPane.add(fromUnitComboBox, 1, 1);
        inputPane.add(toUnitLabel, 0, 2);
        inputPane.add(toUnitComboBox, 1, 2);
        inputPane.add(convertButton, 0, 3, 2, 1);
        GridPane.setMargin(convertButton, new Insets(10, 0, 0, 0));

        // Create the result area
        HBox resultPane = new HBox();
        resultPane.setAlignment(Pos.CENTER);
        resultPane.setPadding(new Insets(20));

        resultLabel = new Label();
        resultLabel.setFont(Font.font("Arial", 18));

        resultPane.getChildren().add(resultLabel);

        // Set the background color
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));

        root.setCenter(inputPane);
        root.setBottom(resultPane);

        // Apply any additional styling or transitions here

        // Create the scene and show the stage
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(inputField.getText());
            String fromUnit = fromUnitComboBox.getValue();
            String toUnit = toUnitComboBox.getValue();

            double result = convert(fromUnit, toUnit, temperature);

            resultLabel.setText(String.format("%.2f %s = %.2f %s", temperature, fromUnit, result, toUnit));
            resultLabel.setTextFill(Color.GREEN);
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input");
            resultLabel.setTextFill(Color.RED);
        }
    }

    private double convert(String fromUnit, String toUnit, double temperature) {
        // Convert the temperature from the "fromUnit" to "toUnit"
        if (fromUnit.equals("Celsius")) {
            if (toUnit.equals("Fahrenheit")) {
                return (temperature * 9 / 5) + 32;
            } else if (toUnit.equals("Kelvin")) {
                return temperature + 273.15;
            }
        } else if (fromUnit.equals("Fahrenheit")) {
            if (toUnit.equals("Celsius")) {
                return (temperature - 32) * 5 / 9;
            } else if (toUnit.equals("Kelvin")) {
                return (temperature + 459.67) * 5 / 9;
            }
        } else if (fromUnit.equals("Kelvin")) {
            if (toUnit.equals("Celsius")) {
                return temperature - 273.15;
            } else if (toUnit.equals("Fahrenheit")) {
                return (temperature * 9 / 5) - 459.67;
            }
        }

        // If the units are the same, return the input temperature
        return temperature;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
