import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class StyleManagerTest {

	@Test
	void getInstanceReturnsSingleton() {
		assertSame(StyleManager.getInstance(), StyleManager.getInstance());
	}

	@Test
	void getStyleReturnsStyleForEachLevel() {
		StyleManager sm = StyleManager.getInstance();
		for (int level = 0; level < 5; level++) {
			assertNotNull(sm.getStyle(level));
		}
	}

	@Test
	void getStyleClampsHighLevelToLastDefinedStyle() {
		StyleManager sm = StyleManager.getInstance();
		Style top = sm.getStyle(4);
		assertSame(top, sm.getStyle(99));
	}
}
