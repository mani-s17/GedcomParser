package com.mani.parser;

import java.util.List;

import com.mani.exceptions.MarshalException;
import com.mani.exceptions.TreeException;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */
public interface Parser
{
	public List<Node> doParse();

	public boolean marshaller(List<Node> nodes) throws TreeException, MarshalException;
}
