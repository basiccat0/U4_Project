
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int fiveKind = 0;
        int fourKind = 0;
        int fullHouse = 0;
        int threeKind = 0;
        int twoPair = 0;
        int onePair = 0;
        int highCard = 0;

        String fileData = "";

        try {
            File f = new File("src/data");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {
                fileData += s.nextLine() + "\n";
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }

        String[] hand = fileData.split("\n");
        for (int h = 0; h < hand.length; h++) {
            String bid = hand[h].split("\\|")[0];
            String[] deck = bid.split(",");

            for (int i = 0; i < deck.length; i++) {
                deck[i] = deck[i].trim();
            }
            int[] counts = new int[5];
            boolean[] counted = new boolean[5];

            for (int i = 0; i < 5; i++) {
                if (counted[i]) continue;

                int count = 1;

                for (int j = i + 1; j < 5; j++) {
                    if (deck[i].equals(deck[j])) {
                        count++;
                        counted[j] = true;
                    }
                }

                counts[i] = count;
            }

            int max = 0;
            int second = 0;

            for (int i = 0; i < 5; i++) {
                if (counts[i] > max) {
                    second = max;
                    max = counts[i];
                } else if (counts[i] > second) {
                    second = counts[i];
                }
            }

            if (max == 5) {
                fiveKind++;

            } else if (max == 4) {
                fourKind++;
            } else if (max == 3 && second == 2) {
                fullHouse++;
            } else if (max == 3) {
                threeKind++;
            } else if (max == 2 && second == 2) {
                twoPair++;
            } else if (max == 2) {
                onePair++;
            } else {
                highCard++;
            }
        }

        int total = 0;

        for (int i = 0; i < hand.length; i++) {
            int rank = 1;

            for (int j = 0; j < hand.length; j++) {
                if (compare(i, j, allCards, type) > 0)
                    rank++;
            }

            total += (int) rank * bids[i];
        }

        System.out.println("Number of five of a kind hands: " + fiveKind);
        System.out.println("Number of full house hands: " + fullHouse);
        System.out.println("Number of four of a kind hands: " + fourKind);
        System.out.println("Number of three of a kind hands: " + threeKind);
        System.out.println("Number of two pair hands: " + twoPair);
        System.out.println("Number of one pair hands: " + onePair);
        System.out.println("Number of high card hands: " + highCard);
    }
}
