package com.rakovets.course.java.core.example.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class XPathExample {
    public static void main(String[] args) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);

        DocumentBuilder builder;
        Document document;
        try {
            builder = builderFactory.newDocumentBuilder();
            document = builder.parse(Paths.get("src", "main", "resources", "example", "xml", "xpath.xml").toString());

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();

            String devName = getDeveloperNameById(document, xpath, 1);
            System.out.printf("Имя разработчика с id = 1: %s\n", devName);

            List<String> names = getDevelopersWithAge(document, xpath, 25);
            System.out.printf("Разработчики, младше 25 лет: %s\n", names.toString());

            List<String> middleDevelopers = getDevelopersByPosition(document, xpath, "Junior");
            System.out.printf("Работают на позиции Junior Developer: %s\n", middleDevelopers.toString());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getDevelopersByPosition(Document doc, XPath xpath, String position) {
        List<String> result = new ArrayList<>();
        try {
            XPathExpression xPathExpression = xpath.compile("/developers/developer[position='" + position + "']/name/text()");
            NodeList nodes = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++) {
                result.add(nodes.item(i).getNodeValue());
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return result;
    }


    private static List<String> getDevelopersWithAge(Document doc, XPath xpath, int age) {
        List<String> result = new ArrayList<>();
        try {
            XPathExpression xPathExpression = xpath.compile("/developers/developer[age<" + age + "]/name/text()");
            NodeList nodeList = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                result.add(nodeList.item(i).getNodeValue());
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return result;
    }


    private static String getDeveloperNameById(Document doc, XPath xpath, int id) {
        String result = null;
        try {
            XPathExpression xPathExpression = xpath.compile("/developers/developer[@id='" + id + "']/name/text()");
            result = (String) xPathExpression.evaluate(doc, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return result;
    }
}
