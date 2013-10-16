package com.mani;

import com.mani.parser.GedcomParser;
import com.mani.parser.Parser;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */
public class EntryPoint
{
	public static void main(String[] args)
	{
		Parser parser = new GedcomParser("/CompleteData.txt");
		parser.doParse();
	}
}
