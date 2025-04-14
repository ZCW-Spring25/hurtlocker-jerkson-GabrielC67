package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.Arrays;
import java.util.List;

public class ItemParser {
    String seperatorRegex = "[:@^*%#;]";
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

        String singleItemLowerCase = singleItem.toLowerCase(); //This should make the entire string lowercase
        String pairArr = Arrays.toString(singleItemLowerCase.split(seperatorRegex)); //This will split the string at each ;



        return null;
    }
}
