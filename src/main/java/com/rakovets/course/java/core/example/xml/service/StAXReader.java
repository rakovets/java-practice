package com.rakovets.course.java.core.example.xml.service;

import com.rakovets.course.java.core.example.xml.model.ItemField;
import com.rakovets.course.java.core.example.xml.model.Item;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class StAXReader {
    private static final String ITEM_ATTRIBUTE_DATE = "date";

    public List<Item> readConfig(String configFilepath) {
        List<Item> items = new ArrayList<>();
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            XMLEventReader eventReader = inputFactory.createXMLEventReader(new FileInputStream(configFilepath));
            // read the XML document
            Item item = new Item();

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have an item element, we create a new item
                    String elementName = startElement.getName().getLocalPart();
                    switch (elementName) {
                        case ItemField.ITEM:
                            item = new Item();
                            String attributeValueByName = getAttributeValueByName(startElement, ITEM_ATTRIBUTE_DATE);
                            if (attributeValueByName != null) {
                                item.setDate(attributeValueByName);
                            }
                            break;
                        case ItemField.MODE:
                            event = eventReader.nextEvent();
                            item.setMode(event.asCharacters().getData());
                            break;
                        case ItemField.UNIT:
                            event = eventReader.nextEvent();
                            item.setUnit(event.asCharacters().getData());
                            break;
                        case ItemField.CURRENT:
                            event = eventReader.nextEvent();
                            item.setCurrent(event.asCharacters().getData());
                            break;
                        case ItemField.INTERACTIVE:
                            event = eventReader.nextEvent();
                            item.setInteractive(event.asCharacters().getData());
                            break;
                    }
                }
                // If we reach the end of an item element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(ItemField.ITEM)) {
                        items.add(item);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return items;
    }

    private String getAttributeValueByName(StartElement startElement, String attributeName) {
        // We read the attributes from this tag and add the date
        // attribute to our object
        Iterator<Attribute> attributes = startElement.getAttributes();
        while (attributes.hasNext()) {
            Attribute attribute = attributes.next();
            if (attribute.getName().toString().equals(attributeName)) {
                return attribute.getValue();
            }
        }
        return null;
    }
}
