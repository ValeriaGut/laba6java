import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;

public class JarDecorator implements IArchivator {

    @Override
    public void Archive(String archive_name, String file_name) throws IOException {
        JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(archive_name));
        JarEntry jarEntry = new JarEntry(file_name);
        jarOutputStream.putNextEntry(jarEntry);
        FileInputStream fileInputStream = new FileInputStream(file_name);
        byte[] buffer = new byte[fileInputStream.available()];
        fileInputStream.read(buffer);
        jarOutputStream.write(buffer);
        jarOutputStream.closeEntry();
        jarOutputStream.close();
        fileInputStream.close();
    }

    @Override
    public void Unarchive(String archive_name) throws IOException {
        JarInputStream jarInputStream = new JarInputStream(new FileInputStream(archive_name));
        JarEntry jarEntry;
        while ((jarEntry = jarInputStream.getNextJarEntry()) != null) {
            String name = jarEntry.getName();
            FileOutputStream fileOutputStream = new FileOutputStream(name);
            fileOutputStream.write(jarInputStream.readAllBytes());
            fileOutputStream.flush();
            jarInputStream.closeEntry();
            fileOutputStream.close();
        }
    }
}