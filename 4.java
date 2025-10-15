import java.util.*;

public class EasyRecommender {
    public static void main(String[] args) {

        // Step 1: Sample user preferences (User -> List of liked items)
        Map<String, List<String>> userData = new HashMap<>();
        userData.put("User1", Arrays.asList("BookA", "BookB", "BookC"));
        userData.put("User2", Arrays.asList("BookA", "BookD"));
        userData.put("User3", Arrays.asList("BookB", "BookE"));
        userData.put("User4", Arrays.asList("BookA", "BookB", "BookD", "BookE"));

        // Step 2: Ask which user to recommend for
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter user name (User1, User2, etc.): ");
        String user = sc.nextLine();

        if (!userData.containsKey(user)) {
            System.out.println("User not found!");
            sc.close();
            return;
        }

        // Step 3: Find similar users (based on common items)
        List<String> targetItems = userData.get(user);
        Map<String, Integer> similarityScore = new HashMap<>();

        for (String other : userData.keySet()) {
            if (other.equals(user)) continue;
            List<String> otherItems = userData.get(other);
            int common = 0;
            for (String item : otherItems) {
                if (targetItems.contains(item)) common++;
            }
            similarityScore.put(other, common);
        }

        // Step 4: Recommend new items from most similar users
        Set<String> recommended = new HashSet<>();
        for (Map.Entry<String, Integer> entry : similarityScore.entrySet()) {
            if (entry.getValue() > 0) {
                for (String item : userData.get(entry.getKey())) {
                    if (!targetItems.contains(item)) {
                        recommended.add(item);
                    }
                }
            }
        }

        // Step 5: Display results
        System.out.println("\nUser Data: " + userData);
        System.out.println("\nMost similar users and their scores: " + similarityScore);
        System.out.println("\nRecommended items for " + user + ": " + recommended);

        sc.close();
    }
}
