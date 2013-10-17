package com.mani.parser;

import com.mani.exceptions.TreeException;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */

/**
 * Interface to parse data file to xml file
 */
public interface Parser
{
	public String doParse(String inputFilePath) throws TreeException;
}
