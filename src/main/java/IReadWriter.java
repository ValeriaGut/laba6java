import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public interface IReadWriter {
    void Read(String file_name, Dictionary dictionary) throws IOException, XMLStreamException;

    void Write(String file_name, Dictionary dictionary) throws IOException, XMLStreamException;
}
