package br.com.cristiano.wakanda.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.cristiano.wakanda.view.util.MessagesUtils;

public class Storage {

	private String gameFile = "games.data";

	private List<Game> createGameList(Game game) {
		List<Game> games = loadGames();
		if (games.contains(game)) {
			games.remove(game);
		}
		games.add(game);
		return games;
	}

	public String deleteGames() {
		File file = new File(this.gameFile);
		file.delete();
		return "service.storage.removed.success";
	}

	private boolean isGamesStored() {
		File file = new File(this.gameFile);
		if (file != null && file.isFile()) {
			return true;
		}
		return false;
	}

	public List<Game> loadGames() {
		if (isGamesStored()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(this.gameFile)));
				@SuppressWarnings("unchecked")
				List<Game> games = (List<Game>) ois.readObject();
				ois.close();
				return games;
			} catch (IOException | ClassNotFoundException e) {
				System.out.println(MessagesUtils.getText("service.storage.load.error"));
				deleteGames();
				loadGames();
			}
		}
		return new ArrayList<>();
	}

	public String saveGame(Game game) throws IOException {
		List<Game> games = createGameList(game);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(this.gameFile)));
		oos.writeObject(games);
		oos.close();
		return "service.storage.save.success";
	}
}