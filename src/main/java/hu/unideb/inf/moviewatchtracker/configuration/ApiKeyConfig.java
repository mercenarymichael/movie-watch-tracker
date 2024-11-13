package hu.unideb.inf.moviewatchtracker.configuration;
import org.springframework.context.annotation.Configuration;
import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class ApiKeyConfig {

    private static final Dotenv dotenv = Dotenv.configure().load();

    public static String getTmdbApiKey() {
        return dotenv.get("TMDB_API_KEY");
    }
}

