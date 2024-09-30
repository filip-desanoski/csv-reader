package mk.iwec.csvreader.infrastructure.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public abstract class GenericCSVReader<T> {

    private final Class<T> type;

    protected GenericCSVReader(Class<T> type) {
        this.type = type;
    }

    public List<T> readCsv(String filePath) throws IOException {
        List<T> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord csvRecord : csvParser) {
                T record = mapCsvRecordToEntity(csvRecord);
                records.add(record);
            }
        }

        return records;
    }

    protected abstract T mapCsvRecordToEntity(CSVRecord csvRecord);
}

