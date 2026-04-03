import java.awt.Color;

/**
 * Singleton access point for the application's fixed set of slide styles.
 *
 * The original JabberPoint code used static initialization via {@link Style#createStyles()}.
 * This refactor keeps the styles global and shared, while making the "one style table"
 * explicit via the Singleton pattern.
 */
public final class StyleManager {
	private static final StyleManager INSTANCE = new StyleManager();

	private final Style[] styles;

	private StyleManager() {
		styles = new Style[5];
		// The styles are fixed.
		styles[0] = new Style(0, Color.red, 48, 20); // item-level 0
		styles[1] = new Style(20, Color.blue, 40, 10); // item-level 1
		styles[2] = new Style(50, Color.black, 36, 10); // item-level 2
		styles[3] = new Style(70, Color.black, 30, 10); // item-level 3
		styles[4] = new Style(90, Color.black, 24, 10); // item-level 4
	}

	public static StyleManager getInstance() {
		return INSTANCE;
	}

	public Style getStyle(int level) {
		if (level >= styles.length) {
			level = styles.length - 1;
		}
		return styles[level];
	}
}
