import java.io.*;
import java.util.zip.*;

public class ZipDecorator implements IArchivator {

    @Override
    public void Archive(String archive_name, String file_name) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(archive_name));
        ZipEntry zipEntry = new ZipEntry(file_name);
        zipOutputStream.putNextEntry(zipEntry);
        FileInputStream fileInputStream = new FileInputStream(file_name);
        byte[] buffer = new byte[fileInputStream.available()];
        fileInputStream.read(buffer);
        zipOutputStream.write(buffer);
        zipOutputStream.closeEntry();
        zipOutputStream.close();
        fileInputStream.close();
    }

    @Override
    public void Unarchive(String archive_name) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(archive_name));
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry())!=null) {
            String name = zipEntry.getName();
            FileOutputStream fileOutputStream = new FileOutputStream(name);
            fileOutputStream.write(zipInputStream.readAllBytes());
            fileOutputStream.flush();
            zipInputStream.closeEntry();
            fileOutputStream.close();
        }
    }
}
