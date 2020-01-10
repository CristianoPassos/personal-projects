package br.com.cristiano.wakanda.view;

import br.com.cristiano.wakanda.model.Languages;

import java.util.Optional;

public class ChooseLanguageView extends BasicView {

    public Languages readLanguage() {
        int id = readIntUserInput(Optional.of(Languages.EN.getId()), Languages.getIds());
        return Languages.recoverById(id).get();
    }
}
