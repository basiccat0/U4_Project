
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
            String handPart = hand[h].split("\\|")[0];
            String[] cards = handPart.split(",");

            for (int i = 0; i < cards.length; i++) {
                cards[i] = cards[i].trim();
            }
            int[] counts = new int[5];

            for (int i = 0; i < 5; i++) {
                int count = 1;
                for (int j = i + 1; j < 5; j++) {
                    if (cards[i].equals(cards[j])) {
                        count++;
                    }
                }

                counts[i] = count;
            }

            int most = 0;
            int secondMost = 0;

            for (int i = 0; i < 5; i++) {
                if (counts[i] > most) {
                    secondMost = most;
                    most = counts[i];
                } else if (counts[i] > secondMost) {
                    secondMost = counts[i];
                }
            }
            
            if (most == 5) {
                fiveKind++;
            } else if (most == 4) {
                fourKind++;
            } else if (most == 3 && secondMost == 2) {
                fullHouse++;
            } else if (most == 3) {
                threeKind++;
            } else if (most == 2 && secondMost == 2) {
                twoPair++;
            } else if (most == 2) {
                onePair++;
            } else {
                highCard++;
            }
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
