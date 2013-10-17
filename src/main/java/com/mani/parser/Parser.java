package com.mani.parser;

import java.io.IOException;
import java.util.List;

import com.mani.entity.Node;
import com.mani.exceptions.MarshalException;
import com.mani.exceptions.TreeException;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */
public interface Parser
{
	public void doParse(String inputFilePath) throws MarshalException, TreeException, IOException;
}
