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


public class IPLRunsAdapter extends IPLAdapter {
    Map<String, IPLCSVDTO> IPLMap = new HashMap<>();

    @Override
    public Map<String, IPLCSVDTO> loadIPLData(String... csvFilePath) {
        Map<String, IPLCSVDTO> IPLMap = super.loadIPLData(IPLRunsCSV.class, csvFilePath[0]);
        if(csvFilePath.length > 1){
            this.loadBowlingData(IPLMap, csvFilePath[1]);
        }
        return IPLMap;
    }

    public void loadBowlingData(Map<String, IPLCSVDTO> iplDTOMap, String csvVFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvVFilePath))) {
            ICSVBuilder iCsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLWicketsCSV> iterator = iCsvBuilder.getCSVIterator(reader, IPLWicketsCSV.class);
            Iterable<IPLWicketsCSV> iterable = () -> iterator;

            StreamSupport.stream(iterable.spliterator(), false)
                    .filter(iplMostWicketsAdapter -> iplDTOMap.get(iplMostWicketsAdapter.player) != null)
                    .forEach(mergedData -> {
                        iplDTOMap.get(mergedData.player).bowlingaverage = mergedData.average;
                        iplDTOMap.get(mergedData.player).wickets = mergedData.wickets;
                        iplDTOMap.get(mergedData.player).mostWickets = mergedData.wickets;

                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

