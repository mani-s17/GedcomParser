package com.mani.parser;

import com.mani.entity.Node;
import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */
public class NodeTest extends TestCase
{
	Node root;
	int level;
	String id , tag, value;

	public void setUp() throws Exception
	{
		super.setUp();
		root = new Node();
		id = "Indi";
		tag = "Name";
		value = "Mani";
	}

	public void testGetterSetter() throws Exception
	{
		root.setLevel(level);
		root.setId(id);
		root.setTag(tag);
		root.setValue(value);

		assertEquals(0, root.getLevel());
		assertEquals(id, root.getId());
		assertEquals(tag, root.getTag());
		assertNotSame("Subramaniam", root.getValue());
	}
}
