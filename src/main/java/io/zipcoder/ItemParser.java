package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
    private String keyValueSeperator = "[:@^*%]";
    private String pairSeperator = ";";
    private String itemSeperator = "##";
    private Pattern keyValuePattern = Pattern.compile(keyValueSeperator);
    private Pattern pairPattern = Pattern.compile(pairSeperator);
    private Pattern itemPattern = Pattern.compile(itemSeperator);

    private static final Pattern PAIR_EXTRACTOR_PATTERN = Pattern.compile("(\\w+)([^a-zA-Z0-9\\s])([^;#]+)");


//    public List<Item> parseItemList(String valueToParse) {
//        /*valueToParse Parameter
//                .append("naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##")
//                .append("naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##")
//                .append("NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##")
//                .toString();
//         */
//        List<Item> items = new ArrayList<>();
//
//        if (valueToParse == null || valueToParse.trim().isEmpty()) {
//            return items;
//        }
//
//        String[] singleItems = itemPattern.split(valueToParse);
//
//        for (String itemStr : singleItems) {
//            if (itemStr == null || itemStr.trim().isEmpty()) {
//                continue;
//            }
//
//            try {
//                Item parsedItem = parseSingleItem(itemStr);
////                System.out.println("Parsed item: " + parsedItem);
//                items.add(parsedItem);
//            } catch (ItemParseException e) {
//                // Handle the exception if needed
////                System.err.println("Error parsing item: " + itemStr);
//            }
//        }
//        return items;
//    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {
        //String valueToParse = "naMe:eggS;price:1.25;type:Food;expiration:1/25/2016##";
        //String valueToParse =  "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##";
        if (singleItem == null || singleItem.trim().isEmpty()) {
            throw new ItemParseException();
        }

        String cleanString = singleItem.trim();
        if (cleanString.endsWith("##")) {
            cleanString = cleanString.substring(0, cleanString.length() - 2);
        }

        Map<String, String> itemData = new HashMap<>();
        Matcher matcher = PAIR_EXTRACTOR_PATTERN.matcher(cleanString);

        while (matcher.find()) {
            String key = matcher.group(1).trim().toLowerCase();
            String value = matcher.group(3).trim().toLowerCase();
            itemData.put(key, value);
        }

        String name = itemData.get("Name");
        String priceStr = itemData.get("Price");
        String type = itemData.get("Type");
        String expiration = itemData.get("Expiration");

        if (name == null  || priceStr == null || type == null || expiration == null) {
            StringBuilder missing = new StringBuilder("Missing required fields: ");
            if (name == null) missing.append("'name' ");
            if (priceStr == null) missing.append("'price' ");
            if (type == null) missing.append("'type' ");
            if (expiration == null) missing.append("'expiration' ");
            // Include details for easier debugging
            missing.append("| Found data: ").append(itemData);
            missing.append("| Original input: '").append(singleItem).append("'");
            throw new ItemParseException();
        }

        double price;
        try {
            price = Double.parseDouble(priceStr);
            return new Item(name, price, type, expiration);
        } catch (NumberFormatException e) {
            throw new ItemParseException();
        }
    }
}
