package br.com.cristiano.wakanda.view;

import br.com.cristiano.wakanda.view.util.MessagesUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

public class BasicView {

    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void changeDefaultInput(ByteArrayInputStream input) {
        BasicView.input = new BufferedReader(new InputStreamReader(input));
    }

    public void clear() {
        printText("\033[H\033[2J");
    }

    private boolean isValid(int option, int[] options) {
        for (int i : options) {
            if (i == option) {
                return true;
            }
        }
        return false;
    }

    public void printMessage(String key) {
        printText(MessagesUtils.getText(key));
    }

    public void printMessage(String key, Object... params) {
        printText(MessagesUtils.getText(key, params));
    }

    public void printText(List<String> texts) {
        texts.forEach(t -> System.out.println(t));
    }

    public void printText(String text) {
        System.out.println(text);
    }

    public int readIntUserInput(Optional<Integer> defaultValue, int[] options) {
        String textOption;
        Integer option = null;
        try {
            textOption = input.readLine();
            option = Integer.parseInt(textOption);
        } catch (NumberFormatException | IOException e) {
            option = solveDefaultCase(input, defaultValue, options);
        }
        if (!isValid(option, options)) {
            option = solveDefaultCase(input, defaultValue, options);
        }
        return option;
    }

    public String readStringUserInput() {
        String text = null;
        try {
            text = input.readLine();
        } catch (IOException e) {
            System.out.println(MessagesUtils.getText("game.user.input.error"));
            text = readStringUserInput();
        }
        return text;
    }

    private Integer solveDefaultCase(BufferedReader input, Optional<Integer> defaultValue, int[] options) {
        if (defaultValue.isPresent()) {
            return defaultValue.get();
        } else {
            System.out.println(MessagesUtils.getText("game.user.input.error"));
            return readIntUserInput(defaultValue, options);
        }
    }
}
