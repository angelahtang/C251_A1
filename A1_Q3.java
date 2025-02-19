import java.util.*;

// No collaborators

public class A1_Q3 {
	public static ArrayList<String> Discussion_Board(String[] posts) {
		HashSet<String> users = new HashSet<>();    // unique users
		HashMap<String, HashSet<String>> user_words = new HashMap<>();  // user key to their words
        HashMap<String, Integer> word_count = new HashMap<>();  // word key to their count


        for (String post : posts) {        // get words from user_words; post is parameter
			String[] parts = post.split(" ", 2);
			if (parts.length < 2) continue;    // empty = skip

			String user = parts[0];        // user = first word
			users.add(user);    // add to hash set of users
			user_words.putIfAbsent(user, new HashSet<>());

			String[] words = parts[1].split(" ");        // word chunk
			for (String word : words) {        // update count and user-word hash
				user_words.get(user).add(word);
				word_count.put(word, word_count.getOrDefault(word, 0) + 1);
			}
		}

        HashMap<String, Integer> word_freq = new HashMap<>();	// count words for unique users
        for (String user : user_words.keySet()) {
            for (String word : user_words.get(user)) {
                word_freq.put(word, word_freq.getOrDefault(word, 0) + 1);
            }
        }

		int num_users = users.size();
        ArrayList<String> result = new ArrayList<>();	// words used by all users
        for (String word : word_freq.keySet()) {
            if (word_freq.get(word) == num_users) {
                result.add(word);
            }
        }

        result.sort((a, b) -> {		// sort words in from most to least frequent + alphabet
            int compare_count = word_count.get(b) - word_count.get(a);
            return (compare_count == 0) ? a.compareTo(b) : compare_count;
        });

        return result;
    }

    public static void main(String[] args) {
        /*** Test #1 ***/
        String[] test1 = {
            "David no no no no nobody never",
            "Alexia why ever not",
            "Parham no not never nobody",
            "Brian no never know nobody",
            "Jeremy why no nobody",
            "Jeremy nobody never know why nobody",
            "David never no nobody",
            "Alexia never never nobody no"
        };

        System.out.println(Discussion_Board(test1));
        // expected: [no, nobody, never]

        /*** Test #6 ***/
        String[] test2 = {
                "James gobble de gook",
                "Bill gobble",
                "james de gook"
        };

        System.out.println(Discussion_Board(test2));
        // expected: empty arraylist
    }

}
