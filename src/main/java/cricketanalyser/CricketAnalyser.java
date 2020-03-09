package cricketanalyser;

import com.censusjar.CSVBuilderFactory;
import com.censusjar.ICSVBuilder;
import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.collections.map.HashedMap;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class CricketAnalyser {
    List<IPLCSVDTO> IPLCSVList=null;
    Map<String,IPLCSVDTO> IPLMap=null;
    Map<SortField, Comparator<IPLCSVDTO>> sortMap=null;

    public CricketAnalyser() {
        IPLMap=new HashMap<>();
       IPLCSVList=new ArrayList<>();
       sortMap=new HashMap<>();
       this.sortMap.put(SortField.AVERAGE,Comparator.comparing(IPL -> IPL.average));
       this.sortMap.put(SortField.STRIKE_RATE,Comparator.comparing(IPL -> IPL.SR));
       this.sortMap.put(SortField.MAX6SAND4S,Comparator.comparing(IPL -> IPL.fourS+IPL.sixS));
       this.sortMap.put(SortField.RUNS,Comparator.comparing(IPL -> IPL.Runs));
      }

    public int loadIPLRunsCSVData(String csvFilePath) throws CricketAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {

            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLRunsCSV> IPLCSVIterator = csvBuilder.getCSVIterator(reader, IPLRunsCSV.class);
            while (IPLCSVIterator.hasNext()) {
                IPLRunsCSV iplRunsCSV=IPLCSVIterator.next();
                this.IPLMap.put(iplRunsCSV.player,new IPLCSVDTO(iplRunsCSV));
            }
            IPLCSVList=IPLMap.values().stream().collect(Collectors.toList());
            return IPLCSVList.size();
        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),
                    CricketAnalyserException.ExceptionType.IPL_CENSUS_FILE_PROBLEM);
        }
    }
    public int loadIPLWicketsCSVData(String csvFilePath) throws CricketAnalyserException {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {

            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLWicketsCSV> IPLCSVIterator = csvBuilder.getCSVIterator(reader, IPLWicketsCSV.class);
            while (IPLCSVIterator.hasNext()) {
                IPLWicketsCSV iplWicketsCSV=IPLCSVIterator.next();
                this.IPLMap.put(iplWicketsCSV.player,new IPLCSVDTO(iplWicketsCSV));
            }
            IPLCSVList=IPLMap.values().stream().collect(Collectors.toList());
            return IPLCSVList.size();
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

      //  sort(this.sortMap.get(sortField));
        this.sort(this.sortMap.get(sortField));
        Collections.reverse(IPLCSVList);
        String sortedRunsData=new Gson().toJson(IPLCSVList);
        return sortedRunsData;
    }

    private  void sort(Comparator<IPLCSVDTO> IplCSVComparator) {
        for (int i=0; i<this.IPLCSVList.size()-1; i++){
            for (int j=0; j<this.IPLCSVList.size()-1; j++){
                IPLCSVDTO census1=this.IPLCSVList.get(j);
                IPLCSVDTO census2=this.IPLCSVList.get(j+1);
                if (IplCSVComparator.compare(census1,census2)>0) {
                    this.IPLCSVList.set(j,census2);
                    this.IPLCSVList.set(j+1,census1);
                }
            }
        }
    }
}
