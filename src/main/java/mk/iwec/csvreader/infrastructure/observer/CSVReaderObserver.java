package mk.iwec.csvreader.infrastructure.observer;

import java.util.List;

public interface CSVReaderObserver {
    void update(List<?> records);
}
