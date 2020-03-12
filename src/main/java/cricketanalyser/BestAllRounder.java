package cricketanalyser;

import com.censusjar.CSVBuilderFactory;
import com.censusjar.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class BestAllRounder extends IPLAdapter {
    Map<String,IPLCSVDTO> iplDTOMap=null;
    @Override
    public Map<String, IPLCSVDTO> loadCensusData(String... csvFilePath) {
        iplDTOMap=super.loadCensusData(IPLRunsCSV.class,csvFilePath[0]);
        this.loadBowlingData(iplDTOMap,csvFilePath[1]);
        return iplDTOMap;
    }
    public void loadBowlingData(Map<String, IPLCSVDTO> iplDTOMap, String csvVFilePath) {
        try(Reader reader= Files.newBufferedReader(Paths.get(csvVFilePath))) {
            ICSVBuilder iCsvBuilder= CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLWicketsCSV> iterator=iCsvBuilder.getCSVIterator(reader,IPLWicketsCSV.class);
            Iterable<IPLWicketsCSV> iterable=() ->iterator;

            StreamSupport.stream(iterable.spliterator(),false)
                    .filter(iplMostWicketsAdapter -> iplDTOMap.get(iplMostWicketsAdapter.player)!=null )
                    .forEach(mergedData->{iplDTOMap.get(mergedData.player).wickets=mergedData.wickets;
                                     iplDTOMap.get(mergedData.player).mostWickets=mergedData.wickets;});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
