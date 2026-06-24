package exercise2;

//necessary imports for file handling and collection management
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

//declaration of the CityDatabase class
public class CityDatabase {
    //maps to maintain a list of cities by country and capitals by country for efficient retrieval
    private Map<String, List<City>> citiesByCountry = new HashMap<>();
    private Map<String, City> capitalsByCountry = new HashMap<>();

    //constructor of CityDatabase that initiates the reading of data from a file
    public CityDatabase(String filename) {
        readData(filename);
    }

    //private method to read data from the file and parse it into City objects
    private void readData(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            //skip the header line of the CSV
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                // parse each line to create City objects
                parseLine(line);
            }
        } catch (IOException e) {
            System.err.println("Failed to read the city database file: " + e.getMessage());
        }
    }

    //parses a line from the CSV and adds the city to the appropriate maps
    private void parseLine(String line) {
        String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        if (parts.length < 4) {
            System.err.println("Skipping malformed line: " + line);
            return;
        }

        try {
            //parse and clean data fields from the CSV line
            String name = parts[0].trim().replace("\"", "");
            String country = parts[1].trim().replace("\"", "");
            boolean capital = "yes".equalsIgnoreCase(parts[2].trim());
            int population = Integer.parseInt(parts[3].trim().replace(",", ""));

            //create a new City object with the parsed data
            City city = new City(name, country, population, capital);

            //add the city to the list of cities for the given country
            citiesByCountry.putIfAbsent(country, new ArrayList<>());
            citiesByCountry.get(country).add(city);

            //if the city is a capital, add it to the capitals map
            if (capital) {
                capitalsByCountry.put(country, city);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid population number for city: " + parts[0] + "; skipping line.");
        }
    }

    //method to print details of a city given its name
    public void printCityDetails(String cityName) {
        citiesByCountry.values().stream()
                .flatMap(List::stream)
                .filter(city -> city.getName().equalsIgnoreCase(cityName))
                .findFirst()
                .ifPresent(System.out::println);
    }

    //method to print the capital of a country given the country name
    public void printCapitalOfCountry(String countryName) {
        Optional.ofNullable(capitalsByCountry.get(countryName))
                .ifPresent(System.out::println);
    }

    //method to print the largest city in a country given the country name
    public void printLargestCityInCountry(String countryName) {
        citiesByCountry.getOrDefault(countryName, Collections.emptyList()).stream()
                .max(Comparator.comparingInt(City::getPopulation))
                .ifPresent(System.out::println);
    }

    //method to print all capitals sorted by country name
    public void printAllCapitals() {
        capitalsByCountry.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue().getName()));
    }

    //method to print the number of cities for each country
    public void printCitiesPerCountry() {
        citiesByCountry.forEach((country, cities) ->
                System.out.println(country + " " + cities.size()));
    }

    //main method providing a command-line interface for user interaction
    public static void main(String[] args) {
        //create an instance of CityDatabase, pointing to the location of the CSV file
        CityDatabase cityDb = new CityDatabase("../world-cities.csv");
        //scanner for user input
        Scanner scanner = new Scanner(System.in);
        String choice;

        //continuously prompt the user for commands until they choose to quit
        while (true) {
            System.out.println("\nPlease enter:");
            System.out.println("1 to print city details");
            System.out.println("2 to print country's capital city");
            System.out.println("3 to print country's largest city");
            System.out.println("4 to print all capital cities");
            System.out.println("5 to print number of cities per country");
            System.out.println("quit to quit");
            choice = scanner.nextLine().trim();

            //switch statement to handle the user's menu selection
            switch (choice) {
                case "1":
                    System.out.println("Enter city name:");
                    cityDb.printCityDetails(scanner.nextLine().trim());
                    break;
                case "2":
                    System.out.println("Enter country name:");
                    cityDb.printCapitalOfCountry(scanner.nextLine().trim());
                    break;
                case "3":
                    System.out.println("Enter country name:");
                    cityDb.printLargestCityInCountry(scanner.nextLine().trim());
                    break;
                case "4":
                    cityDb.printAllCapitals();
                    break;
                case "5":
                    cityDb.printCitiesPerCountry();
                    break;
                case "quit":
                    System.out.println("Bye!");
                    //close the scanner to free up resources
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
