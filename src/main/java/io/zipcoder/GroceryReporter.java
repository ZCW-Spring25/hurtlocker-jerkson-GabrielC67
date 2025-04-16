package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.List;
import java.util.Locale;

public class GroceryReporter {
    private final String originalFileText;

    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile(jerksonFileName);
//        this.parser = new ItemParser();
    }

    @Override
    public String toString() {
        ItemParser parser = new ItemParser();//String that is being tested
        List<Item> items;
        try {
            items = parser.parseItemList(this.originalFileText);
        } catch (Exception e) {
            System.err.println("Error parsing grocery data: " + e.getMessage());
            return "Error: Could not generate report due to parsing issues."  + e.getMessage();
        }
        StringBuilder report = new StringBuilder();

        report.append("=====================\n");

        if (items.isEmpty()) {
            report.append("No items found in the list.\n");
        } else {
            int itemCounter = 0;
            int errorCounter = 0;
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                if (item.getName() == null || item.getPrice() == null || item.getType() == null || item.getExpiration() == null) {
                    errorCounter++;
                    continue;
                }

                report.append(String.format(Locale.US,
                "name: %-10s seen: %d times\n",
                        item.getName()));
//                      item.getPrice()));
//                      item.getType(),
//                      item.getExpiration()));

                if (i < items.size() - 1) {
                    report.append("--------------------\n");
                }
            }
        }
        report.append("=====================");

        return report.toString();
    }
}
