package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Created by vinodkumar on 11/9/15.
 */
public class ReadFile {

        /**
         * @param file
         * @return
         * @throws IOException
         */
        public String readFile (String file) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");
            line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
                line = reader.readLine();
            }
            reader.close();
            return stringBuilder.toString();
        }

}

