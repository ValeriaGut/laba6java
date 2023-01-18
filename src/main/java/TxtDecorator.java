import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TxtDecorator implements IReadWriter{

    @Override
    public void Read(String file_name, Dictionary dictionary) throws IOException {
        FileReader fileReader = new FileReader(file_name);
        Scanner scanner = new Scanner(fileReader);
        while (scanner.hasNextLine()) {
            dictionary.InsertWord(scanner.next());
        }
    }

    @Override
    public void Write(String file_name, Dictionary dictionary) throws IOException {
        FileWriter file_writer = new FileWriter(file_name);
        file_writer.write(dictionary.toString());
        file_writer.close();
    }
}
