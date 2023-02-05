package utilities;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileToString {

    public String FileConvert (String jsonFileName) throws IOException {

        String string = String.format("src/test/java/payload/%s", jsonFileName);
        File file = new File(string);
        String json = FileUtils.readFileToString(file, "UTF-8");
        return json;
    }
}
