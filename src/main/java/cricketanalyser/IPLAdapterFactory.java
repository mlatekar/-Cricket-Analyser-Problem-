package cricketanalyser;

import java.util.Map;

public class IPLAdapterFactory {

    public static Map<String, IPLCSVDTO> getCensusData(CricketAnalyser.IPLCsvFile csvFile, String... csvFilePath) {
        if (csvFile.equals(CricketAnalyser.IPLCsvFile.IPLRuns)) {
            return new IPLRunsAdapter().loadCensusData(csvFilePath);
        }
        if (csvFile.equals(CricketAnalyser.IPLCsvFile.IPLWicket)) {
            return new IPLWicketsAdapter().loadCensusData(csvFilePath);
        }
        if(csvFile.equals(CricketAnalyser.IPLCsvFile.AllRounderPlayer)){
            return new AllRounder().loadCensusData(csvFilePath);
        }
        throw new CricketAnalyserException("Incorrect File name",
                CricketAnalyserException.ExceptionType.IPL_CENSUS_FILE_NOT_FOUND);
    }
}
