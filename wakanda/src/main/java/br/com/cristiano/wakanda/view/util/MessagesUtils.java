package br.com.cristiano.wakanda.view.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;

import br.com.cristiano.wakanda.model.Languages;

public class MessagesUtils {

	private static final Properties messages = new Properties();

	private static Predicate<Object> createPredicate(String prefix) {
		return new Predicate<Object>() {
			@Override
			public boolean test(Object t) {
				return t.toString().contains(prefix);
			}
		};
	}

	public static List<String> getAllByPrefix(String prefix) {
		List<String> output = new ArrayList<>();
		Predicate<Object> predicate = createPredicate(prefix);
		messages.keySet().stream().filter(predicate).forEach(key -> output.add(getText(key.toString())));
		return output;
	}

	public static String getText(String key) {
		return messages.getProperty(key);
	}

	public static String getText(String key, Object... params) {
		return MessageFormat.format(getText(key), params);
	}

	public static String load(Languages language) {
		try {
			loadMessages(language);
			return messages.getProperty("messages.loaded.success");
		} catch (IOException e) {
			return "### Error while Loading Messages File ###\n" + e.getMessage();
		}
	}

	private static void loadMessages(Languages language) throws IOException {
		InputStream input = MessagesUtils.class.getClassLoader()
				.getResourceAsStream("messages_" + language.name() + ".properties");
		messages.load(input);
		input.close();
	}

	private MessagesUtils() {
		super();
	}

}
