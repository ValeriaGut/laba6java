import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Scanner;

public class CryptDecorator implements IReadWriter {

    private SecretKeySpec key = new SecretKeySpec("Bar12345Bar12345".getBytes(), "AES");


    @Override
    public void Read(String file_name, Dictionary dictionary) throws IOException, XMLStreamException {
        try {
            FileReader fileReader = new FileReader(file_name);
            Scanner scanner = new Scanner(fileReader);
            String to_decrypt = "";
            while(scanner.hasNextLine()){
                to_decrypt += scanner.nextLine();
            }
            

        Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] data = cipher.doFinal(to_decrypt.getBytes());
            String to_write = "";
            for (byte b: data) {
                to_write += b;
            }
            FileWriter fileWriter = new FileWriter(file_name);
            fileWriter.write(to_write);
            fileWriter.close();
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Write(String file_name, Dictionary dictionary) throws IOException, XMLStreamException {
        try {

            FileReader fileReader = new FileReader(file_name);
            Scanner scanner = new Scanner(fileReader);
            String to_encrypt = "";
            while (scanner.hasNext()){
                to_encrypt += scanner.nextLine();
            }
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] data = cipher.doFinal(to_encrypt.getBytes());
            String to_write = "";
            for (byte b: data) {
                to_write += b + " ";
            }
            FileWriter fileWriter = new FileWriter(file_name);
            fileWriter.write(to_write);
            fileWriter.close();
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
