package com.rakovets.course.java.core.example.xml.service;


import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.rakovets.course.java.core.example.xml.model.ItemField.*;

public class StAXWriter {
    private final static String EMPTY_PREFIX = "";
    private final static String EMPTY_NAMESPACE_URI = "";

    private final XMLEventFactory eventFactory = XMLEventFactory.newInstance();
    private final XMLEvent lfEvent = eventFactory.createDTD("\n");
    private final XMLEvent tab = eventFactory.createDTD("\t");

    public void saveConfig(String configFilepath) {
        // create an XMLOutputFactory
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        try (OutputStream outputStream = new FileOutputStream(configFilepath)) {
            // create XMLEventWriter
            XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(outputStream);
            // create and write Start Tag
            eventWriter.add(eventFactory.createStartDocument());
            eventWriter.add(lfEvent);
            // create config open tag
            eventWriter.add(eventFactory.createStartElement(EMPTY_PREFIX, EMPTY_NAMESPACE_URI, CONFIG));
            eventWriter.add(lfEvent);
            // Write the different nodes
            createNode(eventWriter, MODE, "1");
            createNode(eventWriter, UNIT, "901");
            createNode(eventWriter, CURRENT, "0");
            createNode(eventWriter, INTERACTIVE, "0");
            // create config close tag
            eventWriter.add(eventFactory.createEndElement(EMPTY_PREFIX, EMPTY_NAMESPACE_URI, CONFIG));
            eventWriter.add(lfEvent);
            // create and write End Tag
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void createNode(XMLEventWriter eventWriter, String name, String value) throws XMLStreamException {
        // create Start node
        StartElement sElement = eventFactory.createStartElement(EMPTY_PREFIX, EMPTY_NAMESPACE_URI, name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement(EMPTY_PREFIX, EMPTY_NAMESPACE_URI, name);
        eventWriter.add(eElement);
        eventWriter.add(lfEvent);
    }
}
