package mk.iwec.csvreader.infrastructure;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EndPoints {
    public static final String BASE = "/api";
    public static final String USER = BASE + "/users";
    public static final String TRAINING = BASE + "/trainings";
    public static final String CONTACT = BASE + "/contacts";
}
