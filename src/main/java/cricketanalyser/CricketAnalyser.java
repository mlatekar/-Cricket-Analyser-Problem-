package cricketanalyser;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.collections.map.HashedMap;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class CricketAnalyser {
    List<IPLRunsCSV> IPLCSVList=null;
    Map<SortField, Comparator<IPLRunsCSV>> sortMap=null;

    public CricketAnalyser() {
       IPLCSVList=new ArrayList<>();
       sortMap=new HashMap<>();
       this.sortMap.put(SortField.AVERAGE,Comparator.comparing(IPL -> IPL.average));
       this.sortMap.put(SortField.STRIKE_RATE,Comparator.comparing(IPL -> IPL.sr));
       this.sortMap.put(SortField.MAX6SAND4S,Comparator.comparing(IPL -> IPL.fourS+IPL.sixS));
    }

    public int loadIPLRunsCSVData(String csvFilePath) throws CricketAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IPLRunsCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IPLRunsCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IPLRunsCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IPLRunsCSV> IPLCSVIterator = csvToBean.iterator();;
            int namOfEateries = 0;
            while (IPLCSVIterator.hasNext()) {
                namOfEateries++;
                IPLRunsCSV IPLData = IPLCSVIterator.next();
                IPLCSVList.add(IPLData);
            }
            return namOfEateries;
        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.IPL_CENSUS_FILE_PROBLEM);
        }
    }

    public static void main(String []args) {
        System.out.println("Welcome to Cricket Analyser");
    }

    public String getSortedData(SortField sortField) throws CricketAnalyserException {
        if(IPLCSVList ==null || IPLCSVList.size() == 0) {
            throw new CricketAnalyserException("No Census Data",CricketAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }

        sort(this.sortMap.get(sortField));
        Collections.reverse(IPLCSVList);
        String sortedRunsData=new Gson().toJson(IPLCSVList);
        return sortedRunsData;
    }

    private  void sort(Comparator<IPLRunsCSV> IplCSVComparator) {
        for (int i=0; i<IPLCSVList.size()-1; i++){
            for (int j=0; j<IPLCSVList.size()-1; j++){
                IPLRunsCSV census1=IPLCSVList.get(j);
                IPLRunsCSV census2=IPLCSVList.get(j+1);
                if (IplCSVComparator.compare(census1,census2)>0) {
                    IPLCSVList.set(j,census2);
                    IPLCSVList.set(j+1,census1);
                }
            }
        }
    }
}
