package br.com.cristiano.wakanda.controller.util;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.cristiano.wakanda.controller.commands.Commands;
import br.com.cristiano.wakanda.model.Languages;
import br.com.cristiano.wakanda.view.util.MessagesUtils;

public class MessagesUtilsTest {
	@Test
	public void loadMessagesDETest() {
		String message = MessagesUtils.load(Languages.DE);
		assertEquals(message, "Nachrichten mit Erfolg geladen");
	}

	private void loadMessagesEn() {
		MessagesUtils.load(Languages.EN);
	}

	@Test
	public void loadMessagesEnTest() {
		String message = MessagesUtils.load(Languages.EN);
		assertEquals(message, "Messages loaded with success");
	}

	@Test
	public void loadMessagesESTest() {
		String message = MessagesUtils.load(Languages.ES);
		assertEquals(message, "Mensajes cargados con éxito");
	}

	@Test
	public void loadMessagesPTTest() {
		String message = MessagesUtils.load(Languages.PT);
		assertEquals(message, "Mensagens carregadas com sucesso");
	}

	@Test
	public void messagesRecoveryByPrefixTest() {
		loadMessagesEn();
		List<String> messages = MessagesUtils.getAllByPrefix("game.command.list.commands.describe");
		assertEquals(messages.size(), Commands.values().length);
	}

	@Test
	public void messagesRecoveryTest() {
		loadMessagesEn();
		String message = MessagesUtils.getText("service.storage.save.success");
		assertEquals(message, "Game saved with success");
	}

	@Test
	public void messagesRecoveryWithParamsTest() {
		loadMessagesEn();
		String message = MessagesUtils.getText("game.setup.new.start", "Cristiano Passos");
		assertEquals(message, "Staring now Cristiano Passos!");
	}
}