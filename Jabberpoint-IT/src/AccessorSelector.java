/**
 * Selects the appropriate {@link Accessor} (Strategy) for a file path.
 * Callers depend on {@code Accessor}, not concrete classes such as {@link XMLAccessor}.
 */
public final class AccessorSelector {

	private static final Accessor XML_STRATEGY = new XMLAccessor();

	private AccessorSelector() {
	}

	/**
	 * @param filename path or name; reserved for extension-based choice of format
	 * @return persistence strategy; only XML is implemented today
	 */
	public static Accessor forFilename(String filename) {
		// When you add e.g. JSON, branch on filename.toLowerCase() extension here.
		return XML_STRATEGY;
	}
}
