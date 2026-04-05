/**
 * Observer in the Observer pattern: notified when the {@link Presentation} changes
 * (slide index or title) so the view can refresh without the model depending on a concrete UI class.
 */
public interface SlideChangeListener {

	/**
	 * Called after the presentation's current slide or title changed.
	 *
	 * @param presentation the presentation that changed
	 * @param currentSlide the slide now shown, or {@code null} if none
	 */
	void slideChanged(Presentation presentation, Slide currentSlide);
}
