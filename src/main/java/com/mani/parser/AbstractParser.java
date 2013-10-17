package com.mani.parser;

import java.util.List;

import com.mani.entity.Node;
import com.mani.exceptions.TreeException;
import com.mani.helper.Constants;
import com.mani.helper.InputFileReader;
import com.mani.helper.OutputFileWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */

/**
 * Contains common features for different types of parsers like GEDCOM, ASCII, BINARY, etc.
 * Also provides hook for implementing its own behaviour
 */

// TODO: try creating different parsers for different data format (other than GEDCOM)
public abstract class AbstractParser implements Parser
{
	InputFileReader fileReader = new InputFileReader();
	OutputFileWriter fileWriter = new OutputFileWriter();

	@Override
	public String doParse(String inputFilePath) throws TreeException
	{
		List<String> rawDataList = fileReader.readFile(inputFilePath);
		List<Node> nodeList = convertRawDataToNode(rawDataList);
		String outputFilePath = inputFilePath.split(Constants.FILE_EXTENSION_SEPARATOR)[0] + ".xml";
		String xmlContent = xmlMarshaller(nodeList);
		fileWriter.writeFile(outputFilePath, xmlContent);
		return outputFilePath;
	}

	public abstract List<Node> convertRawDataToNode(List<String> lines);

	public abstract String xmlMarshaller(List<Node> nodes) throws TreeException;
}
