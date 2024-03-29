package io.github.akjo03.lib.swing.util.key;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.event.KeyEvent;

@Getter
@RequiredArgsConstructor
@SuppressWarnings("unused")
public enum SwingKey {
	CANCEL(KeyEvent.VK_CANCEL),
	BACKSPACE(KeyEvent.VK_BACK_SPACE),
	TAB(KeyEvent.VK_TAB),
	ENTER(KeyEvent.VK_ENTER),
	CLEAR(KeyEvent.VK_CLEAR),
	SHIFT(KeyEvent.VK_SHIFT),
	CTRL(KeyEvent.VK_CONTROL),
	ALT(KeyEvent.VK_ALT),
	PAUSE(KeyEvent.VK_PAUSE),
	CAPS_LOCK(KeyEvent.VK_CAPS_LOCK),
	KANA(KeyEvent.VK_KANA),
	FINAL(KeyEvent.VK_FINAL),
	KANJI(KeyEvent.VK_KANJI),
	ESCAPE(KeyEvent.VK_ESCAPE),
	CONVERT(KeyEvent.VK_CONVERT),
	NO_CONVERT(KeyEvent.VK_NONCONVERT),
	ACCEPT(KeyEvent.VK_ACCEPT),
	MODE_CHANGE(KeyEvent.VK_MODECHANGE),
	SPACE(KeyEvent.VK_SPACE),
	PAGE_UP(KeyEvent.VK_PAGE_UP),
	PAGE_DOWN(KeyEvent.VK_PAGE_DOWN),
	END(KeyEvent.VK_END),
	HOME(KeyEvent.VK_HOME),
	LEFT(KeyEvent.VK_LEFT),
	UP(KeyEvent.VK_UP),
	RIGHT(KeyEvent.VK_RIGHT),
	DOWN(KeyEvent.VK_DOWN),
	COMMA(KeyEvent.VK_COMMA),
	MINUS(KeyEvent.VK_MINUS),
	PERIOD(KeyEvent.VK_PERIOD),
	SLASH(KeyEvent.VK_SLASH),
	NUM_0(KeyEvent.VK_0),
	NUM_1(KeyEvent.VK_1),
	NUM_2(KeyEvent.VK_2),
	NUM_3(KeyEvent.VK_3),
	NUM_4(KeyEvent.VK_4),
	NUM_5(KeyEvent.VK_5),
	NUM_6(KeyEvent.VK_6),
	NUM_7(KeyEvent.VK_7),
	NUM_8(KeyEvent.VK_8),
	NUM_9(KeyEvent.VK_9),
	SEMICOLON(KeyEvent.VK_SEMICOLON),
	EQUALS(KeyEvent.VK_EQUALS),
	LETTER_A(KeyEvent.VK_A),
	LETTER_B(KeyEvent.VK_B),
	LETTER_C(KeyEvent.VK_C),
	LETTER_D(KeyEvent.VK_D),
	LETTER_E(KeyEvent.VK_E),
	LETTER_F(KeyEvent.VK_F),
	LETTER_G(KeyEvent.VK_G),
	LETTER_H(KeyEvent.VK_H),
	LETTER_I(KeyEvent.VK_I),
	LETTER_J(KeyEvent.VK_J),
	LETTER_K(KeyEvent.VK_K),
	LETTER_L(KeyEvent.VK_L),
	LETTER_M(KeyEvent.VK_M),
	LETTER_N(KeyEvent.VK_N),
	LETTER_O(KeyEvent.VK_O),
	LETTER_P(KeyEvent.VK_P),
	LETTER_Q(KeyEvent.VK_Q),
	LETTER_R(KeyEvent.VK_R),
	LETTER_S(KeyEvent.VK_S),
	LETTER_T(KeyEvent.VK_T),
	LETTER_U(KeyEvent.VK_U),
	LETTER_V(KeyEvent.VK_V),
	LETTER_W(KeyEvent.VK_W),
	LETTER_X(KeyEvent.VK_X),
	LETTER_Y(KeyEvent.VK_Y),
	LETTER_Z(KeyEvent.VK_Z),
	OPEN_BRACKET(KeyEvent.VK_OPEN_BRACKET),
	BACK_SLASH(KeyEvent.VK_BACK_SLASH),
	CLOSE_BRACKET(KeyEvent.VK_CLOSE_BRACKET),
	NUMPAD_0(KeyEvent.VK_NUMPAD0),
	NUMPAD_1(KeyEvent.VK_NUMPAD1),
	NUMPAD_2(KeyEvent.VK_NUMPAD2),
	NUMPAD_3(KeyEvent.VK_NUMPAD3),
	NUMPAD_4(KeyEvent.VK_NUMPAD4),
	NUMPAD_5(KeyEvent.VK_NUMPAD5),
	NUMPAD_6(KeyEvent.VK_NUMPAD6),
	NUMPAD_7(KeyEvent.VK_NUMPAD7),
	NUMPAD_8(KeyEvent.VK_NUMPAD8),
	NUMPAD_9(KeyEvent.VK_NUMPAD9),
	NUMPAD_MULTIPLY(KeyEvent.VK_MULTIPLY),
	NUMPAD_ADD(KeyEvent.VK_ADD),
	NUMPAD_SEPARATOR(KeyEvent.VK_SEPARATOR),
	NUMPAD_SUBTRACT(KeyEvent.VK_SUBTRACT),
	NUMPAD_DECIMAL(KeyEvent.VK_DECIMAL),
	NUMPAD_DIVIDE(KeyEvent.VK_DIVIDE),
	FUNCTION_1(KeyEvent.VK_F1),
	FUNCTION_2(KeyEvent.VK_F2),
	FUNCTION_3(KeyEvent.VK_F3),
	FUNCTION_4(KeyEvent.VK_F4),
	FUNCTION_5(KeyEvent.VK_F5),
	FUNCTION_6(KeyEvent.VK_F6),
	FUNCTION_7(KeyEvent.VK_F7),
	FUNCTION_8(KeyEvent.VK_F8),
	FUNCTION_9(KeyEvent.VK_F9),
	FUNCTION_10(KeyEvent.VK_F10),
	FUNCTION_11(KeyEvent.VK_F11),
	FUNCTION_12(KeyEvent.VK_F12),
	DELETE(KeyEvent.VK_DELETE),
	DEAD_GRAVE(KeyEvent.VK_DEAD_GRAVE),
	DEAD_ACUTE(KeyEvent.VK_DEAD_ACUTE),
	DEAD_CIRCUMFLEX(KeyEvent.VK_DEAD_CIRCUMFLEX),
	DEAD_TILDE(KeyEvent.VK_DEAD_TILDE),
	DEAD_MACRON(KeyEvent.VK_DEAD_MACRON),
	DEAD_BREVE(KeyEvent.VK_DEAD_BREVE),
	DEAD_ABOVE_DOT(KeyEvent.VK_DEAD_ABOVEDOT),
	DEAD_DIAERESIS(KeyEvent.VK_DEAD_DIAERESIS),
	DEAD_ABOVE_RING(KeyEvent.VK_DEAD_ABOVERING),
	DEAD_DOUBLE_ACUTE(KeyEvent.VK_DEAD_DOUBLEACUTE),
	DEAD_CARON(KeyEvent.VK_DEAD_CARON),
	DEAD_CEDILLA(KeyEvent.VK_DEAD_CEDILLA),
	DEAD_OGONEK(KeyEvent.VK_DEAD_OGONEK),
	DEAD_IOTA(KeyEvent.VK_DEAD_IOTA),
	DEAD_VOICED_SOUND(KeyEvent.VK_DEAD_VOICED_SOUND),
	DEAD_SEMIVOICED_SOUND(KeyEvent.VK_DEAD_SEMIVOICED_SOUND),
	NUM_LOCK(KeyEvent.VK_NUM_LOCK),
	SCROLL_LOCK(KeyEvent.VK_SCROLL_LOCK),
	AMPERSAND(KeyEvent.VK_AMPERSAND),
	ASTERISK(KeyEvent.VK_ASTERISK),
	DOUBLE_QUOTE(KeyEvent.VK_QUOTEDBL),
	LESS(KeyEvent.VK_LESS),
	PRINT_SCREEN(KeyEvent.VK_PRINTSCREEN),
	INSERT(KeyEvent.VK_INSERT),
	HELP(KeyEvent.VK_HELP),
	META(KeyEvent.VK_META),
	GREATER(KeyEvent.VK_GREATER),
	LEFT_BRACE(KeyEvent.VK_BRACELEFT),
	RIGHT_BRACE(KeyEvent.VK_BRACERIGHT),
	BACK_QUOTE(KeyEvent.VK_BACK_QUOTE),
	QUOTE(KeyEvent.VK_QUOTE),
	KEYPAD_UP(KeyEvent.VK_KP_UP),
	KEYPAD_DOWN(KeyEvent.VK_KP_DOWN),
	KEYPAD_LEFT(KeyEvent.VK_KP_LEFT),
	KEYPAD_RIGHT(KeyEvent.VK_KP_RIGHT),
	ALPHANUMERIC(KeyEvent.VK_ALPHANUMERIC),
	KATAKANA(KeyEvent.VK_KATAKANA),
	HIRAGANA(KeyEvent.VK_HIRAGANA),
	FULL_WIDTH(KeyEvent.VK_FULL_WIDTH),
	HALF_WIDTH(KeyEvent.VK_HALF_WIDTH),
	ROMAN_CHARACTERS(KeyEvent.VK_ROMAN_CHARACTERS),
	ALL_CANDIDATES(KeyEvent.VK_ALL_CANDIDATES),
	PREVIOUS_CANDIDATE(KeyEvent.VK_PREVIOUS_CANDIDATE),
	CODE_INPUT(KeyEvent.VK_CODE_INPUT),
	JAPANESE_KATAKANA(KeyEvent.VK_JAPANESE_KATAKANA),
	JAPANESE_HIRAGANA(KeyEvent.VK_JAPANESE_HIRAGANA),
	JAPANESE_ROMAN(KeyEvent.VK_JAPANESE_ROMAN),
	KANA_LOCK(KeyEvent.VK_KANA_LOCK),
	INPUT_METHOD_ON_OFF(KeyEvent.VK_INPUT_METHOD_ON_OFF),
	AT(KeyEvent.VK_AT),
	COLON(KeyEvent.VK_COLON),
	CIRCUMFLEX(KeyEvent.VK_CIRCUMFLEX),
	DOLLAR_SIGN(KeyEvent.VK_DOLLAR),
	EURO_SIGN(KeyEvent.VK_EURO_SIGN),
	EXCLAMATION_MARK(KeyEvent.VK_EXCLAMATION_MARK),
	INVERTED_EXCLAMATION_MARK(KeyEvent.VK_INVERTED_EXCLAMATION_MARK),
	LEFT_PARENTHESIS(KeyEvent.VK_LEFT_PARENTHESIS),
	NUMBER_SIGN(KeyEvent.VK_NUMBER_SIGN),
	PLUS(KeyEvent.VK_PLUS),
	RIGHT_PARENTHESIS(KeyEvent.VK_RIGHT_PARENTHESIS),
	UNDERSCORE(KeyEvent.VK_UNDERSCORE),
	SYSTEM(KeyEvent.VK_WINDOWS),
	CONTEXT_MENU(KeyEvent.VK_CONTEXT_MENU),
	FUNCTION_13(KeyEvent.VK_F13),
	FUNCTION_14(KeyEvent.VK_F14),
	FUNCTION_15(KeyEvent.VK_F15),
	FUNCTION_16(KeyEvent.VK_F16),
	FUNCTION_17(KeyEvent.VK_F17),
	FUNCTION_18(KeyEvent.VK_F18),
	FUNCTION_19(KeyEvent.VK_F19),
	FUNCTION_20(KeyEvent.VK_F20),
	FUNCTION_21(KeyEvent.VK_F21),
	FUNCTION_22(KeyEvent.VK_F22),
	FUNCTION_23(KeyEvent.VK_F23),
	FUNCTION_24(KeyEvent.VK_F24),
	COMPOSE(KeyEvent.VK_COMPOSE),
	BEGIN(KeyEvent.VK_BEGIN),
	ALT_GRAPH(KeyEvent.VK_ALT_GRAPH),
	STOP(KeyEvent.VK_STOP),
	AGAIN(KeyEvent.VK_AGAIN),
	PROPS(KeyEvent.VK_PROPS),
	UNDO(KeyEvent.VK_UNDO),
	COPY(KeyEvent.VK_COPY),
	PASTE(KeyEvent.VK_PASTE),
	FIND(KeyEvent.VK_FIND),
	CUT(KeyEvent.VK_CUT);

	private final int code;
}