package csv;

import domain.LenderOffer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;
import static java.util.stream.Collectors.toList;

public class CsvReader {

    public static Collection<LenderOffer> csvToLenderInfoList(String marketFile) {
        try {
            List<String> marketDataEntriesAsStrings = readAllLines(get(marketFile));
            return parseStringsToLenderInfo(marketDataEntriesAsStrings);
        } catch (IOException e) {
            // Needs proper exception handling/logging, depending on how we want to deal with such cases
            throw new RuntimeException("Couldn't read market data file", e);
        }
    }

    private static Collection<LenderOffer> parseStringsToLenderInfo(List<String> marketDataEntriesAsStrings) {
        return marketDataEntriesAsStrings.stream().skip(1).map(s -> {
            String[] lenderInfoAsArray = s.split(",");
            BigDecimal rate = new BigDecimal(lenderInfoAsArray[1]);
            BigDecimal available = new BigDecimal(lenderInfoAsArray[2]);
            return new LenderOffer(rate, available);
        }).collect(toList());
    }
}
