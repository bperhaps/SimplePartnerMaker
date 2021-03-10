package view;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputView {
    public static List<List<String>> readToList(String path) {
        try (CSVReader csvReader = new CSVReader(new FileReader(path))) {
            return readFile(csvReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new AssertionError();
    }

    private static List<List<String>> readFile(CSVReader csvReader) throws IOException {
        List<List<String>> records = new ArrayList<>();
        csvReader.readNext();

        String[] values;
        while ((values = csvReader.readNext()) != null) {
            records.add(Arrays.asList(values));
        }

        return records;
    }
}
