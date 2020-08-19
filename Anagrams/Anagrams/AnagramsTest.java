package Anagrams;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AnagramsTest {
	
	Anagrams A1 = new Anagrams();

	@Test
	void testaddWord() {
		A1.addWord("fire");
		assertTrue(A1.anagramTable.containsKey(A1.myHashCode("fire")));
	}
	
	@Test
	void testmyHashCode() {
		assertEquals(A1.myHashCode("cool"), 408665);
	}
	
}
