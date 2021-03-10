package com.rakovets.course.java.core.example.xml;

import com.rakovets.course.java.core.example.xml.service.StAXReader;
import com.rakovets.course.java.core.example.xml.model.Item;

import java.nio.file.Paths;
import java.util.List;

public class StAXExample1Reading {
    public static void main(String[] args) {
        StAXReader read = new StAXReader();
        List<Item> readConfig = read.readConfig(Paths.get("src", "main", "resources", "example", "xml", "stax-reading.xml").toString());
        for (Item item : readConfig) {
            System.out.println(item);
        }
    }
}
