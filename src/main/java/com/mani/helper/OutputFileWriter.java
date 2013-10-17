package com.mani.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/17/13
 * Time: 4:33 PM
 */
public class OutputFileWriter
{
	public void writeFile(String filePath, String fileContent)
	{
		File outputFile = new File(filePath);
		BufferedWriter bw = null;
		try
		{
			if (!outputFile.exists())
				outputFile.createNewFile();
			bw = new BufferedWriter(new FileWriter(outputFile.getAbsoluteFile()));
			bw.write(fileContent);
		}
		catch (IOException e)
		{
			System.err.println("Unable to write file " + outputFile.getAbsoluteFile());
			e.printStackTrace();
		}
		catch (NullPointerException e)
		{
			System.err.println("Unable to find file, check file path");
			e.printStackTrace();
		}
		finally
		{
			if (bw != null)
			{
				try
				{
					bw.close();
				}
				catch (IOException e)
				{
					System.err.println("Unable to close file " + outputFile.getAbsoluteFile());
					e.printStackTrace();
				}
			}
		}
	}
}
