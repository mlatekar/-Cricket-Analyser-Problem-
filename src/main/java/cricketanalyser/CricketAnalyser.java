package cricketanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;


public class CricketAnalyser {
    List<IPLCSVDTO> IPLCSVList = null;
    Map<String, IPLCSVDTO> IPLMap = null;
    Map<SortField, Comparator<IPLCSVDTO>> sortMap = null;

    public enum IPLCsvFile {IPLRuns, IPLWicket, AllRounderPlayer,AllRounder};

    public CricketAnalyser() {
        IPLMap = new HashMap<>();
        IPLCSVList = new ArrayList<>();
        sortMap = new HashMap<>();
        this.sortMap.put(SortField.AVERAGE, Comparator.comparing(IPL -> IPL.average));
        this.sortMap.put(SortField.AVERAGEWITHSTRKIERATE, Comparator.comparing(IPL -> IPL.average));

        this.sortMap.put(SortField.STRIKE_RATE, Comparator.comparing(IPL -> IPL.SR));

        this.sortMap.put(SortField.MAX6SAND4S, Comparator.comparing(IPL -> IPL.fourS + IPL.sixS));
        Comparator<IPLCSVDTO> comparator = Comparator.comparing(IPL -> IPL.fourS + IPL.sixS);
        this.sortMap.put(SortField.TOTALSIXANDFOR, comparator.thenComparing(IPL -> IPL.fourS + IPL.sixS));


        this.sortMap.put(SortField.RUNS, Comparator.comparing(IPL -> IPL.Runs));
        this.sortMap.put(SortField.EconomyRate, Comparator.comparing(IPL -> IPL.econ));


        this.sortMap.put(SortField.BESTSTRIKE, Comparator.comparing(IPL -> IPL.fourW + IPL.fiveW));
        Comparator<IPLCSVDTO> bestStrikeRateWith4w5w = Comparator.comparing(IPL -> IPL.fourW + IPL.fiveW);
        this.sortMap.put(SortField.TOTAL4W5W, bestStrikeRateWith4w5w.thenComparing(IPL -> IPL.SR));

        this.sortMap.put(SortField.BESTAVERAGE, Comparator.comparing(IPL -> IPL.average));
        this.sortMap.put(SortField.MAXIMUMWICKETWITHAVERAGE, Comparator.comparing(IPL -> IPL.wickets));

        this.sortMap.put(SortField.MAX_AVERAGE, new ComparatorBowlingBatting());
        this.sortMap.put(SortField.ALLROUNDER, new ComparatorBestAllRounder());
    }

    public int loadCensusData(IPLCsvFile csvFile, String... csvFilePath) throws CricketAnalyserException {
        IPLMap = IPLAdapterFactory.getCensusData(csvFile, csvFilePath);
        return IPLMap.size();
    }

    public String getSortedData(SortField sortField) {
        IPLCSVList = IPLMap.values().stream().collect(Collectors.toList());
        if (IPLCSVList == null || IPLCSVList.size() == 0) {
            throw new CricketAnalyserException("No Census Data", CricketAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        //  sort(this.sortMap.get(sortField));
        this.sort(sortMap.get(sortField));
        Collections.reverse(IPLCSVList);
        String sortedRunsData = new Gson().toJson(IPLCSVList);
        return sortedRunsData;
    }

    private void sort(Comparator<IPLCSVDTO> IplCSVComparator) {
        for (int i = 0; i < this.IPLCSVList.size() - 1; i++) {
            for (int j = 0; j < this.IPLCSVList.size() - i - 1; j++) {
                IPLCSVDTO census1 = this.IPLCSVList.get(j);
                IPLCSVDTO census2 = this.IPLCSVList.get(j + 1);
                if (IplCSVComparator.compare(census1, census2) > 0) {
                    this.IPLCSVList.set(j, census2);
                    this.IPLCSVList.set(j + 1, census1);
                }
            }
        }
    }
}
