package com.dream.generatefile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/5/25
 */
public class GenerateColorsXml {

    public static void main(String[] args) {

        try {
            // 创建 XML 文档
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

            //color
            DocumentBuilder colorBuilder = docFactory.newDocumentBuilder();
            Document colorDoc = colorBuilder.newDocument();

            //radius
            DocumentBuilder radiusBuilder = docFactory.newDocumentBuilder();
            Document radiusDoc = radiusBuilder.newDocument();

            // color 创建根节点
            Element colorResourcesElement = colorDoc.createElement("resources");
            colorDoc.appendChild(colorResourcesElement);

            // radius 创建根节点
            Element radiusResourcesElement = radiusDoc.createElement("resources");
            radiusDoc.appendChild(radiusResourcesElement);

            // 添加 Color 项
            addColorItem(colorDoc, colorResourcesElement, "purple_200", "#FFBB86FC");
            addColorItem(colorDoc, colorResourcesElement, "purple_500", "#FF6200EE");
            addColorItem(colorDoc, colorResourcesElement, "purple_700", "#FF3700B3");
            addColorItem(colorDoc, colorResourcesElement, "teal_200", "#FF03DAC5");
            addColorItem(colorDoc, colorResourcesElement, "teal_700", "#FF018786");
            addColorItem(colorDoc, colorResourcesElement, "black", "#FF000000");
            addColorItem(colorDoc, colorResourcesElement, "white", "#FFFFFFFF");

            // 添加 Radius 项
            addDimenItem(radiusDoc, radiusResourcesElement, "radius_8", "8dp");
            addDimenItem(radiusDoc, radiusResourcesElement, "radius_9", "9dp");
            addDimenItem(radiusDoc, radiusResourcesElement, "radius_10", "10dp");
            addDimenItem(radiusDoc, radiusResourcesElement, "radius_11", "11dp");
            addDimenItem(radiusDoc, radiusResourcesElement, "radius_12", "12dp");
            addDimenItem(radiusDoc, radiusResourcesElement, "radius_13", "13dp");
            addDimenItem(radiusDoc, radiusResourcesElement, "radius_14", "14dp");



            // 将 XML 写入文件
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");


            DOMSource colorSource = new DOMSource(colorDoc);
            StreamResult colorResult = new StreamResult(new File("colors1.xml"));
            transformer.transform(colorSource, colorResult);

            DOMSource radiusSource = new DOMSource(radiusDoc);
            StreamResult radiusResult = new StreamResult(new File("radius1.xml"));
            transformer.transform(radiusSource, radiusResult);

            System.out.println("XML 文件生成成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addColorItem(Document doc, Element parentElement, String name, String value) {
        Element colorElement = doc.createElement("color");
        colorElement.setAttribute("name", name);
        colorElement.setTextContent(value);
        parentElement.appendChild(colorElement);
    }

    private static void addDimenItem(Document doc, Element parentElement, String name, String value) {
        Element radiusElement = doc.createElement("dimen");
        radiusElement.setAttribute("name", name);
        radiusElement.setTextContent(value);
        parentElement.appendChild(radiusElement);
    }
}

