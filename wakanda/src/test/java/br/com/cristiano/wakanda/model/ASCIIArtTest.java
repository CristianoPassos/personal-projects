package br.com.cristiano.wakanda.model;

import br.com.cristiano.wakanda.BaseTest;
import org.junit.Test;

import java.util.List;

public class ASCIIArtTest extends BaseTest {
    ASCIIArt service = new ASCIIArt();

    private void printArt(List<String> art) {
        art.stream().forEach(a -> System.out.println(a));

    }

    @Test
    public void printLevelUpTest() {
        List<String> art = this.service.createArt("game.level.up");
        printArt(art);
    }

    @Test
    public void printWakandaTest() {
        List<String> art = this.service.createArt("game.name");
        printArt(art);
    }

}
