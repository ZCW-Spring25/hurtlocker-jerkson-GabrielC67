package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ItemParser {
    private String keyValueSeperator = "[:@^*%]";
    private String pairSeperator = ";";
    private Pattern keyValuePattern = Pattern.compile(keyValueSeperator);
    private Pattern pairPattern = Pattern.compile(pairSeperator);


    public List<Item> parseItemList(String valueToParse) {
        /*valueToParse Parameter
                .append("naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##")
                .append("naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##")
                .append("NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##")
                .toString();
         */


        return null;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {
        //String valueToParse = "naMe:eggS;price:1.25;type:Food;expiration:1/25/2016##";
        //String valueToParse =  "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##";
        if (singleItem == null || singleItem.trim().isEmpty()) {
            throw new ItemParseException();
        }

        String cleanedItem = singleItem.replaceAll("##", "");
        String[] pairs = pairPattern.split(cleanedItem);

        if (pairs.length < 4) {
            throw new ItemParseException();
        }

        Map<String, String> itemData = new HashMap<>();
        String[] valuesOnly = new String[pairs.length];
        int valuesIndex = 0;

        for (String pair : pairs) {
            String[] keyValue = keyValuePattern.split(pair, 2);
            if (keyValue.length == 2) {
                String key = keyValue[0].trim().toLowerCase();
                String value = keyValue[1].trim().toLowerCase();
                itemData.put(key, value);
                valuesOnly[valuesIndex++] = value.trim();
            } else {
                System.err.println();
            }
        }

        String name = itemData.get("name");
        String priceStr = itemData.get("price");
        String type = itemData.get("type");
        String expiration = itemData.get("expiration");

        if (name == null || priceStr == null || type == null || expiration == null) {
            throw new ItemParseException();
        }

        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            throw new ItemParseException();
        }

        return new Item(name, price, type, expiration);
    }
}
