package com.rakovets.course.java.core.example.xml;

import com.rakovets.course.java.core.example.xml.service.StAXWriter;

import java.nio.file.Paths;

public class StAXExample2Writing {
    public static void main(String[] args) {
        StAXWriter configFile = new StAXWriter();
        configFile.saveConfig(Paths.get("src", "main", "resources", "example", "xml", "stax-writing.xml").toString());
    }
}
