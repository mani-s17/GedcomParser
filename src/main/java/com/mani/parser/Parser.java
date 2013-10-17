package com.mani.parser;

import java.io.IOException;

import com.mani.exceptions.MarshalException;
import com.mani.exceptions.TreeException;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */

/**
 * Interface to parse data file to xml file
* */
public interface Parser
{
	public void doParse(String inputFilePath) throws MarshalException, TreeException, IOException;
}
