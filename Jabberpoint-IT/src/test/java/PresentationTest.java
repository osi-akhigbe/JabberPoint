import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PresentationTest {

	private Presentation presentation;

	@BeforeEach
	void setUp() {
		presentation = new Presentation();
	}

	@Test
	void appendIncreasesSize() {
		assertEquals(0, presentation.getSize());
		presentation.append(new Slide());
		assertEquals(1, presentation.getSize());
	}

	@Test
	void getSlideReturnsNullForInvalidIndex() {
		presentation.append(new Slide());
		assertNull(presentation.getSlide(-1));
		assertNull(presentation.getSlide(1));
	}

	@Test
	void nextSlideDoesNotPassEnd() {
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.setSlideNumber(0);
		presentation.nextSlide();
		assertEquals(1, presentation.getSlideNumber());
		presentation.nextSlide();
		assertEquals(1, presentation.getSlideNumber());
	}

	@Test
	void prevSlideDoesNotGoBelowZero() {
		presentation.append(new Slide());
		presentation.setSlideNumber(0);
		presentation.prevSlide();
		assertEquals(0, presentation.getSlideNumber());
	}

	@Test
	void setTitleNotifiesListeners() {
		AtomicInteger count = new AtomicInteger();
		presentation.addSlideChangeListener((p, slide) -> count.incrementAndGet());
		presentation.setTitle("T");
		assertEquals(1, count.get());
	}

	@Test
	void duplicateListenerRegisteredOnce() {
		AtomicInteger count = new AtomicInteger();
		SlideChangeListener l = (p, slide) -> count.incrementAndGet();
		presentation.addSlideChangeListener(l);
		presentation.addSlideChangeListener(l);
		presentation.setTitle("X");
		assertEquals(1, count.get());
	}
}
