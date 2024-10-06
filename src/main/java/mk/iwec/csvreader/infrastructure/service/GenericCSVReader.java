package mk.iwec.csvreader.infrastructure.service;

import mk.iwec.csvreader.infrastructure.observer.CSVReaderObserver;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public abstract class GenericCSVReader<T> {

    private final Class<T> type;
    private final List<CSVReaderObserver> observers = new ArrayList<>();

    protected GenericCSVReader(Class<T> type) {
        this.type = type;
    }

    public void addObserver(CSVReaderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(CSVReaderObserver observer) {
        observers.remove(observer);
    }

    public List<T> readCsv(String filePath, List<String> expectedHeaders) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            Map<String, Integer> headerMap = csvParser.getHeaderMap();
            if (!headerMap.keySet().containsAll(expectedHeaders)) {
                throw new IllegalArgumentException("CSV file does not contain the expected headers: " + expectedHeaders);
            }

            List<T> records = csvParser.getRecords().stream()
                    .map(this::mapCsvRecordToEntity)
                    .collect(Collectors.toList());

            notifyObservers(records);

            return records;
        }
    }

    private void notifyObservers(List<T> records) {
        observers.forEach(observer -> observer.update(records));
    }

    protected abstract T mapCsvRecordToEntity(CSVRecord csvRecord);
}

