/**
 * Subject in the Observer pattern: {@link Presentation} implementations register listeners
 * and notify them when slide or title state changes.
 */
public interface SlideChangeSubject {

	/**
	 * Registers a listener to receive slide/title updates.
	 */
	void addSlideChangeListener(SlideChangeListener listener);

	/**
	 * Unregisters a listener (e.g. on dispose).
	 */
	void removeSlideChangeListener(SlideChangeListener listener);
}
