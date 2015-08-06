package org.erik.code.main;

import org.erik.code.genertator.GenerationOrganizer;
import org.erik.code.parser.XmlParser;

/**
 * Created by wandong.cwd on 2014/10/31.
 */
public class Geneter {

    public static void main(String[] args) {
        GenerationOrganizer generationOrganizer = new GenerationOrganizer("config.xml");
        generationOrganizer.codeGenerate();
    }
}
