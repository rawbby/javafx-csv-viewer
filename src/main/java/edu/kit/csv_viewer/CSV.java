package edu.kit.csv_viewer;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class CSV {
    private CSV() {
    }

    private static ArrayList<String> parseLine(String line) {
        var entry = new ArrayList<String>();
        @SuppressWarnings("RegExpRedundantEscape") final var regex = "(?<=^|,)(?:\\\"((?:[^\\\"\\\\]|\\\\.)*)\\\"|((?:[^,\\\"][^,]*)?))(?=,|$)";
        final var pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final var matcher = pattern.matcher(line);
        while (matcher.find()) {
            if (matcher.group(1) != null)
                entry.add(matcher.group(1));
            else if (matcher.group(2) != null)
                entry.add(matcher.group(2));
            else
                throw new RuntimeException("Regular expression with two empty groups where only one is expected to be possible!");
        }
        return entry;
    }

    public static ArrayList<ArrayList<String>> parse(String csv) {
        var result = new ArrayList<ArrayList<String>>();
        final var lines = csv.split("[\r\n]");
        for (var line : lines)
            result.add(parseLine(line));
        return result;
    }

    public static ArrayList<ArrayList<String>> read(String path) {
        var result = new ArrayList<ArrayList<String>>();
        try (var reader = new BufferedReader(new FileReader(path))) {
            var line = reader.readLine();
            while (line != null) {
                result.add(parseLine(line));
                line = reader.readLine();
            }
        } catch (IOException _) {
        }
        return result;
    }
}
