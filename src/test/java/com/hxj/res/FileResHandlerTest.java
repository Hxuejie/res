package com.hxj.res;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FileResHandlerTest {

	private FileResHandler	frh;

	@Before
	public void setUp() throws Exception {
		frh = FileResHandler.getInstance();
	}

	@Test
	public void testSetRootPath() {
		frh.setRootPath("D:/");
	}

	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInputStream() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateFileString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateFileStringInputStream() {
		fail("Not yet implemented");
	}

}
