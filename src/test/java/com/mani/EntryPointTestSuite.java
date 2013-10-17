package com.mani;

import com.mani.entity.NodeTest;
import com.mani.helper.InputFileReaderTest;
import com.mani.helper.NodeMarshallerTest;
import com.mani.helper.OutputFileWriterTest;
import com.mani.helper.TreeBuilderTest;
import com.mani.parser.GedcomParserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created with IntelliJ IDEA.
 * User: Subramaniam S
 * Date: 10/16/13
 */

@RunWith(Suite.class)
@Suite.SuiteClasses(
		{
				NodeTest.class,

				InputFileReaderTest.class,
				NodeMarshallerTest.class,
				OutputFileWriterTest.class,
				TreeBuilderTest.class,

				GedcomParserTest.class
		}
)

public class EntryPointTestSuite
{
}
