import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class AccessorSelectorTest {

	@Test
	void forFilenameReturnsAccessorForXmlPath() {
		Accessor a = AccessorSelector.forFilename("presentation.xml");
		assertNotNull(a);
	}

	@Test
	void forFilenameReturnsAccessorForNullAndEmpty() {
		assertNotNull(AccessorSelector.forFilename(null));
		assertNotNull(AccessorSelector.forFilename(""));
	}

	@Test
	void forFilenameReturnsSameXmlStrategyInstance() {
		Accessor first = AccessorSelector.forFilename("a.xml");
		Accessor second = AccessorSelector.forFilename("b.xml");
		assertSame(first, second);
	}
}
