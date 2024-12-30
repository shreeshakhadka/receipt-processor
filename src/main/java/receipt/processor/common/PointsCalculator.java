package receipt.processor.common;

import receipt.processor.model.Item;
import receipt.processor.model.Receipt;

import java.time.LocalTime;

public class PointsCalculator {
    private static final int ROUND_DOLLAR_POINTS = 50;
    private static final int MULTIPLE_OF_25_POINTS = 25;
    private static final int POINTS_PER_TWO_ITEMS = 5;
    private static final int ODD_DAY_POINTS = 6;
    private static final int PURCHASE_TIME_BONUS = 10;
    private static final LocalTime BONUS_TIME_START = LocalTime.of(14, 0);
    private static final LocalTime BONUS_TIME_END = LocalTime.of(16, 0);

    public static int calculatePoints(Receipt receipt) {
        int totalPoints = 0;
        totalPoints += countAlphanumericCharacters(receipt.getRetailer());
        totalPoints += calculateTotalPoints(receipt.getTotal());
        totalPoints += calculateItemPoints(receipt.getItems());
        totalPoints += calculateDatePoints(receipt.getPurchaseDate());
        totalPoints += calculateTimePoints(receipt.getPurchaseTime());
        return totalPoints;
    }

    private static int countAlphanumericCharacters(String text) {
        return text.replaceAll("[^a-zA-Z0-9]", "").length();
    }

    private static int calculateTotalPoints(String totalString) {
        double total = Double.parseDouble(totalString);
        int points = 0;
        if (totalString.endsWith(".00")) {
            points += ROUND_DOLLAR_POINTS;
        }
        if (total % 0.25 == 0) {
            points += MULTIPLE_OF_25_POINTS;
        }
        return points;
    }

    private static int calculateItemPoints(java.util.List<Item> items) {
        int points = 0;
        points += (items.size() / 2) * POINTS_PER_TWO_ITEMS;
        for (Item item : items) {
            String description = item.getShortDescription().trim();
            if (description.length() % 3 == 0) {
                double price = Double.parseDouble(item.getPrice());
                points += (int) Math.ceil(price * 0.2);
            }
        }
        return points;
    }

    private static int calculateDatePoints(String purchaseDate) {
        String[] dateParts = purchaseDate.split("-");
        int dayOfMonth = Integer.parseInt(dateParts[2]);
        return dayOfMonth % 2 != 0 ? ODD_DAY_POINTS : 0;
    }

    private static int calculateTimePoints(String purchaseTime) {
        LocalTime time = LocalTime.parse(purchaseTime);
        return (time.isAfter(BONUS_TIME_START) && time.isBefore(BONUS_TIME_END)) ? PURCHASE_TIME_BONUS : 0;
    }
}
