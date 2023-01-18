import java.io.*;
import java.util.Map;

import javax.xml.stream.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XMLDecorator implements IReadWriter{

    @Override
    public void Read(String file_name, Dictionary dictionary) throws IOException, XMLStreamException {
        boolean word = false;
        boolean reiterations = false;
        boolean wflag = false;
        boolean rflag = false;
        String key = "";
        int value = 0;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader(file_name));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        if (qName.equalsIgnoreCase("dictionary")) {
                        } else if (qName.equalsIgnoreCase("word")) {
                            word = true;
                        }
                        else if (qName.equalsIgnoreCase("reiterations")) {
                            reiterations = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (word) {
                            key = characters.getData();
                            word = false;
                            wflag = true;
                        }
                        if (reiterations) {
                            value = Integer.parseInt(characters.getData());
                            reiterations = false;
                            rflag = true;
                        }
                        if (wflag && rflag){
                            dictionary.InsertData(key, value);
                            wflag = false;
                            rflag = false;
                        }

                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if (endElement.getName().getLocalPart().equalsIgnoreCase("word")) {
                        }
                        break;

                }
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(XMLStreamException e){
            e.printStackTrace();
        }
    }

    @Override
    public void Write(String file_name, Dictionary dictionary) throws IOException, XMLStreamException {
        try {
            StringWriter stringWriter = new StringWriter();

            XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);

            xMLStreamWriter.writeStartDocument();
            xMLStreamWriter.writeStartElement("dictionary");
            for(Map.Entry<String, Integer> entry : dictionary.getDictionary().entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                xMLStreamWriter.writeStartElement("word");
                xMLStreamWriter.writeCharacters(key + "\n");
                xMLStreamWriter.writeStartElement("reiterations");
                xMLStreamWriter.writeCharacters(String.valueOf(value));
                xMLStreamWriter.writeEndElement();
                xMLStreamWriter.writeEndElement();
            }

            xMLStreamWriter.writeEndDocument();

            xMLStreamWriter.flush();
            xMLStreamWriter.close();

            String xmlString = stringWriter.getBuffer().toString();

            stringWriter.close();

            FileWriter fileWriter = new FileWriter(file_name);
            fileWriter.write(xmlString);
            fileWriter.close();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
