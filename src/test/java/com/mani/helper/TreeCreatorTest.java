package com.mani.helper;

import com.mani.parser.GedcomParser;
import com.mani.parser.Node;
import com.mani.parser.Parser;
import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/17/13
 * Time: 11:46 AM
 */
public class TreeCreatorTest extends TestCase
{
	public void testCreate() throws Exception
	{
		Parser parser = new GedcomParser("/sampleData.txt");
		//Node root = TreeCreator.create(parser.doParse());
	}
}
