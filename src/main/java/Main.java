import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException, XMLStreamException {
        Dictionary dictionary = new Dictionary();

        TxtDecorator txtDecorator = new TxtDecorator();
        txtDecorator.Read("input.txt", dictionary);
        txtDecorator.Write("output.txt", dictionary);

      //  XMLDecorator xmlDecorator = new XMLDecorator();
       // xmlDecorator.Read("input.xml", dictionary);
       // xmlDecorator.Write("output.xml", dictionary);

       // JsonDecorator jsonDecorator = new JsonDecorator();
      // jsonDecorator.Read("input.json", dictionary);
       // jsonDecorator.Write("output.json", dictionary);

        RarDecorator rarDecorator = new RarDecorator();
        rarDecorator.Archive("inputs.rar", "input.txt");

        ZipDecorator zipDecorator = new ZipDecorator();
        zipDecorator.Archive("inputs.zip", "input.xml");

        JarDecorator jarDecorator = new JarDecorator();
        jarDecorator.Archive("inputs.jar", "input.xml");

        CryptDecorator cryptDecorator= new CryptDecorator();
        cryptDecorator.Write("crypt.txt", dictionary);
        cryptDecorator.Read("crypt.txt", dictionary);


       /*
        CryptDecorator cryptDecorator = new CryptDecorator();
        ZipDecorator zipDecorator = new ZipDecorator();
        cryptDecorator.Write("output.txt", dictionary);*/

    }


}
