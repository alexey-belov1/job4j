package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.regex.Pattern;

public class PHTML {

    private static final Logger LOG = LogManager.getLogger(PHTML.class.getName());

    private String url = "https://www.sql.ru/forum/job-offers";

    private DateTimeFormatter fmt = new DateTimeFormatterBuilder()
            .appendPattern("d ")
            .appendText(ChronoField.MONTH_OF_YEAR, new HashMap<>() { {
                put(1L, "янв"); put(2L,  "фев"); put(3L,  "мар"); put(4L,  "апр");
                put(5L, "май"); put(6L,  "июн"); put(7L,  "июл"); put(8L,  "авг");
                put(9L, "сен"); put(10L, "окт"); put(11L, "ноя"); put(12L, "дек");
            } })
            .appendPattern(" yy, HH:mm")
            .toFormatter(new Locale("ru"));

    private LocalDateTime parseDate(String dateIn) {
        LocalDateTime dateOut;
        if (dateIn.contains("сегодня")) {
            dateOut = LocalDateTime.of(
                    LocalDate.now(),
                    LocalTime.parse(dateIn.split(" ")[1])
            );
        } else if (dateIn.contains("вчера")) {
            dateOut = LocalDateTime.of(
                    LocalDate.now().minusDays(1L),
                    LocalTime.parse(dateIn.split(" ")[1])
            );
        } else {
            dateOut = LocalDateTime.parse(dateIn, fmt);
        }

        return dateOut;
    }

    private int getPages() {
        int page = 0;
        try {
            Document doc = Jsoup.connect(url).get();
            page = Integer.parseInt(doc.select("table.sort_options td a").last().text());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return page;
    }

    private boolean filter(String str) {
        return Pattern.compile("java\\b", Pattern.CASE_INSENSITIVE).matcher(str).find();
    }

    public Set<Vacancy> parseVacancies(LocalDateTime datePre) {
        LOG.info("Start parsing...");

        Set<Vacancy> vacancies = new LinkedHashSet<>();
        exitlabel: for (int i = 1; i <= getPages(); i++) {
            try {
                Document doc = Jsoup.connect(url + "/" + i).get();
                Elements table = doc.select("table.forumtable tr:has(td)");
                for (Element tr : table) {
                    Elements td = tr.select("td");

                    String text = String.format("%s (%s)", td.get(1).text(), parseDate(td.get(5).text()).format(fmt));

                    if (filter(text)) {
                        LocalDateTime date = parseDate(td.get(5).text());
                        if (datePre.isAfter(date)) {
                            break exitlabel;
                        }
                        String name = td.get(2).text();
                        String link = td.get(1).select("a").attr("href");
                        vacancies.add(new Vacancy(name, text, link));
                    }
                }
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }

        LOG.info(String.format("%s new vacancies found.", vacancies.size()));
        return vacancies;
    }
}
