package ru.otus.l011;


import com.google.common.base.Splitter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by knazarov.
 *
 *  * To start the application:
 * mvn clean package
 * java -jar ./target/L01.1-homework.jar this, is,,a,test
 *
 */

public class Main {

    public static void main(String... args) {
        //example input: this, is,,a,test
        List<String> inputChars = Arrays.asList(args);

        //using google guava splitter to split string on ','
        Iterable<String> str = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split(inputChars.toString());

        //example output: this, is, a, test]
        System.out.println(str);
    }
}
