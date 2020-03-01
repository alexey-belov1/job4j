package ru.job4j.price;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PriceTest {

    private Date setDate(String date) {
        Date out = null;
        try {
            out = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    @Test
    public void whenTestInTask() {

        List<Price> currentPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("31.01.2013 23:59:59"), 11000),
                new Price("122856", 2, 1, setDate("10.01.2013 00:00:00"), setDate("20.01.2013 23:59:59"), 99000),
                new Price("6654", 1, 2, setDate("01.01.2013 00:00:00"), setDate("31.01.2013 00:00:00"), 5000)
        );

        List<Price> newPrice = List.of(
                new Price("122856", 1, 1, setDate("20.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000),
                new Price("122856", 2, 1, setDate("15.01.2013 00:00:00"), setDate("25.01.2013 23:59:59"), 92000),
                new Price("6654", 1, 2, setDate("12.01.2013 00:00:00"), setDate("13.01.2013 00:00:00"), 4000)
        );

        List<Price> expected = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000),
                new Price("122856", 2, 1, setDate("10.01.2013 00:00:00"), setDate("15.01.2013 00:00:00"), 99000),
                new Price("122856", 2, 1, setDate("15.01.2013 00:00:00"), setDate("25.01.2013 23:59:59"), 92000),
                new Price("6654", 1, 2, setDate("01.01.2013 00:00:00"), setDate("12.01.2013 00:00:00"), 5000),
                new Price("6654", 1, 2, setDate("12.01.2013 00:00:00"), setDate("13.01.2013 00:00:00"), 4000),
                new Price("6654", 1, 2, setDate("13.01.2013 00:00:00"), setDate("31.01.2013 00:00:00"), 5000)
        );

        List<Price> result = Price.putPrices(currentPrice, newPrice);

        assertThat(expected, is(result));
    }

    @Test
    public void whenCase1Before() {

        List<Price> currentPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.01.2013 23:59:59"), 11000)
        );

        List<Price> newPrice = List.of(
                new Price("122856", 1, 1, setDate("01.02.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 9000)
        );

        List<Price> expected = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.01.2013 23:59:59"), 11000),
                new Price("122856", 1, 1, setDate("01.02.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 9000)
        );

        List<Price> result = Price.putPrices(currentPrice, newPrice);

        assertThat(expected, is(result));
    }

    @Test
    public void whenCase1Equals() {

        List<Price> currentPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("01.02.2013 23:59:59"), 11000)
        );

        List<Price> newPrice = List.of(
                new Price("122856", 1, 1, setDate("01.02.2013 23:59:59"), setDate("20.02.2013 23:59:59"), 9000)
        );

        List<Price> expected = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("01.02.2013 23:59:59"), 11000),
                new Price("122856", 1, 1, setDate("01.02.2013 23:59:59"), setDate("20.02.2013 23:59:59"), 9000)
        );

        List<Price> result = Price.putPrices(currentPrice, newPrice);

        assertThat(expected, is(result));
    }

    @Test
    public void whenCase2Before() {

        List<Price> currentPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("01.02.2013 23:59:59"), 11000)
        );

        List<Price> newPrice = List.of(
                new Price("122856", 1, 1, setDate("20.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 9000)
        );

        List<Price> expected = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.01.2013 00:00:00"), 11000),
                new Price("122856", 1, 1, setDate("20.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 9000)
        );

        List<Price> result = Price.putPrices(currentPrice, newPrice);

        assertThat(expected, is(result));
    }

    @Test
    public void whenCase2Equals() {

        List<Price> currentPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> newPrice = List.of(
                new Price("122856", 1, 1, setDate("20.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 9000)
        );

        List<Price> expected = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.01.2013 00:00:00"), 11000),
                new Price("122856", 1, 1, setDate("20.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 9000)
        );

        List<Price> result = Price.putPrices(currentPrice, newPrice);

        assertThat(expected, is(result));
    }

    @Test
    public void whenCase3() {

        List<Price> currentPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> newPrice = List.of(
                new Price("122856", 1, 1, setDate("20.01.2013 00:00:00"), setDate("01.02.2013 23:59:59"), 9000)
        );

        List<Price> expected = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.01.2013 00:00:00"), 11000),
                new Price("122856", 1, 1, setDate("20.01.2013 00:00:00"), setDate("01.02.2013 23:59:59"), 9000),
                new Price("122856", 1, 1, setDate("01.02.2013 23:59:59"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> result = Price.putPrices(currentPrice, newPrice);

        assertThat(expected, is(result));
    }

    @Test
    public void whenCase4Before() {

        List<Price> currentPrice = List.of(
                new Price("122856", 1, 1, setDate("20.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> newPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("01.02.2013 23:59:59"), 9000)
        );

        List<Price> expected = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("01.02.2013 23:59:59"), 9000),
                new Price("122856", 1, 1, setDate("01.02.2013 23:59:59"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> result = Price.putPrices(currentPrice, newPrice);

        assertThat(expected, is(result));
    }

    @Test
    public void whenCase4Equals() {

        List<Price> currentPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> newPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("01.02.2013 23:59:59"), 9000)
        );

        List<Price> expected = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("01.02.2013 23:59:59"), 9000),
                new Price("122856", 1, 1, setDate("01.02.2013 23:59:59"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> result = Price.putPrices(currentPrice, newPrice);

        assertThat(expected, is(result));
    }

    @Test
    public void whenCase5Before() {

        List<Price> currentPrice = List.of(
                new Price("122856", 1, 1, setDate("01.02.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> newPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.01.2013 23:59:59"), 9000)
        );

        List<Price> expected = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.01.2013 23:59:59"), 9000),
                new Price("122856", 1, 1, setDate("01.02.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> result = Price.putPrices(currentPrice, newPrice);

        assertThat(expected, is(result));
    }

    @Test
    public void whenCase5Equals() {

        List<Price> currentPrice = List.of(
                new Price("122856", 1, 1, setDate("01.02.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> newPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("01.02.2013 00:00:00"), 9000)
        );

        List<Price> expected = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("01.02.2013 00:00:00"), 9000),
                new Price("122856", 1, 1, setDate("01.02.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> result = Price.putPrices(currentPrice, newPrice);

        assertThat(expected, is(result));
    }

    @Test
    public void whenCase6Before() {

        List<Price> currentPrice = List.of(
                new Price("122856", 1, 1, setDate("20.01.2013 00:00:00"), setDate("01.02.2013 23:59:59"), 11000)
        );

        List<Price> newPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 9000)
        );

        List<Price> expected = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 9000)
        );

        List<Price> result = Price.putPrices(currentPrice, newPrice);

        assertThat(expected, is(result));
    }

    @Test
    public void whenCase6Equals() {

        List<Price> currentPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> newPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 9000)
        );

        List<Price> expected = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 9000)
        );

        List<Price> result = Price.putPrices(currentPrice, newPrice);

        assertThat(expected, is(result));
    }


    @Test
    public void whenCase1SameValue() {

        List<Price> currentPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("01.02.2013 23:59:59"), 11000)
        );

        List<Price> newPrice = List.of(
                new Price("122856", 1, 1, setDate("01.02.2013 23:59:59"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> expected = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> result = Price.putPrices(currentPrice, newPrice);

        assertThat(expected, is(result));
    }

    @Test
    public void whenCase2SameValue() {

        List<Price> currentPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("01.02.2013 23:59:59"), 11000)
        );

        List<Price> newPrice = List.of(
                new Price("122856", 1, 1, setDate("20.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> expected = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> result = Price.putPrices(currentPrice, newPrice);

        assertThat(expected, is(result));
    }


    @Test
    public void whenCase3SameValue() {

        List<Price> currentPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> newPrice = List.of(
                new Price("122856", 1, 1, setDate("20.01.2013 00:00:00"), setDate("01.02.2013 23:59:59"), 11000)
        );

        List<Price> expected = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> result = Price.putPrices(currentPrice, newPrice);

        assertThat(expected, is(result));
    }

    @Test
    public void whenCase4SameValue() {

        List<Price> currentPrice = List.of(
                new Price("122856", 1, 1, setDate("20.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> newPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("01.02.2013 23:59:59"), 11000)
        );

        List<Price> expected = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> result = Price.putPrices(currentPrice, newPrice);

        assertThat(expected, is(result));
    }

    @Test
    public void whenCase5SameValue() {

        List<Price> currentPrice = List.of(
                new Price("122856", 1, 1, setDate("01.02.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> newPrice = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("01.02.2013 00:00:00"), 11000)
        );

        List<Price> expected = List.of(
                new Price("122856", 1, 1, setDate("01.01.2013 00:00:00"), setDate("20.02.2013 23:59:59"), 11000)
        );

        List<Price> result = Price.putPrices(currentPrice, newPrice);

        assertThat(expected, is(result));
    }
}