package ARMS_APITEST_RESTASSURED.config;

import com.github.javafaker.Faker;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TestDataConstants {
    private static final Faker faker = new Faker();


    public static final long RESPONSE_TIME_THRESHOLD = 3500L;
    public static final String DefaultAccessLevelName = "AccessLevel " + System.currentTimeMillis();
    public static final String DefaultName = "AccessLevel";
    public static final String GENERAL_DESCRIPTION = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum auctor purus inagna maximus";

    // Competency names
    public static final String EXISTING_COMPETENCY_1 = "Expertise";
    public static final String EXISTING_COMPETENCY_2 = "Leadership";
    public static final String NON_EXISTING_COMPETENCY = "Communication";

    // Competency statuses
    public static final String ACTIVE_STATUS = "ACTIVE";
    public static final String INACTIVE_STATUS = "INACTIVE";
    public static final String INVALID_STATUS = "IDLE";

    // Competency Criteria
    public static final String FIX_BASED = "FIX_BASED";
    public static final String SCORE_BASED = "SCORE_BASED";
    public static final String NO_JUSTIFICATION = "NO_JUSTIFICATION";
    public static final String INVALID_JUSTIFICATION = "BASED";

//    public static String getTimestamp() {
////        return "Automate-Competency-API-" + Instant.now().toEpochMilli() + "-" + new Random().nextInt(1000);
//        return "Automate-Competency-API-" + faker.bothify("?????-#####");
//    }
    // Competency Details
    public static final String COMPETENCY_NAME = "COMPETENCY";


}
