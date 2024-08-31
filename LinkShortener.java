import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class LinkShortener {
    // Base62 characters for encoding
    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;
    private Map<String, String> urlMap = new HashMap<>();
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    // Generate a short URL
    public String shorten(String longUrl) {
        String shortUrl;
        do {
            shortUrl = generateShortUrl();
        } while (urlMap.containsKey(shortUrl)); // Ensure uniqueness
        urlMap.put(shortUrl, longUrl);
        return shortUrl;
    }

    // Retrieve the long URL from a short URL
    public String expand(String shortUrl) {
        return urlMap.get(shortUrl);
    }

    // Generate a random short URL
    private String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder(SHORT_URL_LENGTH);
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = random.nextInt(BASE62.length());
            shortUrl.append(BASE62.charAt(index));
        }
        return shortUrl.toString();
    }

    // Main method to handle user input and output
    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Shorten a URL");
            System.out.println("2. Expand a URL");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter the long URL: ");
                String longUrl = scanner.nextLine();
                String shortUrl = linkShortener.shorten(longUrl);
                System.out.println("Shortened URL: " + shortUrl);
            } else if (choice == 2) {
                System.out.print("Enter the short URL: ");
                String shortUrl = scanner.nextLine();
                String longUrl = linkShortener.expand(shortUrl);
                if (longUrl != null) {
                    System.out.println("Expanded URL: " + longUrl);
                } else {
                    System.out.println("Short URL not found.");
                }
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}