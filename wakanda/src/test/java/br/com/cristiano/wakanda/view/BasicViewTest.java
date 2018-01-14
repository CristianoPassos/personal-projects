package br.com.cristiano.wakanda.view;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;

import br.com.cristiano.wakanda.BaseTest;

public class BasicViewTest extends BaseTest {

	private BasicView view = new BasicView();

	@Test
	public void defaultInputTest() {
		setUserInput("s5".getBytes());
		int option = view.readIntUserInput(Optional.of(3), new int[] { 1, 2, 3 });
		assertEquals(option, 3);
	}

	@Test
	public void noDefaultValueTest() {
		setUserInput(("1".getBytes()));
		int option = view.readIntUserInput(Optional.empty(), new int[] { 1, 2, 3 });
		assertEquals(option, 1);
	}

	@Test
	public void noDefaultValueWithWrongUserInputTest() {
		setUserInput("asdsa\n1".getBytes());
		int option = view.readIntUserInput(Optional.empty(), new int[] { 1, 2, 3 });
		assertEquals(option, 1);
	}

	@Test
	public void validInputTest() {
		setUserInput("1".getBytes());
		int option = view.readIntUserInput(Optional.of(3), new int[] { 1, 2, 3 });
		assertEquals(option, 1);
	}

}
