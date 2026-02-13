

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
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

        int[] type = new int[hand.length];
        int[] bid = new int[hand.length];
        String[][] allCards = new String[hand.length][5];

        for (int h = 0; h < hand.length; h++) {
            String cardPart = hand[h].split("\\|")[0];
            int bidValue = Integer.parseInt(hand[h].split("\\|")[1]);
            bid[h] = bidValue;
            String[] deck = cardPart.split(",");

            for (int i = 0; i < deck.length; i++) {
                deck[i] = deck[i].trim();
            }
            int[] counts = new int[5];
            boolean[] counted = new boolean[5];

            for (int i = 0; i < 5; i++) {
                if (counted[i]) {
                    continue;
                }
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
            int rank;
            if (max == 5) {
                fiveKind++;
                rank = 7;
            } else if (max == 4) {
                fourKind++;
                rank = 6;
            } else if (max == 3 && second == 2) {
                fullHouse++;
                rank = 5;
            } else if (max == 3) {
                threeKind++;
                rank = 4;
            } else if (max == 2 && second == 2) {
                twoPair++;
                rank = 3;
            } else if (max == 2) {
                onePair++;
                rank = 2;
            } else {
                highCard++;
                rank = 1;
            }

            type[h] = rank;
            allCards[h] = deck;
        }

        int total = 0;

        for (int i = 0; i < hand.length; i++) {
            int rank = 1;

            for (int j = 0; j < hand.length; j++) {
                if (compare(i, j, allCards, type) > 0)
                    rank++;
            }

            total += (int) rank * bid[i];
        }


        System.out.println("Number of five of a kind hands: " + fiveKind);
        System.out.println("Number of full house hands: " + fullHouse);
        System.out.println("Number of four of a kind hands: " + fourKind);
        System.out.println("Number of three of a kind hands: " + threeKind);
        System.out.println("Number of two pair hands: " + twoPair);
        System.out.println("Number of one pair hands: " + onePair);
        System.out.println("Number of high card hands: " + highCard);
        System.out.println("Total Bid Value: " + total);
    }
    public static int compare(int i, int j, String[][] cards, int[] type) {
        if (type[i] != type[j]) {
            return type[i] - type[j];
        }
        for (int k = 0; k < 5; k++) {
            int a = convert(cards[i][k]);
            int b = convert(cards[j][k]);

            if (a != b) return a - b;
        }

        return 0;
    }

    public static int convert (String card) {
        if (card.equals("Ace")) {
            return 12;
        }
        if (card.equals("King")) {
            return 11;
        }
        if (card.equals("Queen")) {
            return 10;
        }
        if (card.equals("Jack")) {
            return 9;
        }
        return Integer.parseInt(card) - 2;
    }
}
