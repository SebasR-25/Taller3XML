package fileOperations;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXML {
    private Document document;

    public WriteXML() throws ParserConfigurationException{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        document = db.newDocument(); 
    }
    public void generateDoc(){
        Element  element = document.createElement("Habitación");
        document.appendChild(element);
    }
}
