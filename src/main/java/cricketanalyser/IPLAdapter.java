package cricketanalyser;

import com.censusjar.CSVBuilderFactory;
import com.censusjar.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IPLAdapter {

    public abstract Map<String, IPLCSVDTO> loadCensusData(String... csvFilePath);

    public <E> Map<String, IPLCSVDTO> loadCensusData(Class<E> csvClass, String csvFilePath) {

        Map<String, IPLCSVDTO> censusCSVMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> censusCSVIterator = csvBuilder.getCSVIterator(reader,csvClass);
            Iterable<E> censusCodeIterable = () -> censusCSVIterator;
            if (csvClass.getName().equals("cricketanalyser.IPLRunsCSV")) {
                StreamSupport.stream(censusCodeIterable.spliterator(), false)
                        .map(IPLRunsCSV.class::cast)
                        .forEach(iplMap -> censusCSVMap.put(iplMap.player, new IPLCSVDTO(iplMap)));
            } else if (csvClass.getName().equals("cricketanalyser.IPLWicketsCSV")) {
                StreamSupport.stream(censusCodeIterable.spliterator(), false)
                        .map(IPLWicketsCSV.class::cast)
                        .forEach(iplMap -> censusCSVMap.put(iplMap.player, new IPLCSVDTO(iplMap)));
            }

            return censusCSVMap;
        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.IPL_CENSUS_FILE_PROBLEM);
        }
    }

}
