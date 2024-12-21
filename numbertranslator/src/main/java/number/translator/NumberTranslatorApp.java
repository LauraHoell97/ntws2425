package number.translator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NumberTranslatorApp extends Application {

    private static final int SCENE_WIDTH = 640;
    private static final int SCENE_HEIGHT = 480;
    private static final String[] LANGUAGES = { "german", "english", "french", "spanish" };

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        GridPane root = createGridPane();

        TextField input = new TextField();
        TextField output = new TextField();
        output.setEditable(false);
        ComboBox<String> cmbLanguage = new ComboBox<>();
        cmbLanguage.getItems().addAll(LANGUAGES);

        addUIComponents(root, input, output, cmbLanguage);

        Button convertButton = new Button("Convert");
        root.add(convertButton, 1, 4);

        convertButton.setOnAction(event -> {
            String inputText = input.getText();
            String selectedLanguage = cmbLanguage.getValue();
            String outputText = convertNumber(inputText, selectedLanguage);
            output.setText(outputText);
        });

        scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Number Translator");
        stage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        return gridPane;
    }

    private void addUIComponents(GridPane root, TextField input, TextField output, ComboBox<String> cmbLanguage) {
        root.add(new Label("Input:"), 0, 1);
        root.add(input, 1, 1);

        root.add(new Label("Output:"), 0, 2);
        root.add(output, 1, 2);

        root.add(new Label("Language"), 0, 3);
        root.add(cmbLanguage, 1, 3);
    }

    private String convertNumber(String input, String language) {
        if (input == null || input.isEmpty() || language == null || language.isEmpty()) {
            return "";
        }

        Map<Character, String> digitNames = getDigitNames(language);
        StringBuilder result = new StringBuilder();

        for (char digit : input.toCharArray()) {
            if (digitNames.containsKey(digit)) {
                if (result.length() > 0) {
                    result.append(" ");
                }
                result.append(digitNames.get(digit));
            } else {
                return "Invalid input";
            }
        }

        return result.toString();
    }

    private Map<Character, String> getDigitNames(String language) {
        Map<Character, String> digitNames = new HashMap<>();
        switch (language.toLowerCase()) {
            case "german" -> {
                digitNames.put('0', "null");
                digitNames.put('1', "eins");
                digitNames.put('2', "zwei");
                digitNames.put('3', "drei");
                digitNames.put('4', "vier");
                digitNames.put('5', "fünf");
                digitNames.put('6', "sechs");
                digitNames.put('7', "sieben");
                digitNames.put('8', "acht");
                digitNames.put('9', "neun");
            }
            case "english" -> {
                digitNames.put('0', "zero");
                digitNames.put('1', "one");
                digitNames.put('2', "two");
                digitNames.put('3', "three");
                digitNames.put('4', "four");
                digitNames.put('5', "five");
                digitNames.put('6', "six");
                digitNames.put('7', "seven");
                digitNames.put('8', "eight");
                digitNames.put('9', "nine");
            }
            case "french" -> {
                digitNames.put('0', "zéro");
                digitNames.put('1', "un");
                digitNames.put('2', "deux");
                digitNames.put('3', "trois");
                digitNames.put('4', "quatre");
                digitNames.put('5', "cinq");
                digitNames.put('6', "six");
                digitNames.put('7', "sept");
                digitNames.put('8', "huit");
                digitNames.put('9', "neuf");
            }
            case "spanish" -> {
                digitNames.put('0', "cero");
                digitNames.put('1', "uno");
                digitNames.put('2', "dos");
                digitNames.put('3', "tres");
                digitNames.put('4', "cuatro");
                digitNames.put('5', "cinco");
                digitNames.put('6', "seis");
                digitNames.put('7', "siete");
                digitNames.put('8', "ocho");
                digitNames.put('9', "nuevo");
            }
            // prinzipiell unmöglich aber warum nicht
            default -> throw new IllegalArgumentException("Unsupported language: " + language);
        }
        return digitNames;
    }

    public static void main(String[] args) {
        launch();
    }
}