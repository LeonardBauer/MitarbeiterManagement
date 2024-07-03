//  Author:     leonard
//  Project:    MitarbeiterManagement
//  Date:       14:44 02.07.24 


import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class YamlReaderWriter {
    public static void main(String[] args) {
        String filePath = "config.yaml";
        File configFile = new File(filePath);

        if (!configFile.exists()) {
            System.out.println("config.yaml not found. Creating default config.yaml.");
            createDefaultConfig(filePath);
        }

        // Load the YAML file
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            Yaml yaml = new Yaml();

            //  Parse the YAML file into a Map
            Map<String, Object> config = yaml.load(inputStream);

            // Access the data
            Map<String, Object> databaseConfig = (Map<String, Object>) config.get("database");
            String host = (String) databaseConfig.get("host");
            int port = (int) databaseConfig.get("port");
            String name = (String) databaseConfig.get("name");
            String user = (String) databaseConfig.get("user");
            String password = (String) databaseConfig.get("password");

            // Print the data
            System.out.println("Database Host: " + host);
            System.out.println("Database Port: " + port);
            System.out.println("Database Name: " + name);
            System.out.println("Database User: " + user);
            System.out.println("Database Password: " + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDefaultConfig(String filePath) {
        // Create a map representing the default data
        Map<String, Object> config = new HashMap<>();
        Map<String, Object> databaseConfig = new HashMap<>();

        databaseConfig.put("host", "localhost");
        databaseConfig.put("port", 3306);
        databaseConfig.put("name", "test_db");
        databaseConfig.put("user", "root");
        databaseConfig.put("password", "root");

        config.put("database", databaseConfig);

        // Configure DumperOptions
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        // Create a Yaml instance
        Yaml yaml = new Yaml(options);

        // Write the default data to a YAML file
        try (FileWriter writer = new FileWriter(filePath)) {
            yaml.dump(config, writer);
            System.out.println("Default config.yaml created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
