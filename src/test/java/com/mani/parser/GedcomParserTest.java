package com.mani.parser;

import java.util.List;

import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 * Time: 10:48 PM
 */
public class GedcomParserTest extends TestCase
{
	Parser parser;

	public void setUp() throws Exception
	{
		super.setUp();
		parser = new GedcomParser("/CompleteData.txt");
	}

	public void testDoParse() throws Exception
	{
		List<Node> nodes = parser.doParse();
		assertEquals(5, nodes.size());

		for(Node node : nodes)
		{
			if(node.getLevel() == 0)
			{
				assertNotSame(null, node.getId());
				assertNotSame(null, node.getTag());
				assertEquals(null, node.getValue());
			}
			else
			{
				assertEquals(null, node.getId());
				assertNotSame(null, node.getTag());
				assertNotSame(null, node.getValue());
			}
		}
	}

	public void testMarshaller() throws Exception
	{
		assertEquals(true, parser.marshaller(parser.doParse()));
	}
}
