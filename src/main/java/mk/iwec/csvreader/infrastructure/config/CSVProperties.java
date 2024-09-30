package mk.iwec.csvreader.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CSVProperties {

    @Value("${csv.file.base.path}")
    private String basePath;

    public String getBasePath() {
        return basePath;
    }
}
