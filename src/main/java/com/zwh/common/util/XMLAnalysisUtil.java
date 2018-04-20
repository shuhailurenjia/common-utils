package com.zwh.common.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

/**
 * Created by a on 14-11-25.
 */
public class XMLAnalysisUtil {

    public static String xmlAnXinJieResultAnalysis(String xml) {
        String result = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xml)));
            Element root = doc.getDocumentElement();
            result = root.getElementsByTagName("reqCode").item(0).getFirstChild().getNodeValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String xmlZhuWangResultAnalysis(String xml) {
        String result = null;
        try {
//            DocumentBuilderFactory factory = DocumentBuilderFactory .newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document doc = builder.parse(new InputSource(new StringReader(xml)));
//            Element root = doc.getDocumentElement();
//            result = root.getElementsByTagName("returnstatus").item(0).getFirstChild().getNodeValue();
            if (xml.contains("<returnstatus>Success</returnstatus>")) {
                return "00";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String RuanWeiResultAnalysis(String xml) {
        String result = null;
        try {
//            DocumentBuilderFactory factory = DocumentBuilderFactory .newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document doc = builder.parse(new InputSource(new StringReader(xml)));
//            Element root = doc.getDocumentElement();
//            result = root.getElementsByTagName("returnstatus").item(0).getFirstChild().getNodeValue();

            if (xml.contains("<returnstatus>Success</returnstatus>")) {
                return "00";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
