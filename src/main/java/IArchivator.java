import java.io.FileNotFoundException;
import java.io.IOException;

public interface IArchivator {
    void Archive(String archive_name, String file_name) throws IOException;
    void Unarchive(String archive_name) throws IOException;
}
