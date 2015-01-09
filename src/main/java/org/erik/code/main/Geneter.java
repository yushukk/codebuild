package org.erik.code.main;

import org.erik.code.genertator.GenerationOrganizer;
import org.erik.code.parser.XmlParser;

/**
 * Created by wandong.cwd on 2014/10/31.
 */
public class Geneter {

    public static void main(String[] args) {
        XmlParser xmlParser = new XmlParser();
        xmlParser.parseConfigXml("config.xml");
        GenerationOrganizer generationOrganizer = new GenerationOrganizer();
        generationOrganizer.codeGenerate();
    }
}
