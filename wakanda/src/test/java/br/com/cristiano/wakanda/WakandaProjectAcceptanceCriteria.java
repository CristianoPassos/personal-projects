package br.com.cristiano.wakanda;

import org.junit.Before;
import org.junit.Test;

public class WakandaProjectAcceptanceCriteria extends BaseTest {
    @Test
    public void as_a_player_I_want_to_create_a_character() {
        setUserInput("0\n0\nCristiano de Oliveira Passos\n97".getBytes());
        WakandaProject.main(null);
        assertMessageIsPresent("game.exit.author");
    }

    @Test
    public void as_a_player_I_want_to_explore() {
        setUserInput("0\n0\nCristiano de Oliveira Passos\n1\n0\n1\n0\n97".getBytes());
        WakandaProject.main(null);
        assertMessageIsPresent("game.command.move.welcome", "Birnin T'Chaka - City");
        assertMessageIsPresent("game.command.move.welcome", "Birnin Azzaria - City");
    }

    @Test
    public void as_a_player_I_want_to_gain_experience_through_fighting() {
        setUserInput(
                "0\n0\nCristiano de Oliveira Passos\n2\n2\n2\n2\n2\n2\n2\n2\n2\n2\n2\n1\n0\n2\n2\n2\n2\n2\n2\n2\n2\n2\n97"
                        .getBytes());
        WakandaProject.main(null);
        assertMessageIsPresent("game.character.level", 2);
    }

    @Test
    public void as_a_player_I_want_to_save_and_resume_a_game() {
        saveGameTest();
        setUpBeforeTest();
        resumeGameTest();
    }

    private void resumeGameTest() {
        setUserInput("0\n1\n0\n97".getBytes());
        WakandaProject.main(null);
        assertMessageIsPresent("game.welcome", "Cristiano de Oliveira Passos");
    }

    private void saveGameTest() {
        setUserInput("0\n0\nCristiano de Oliveira Passos\n99\n97".getBytes());
        WakandaProject.main(null);
        assertMessageIsPresent("service.storage.save.success");
    }

    @Before
    @Override
    public void setUpBeforeTest() {
        super.setUpBeforeTest();
        WakandaProject.setExit(0);
    }

}