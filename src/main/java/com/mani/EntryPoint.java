package com.mani;

import java.io.IOException;

import com.mani.exceptions.MarshalException;
import com.mani.exceptions.TreeException;
import com.mani.parser.GedcomParser;
import com.mani.parser.Parser;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */
public class EntryPoint
{
	public static void main(String[] args) throws TreeException, MarshalException, IOException
	{
		Parser parser = new GedcomParser();
		parser.doParse(EntryPoint.class.getResource("/sampleData.txt").getPath());
		parser.doParse(EntryPoint.class.getResource("/CompleteData.txt").getPath());
	}
}
