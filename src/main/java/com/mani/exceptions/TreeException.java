package com.mani.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/17/13
 */

/**
 * This exception will be thrown while constructing tree if Root / No node is not available
 */
public class TreeException extends Exception
{
	public TreeException()
	{
		super();
	}

	public TreeException(String message)
	{
		super(message);
	}

	public TreeException(Throwable object)
	{
		super(object);
	}

	public TreeException(String message, Throwable object)
	{
		super(message, object);
	}
}
