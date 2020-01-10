package br.com.cristiano.wakanda.model;

import java.util.Optional;

public enum Languages {
    DE(1, "Ich möchte auf Deutsch spielen"), EN(0, "I want to play in English (Default)"), ES(2,
            "Quiero jugar en español"), PT(3, "Quero jogar em Português");

    private static final int[] ids;

    static {
        Languages[] values = Languages.values();
        ids = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            ids[i] = values[i].id;
        }
    }

    private int id;
    private String welcome;

    private Languages(int id, String welcome) {
        this.welcome = welcome;
        this.id = id;
    }

    public static int[] getIds() {
        return ids;
    }

    public static Optional<Languages> recoverById(int id) {
        for (Languages langague : Languages.values()) {
            if (langague.getId() == id) {
                return Optional.of(langague);
            }
        }
        return Optional.empty();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "[" + this.id + "] " + this.welcome;
    }

}
