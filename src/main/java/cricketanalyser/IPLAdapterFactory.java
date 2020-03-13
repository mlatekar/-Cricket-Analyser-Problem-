package cricketanalyser;

import java.util.Map;

public class IPLAdapterFactory {

    public static Map<String, IPLCSVDTO> getIPLData(CricketAnalyser.IPLCsvFile csvFile, String... csvFilePath) {
        if (csvFile.equals(CricketAnalyser.IPLCsvFile.IPLRuns)) {
            return new IPLRunsAdapter().loadIPLData(csvFilePath);
        }
        if (csvFile.equals(CricketAnalyser.IPLCsvFile.IPLWicket)) {
            return new IPLWicketsAdapter().loadIPLData(csvFilePath);
        }
       /* if (csvFile.equals(CricketAnalyser.IPLCsvFile.AllRounderPlayer)) {
            return new AllRounder().loadIPLData(csvFilePath);
        }
        if (csvFile.equals(CricketAnalyser.IPLCsvFile.AllRounder)) {
            return new BestAllRounder().loadIPLData(csvFilePath);
        }*/
        throw new CricketAnalyserException("Incorrect File name",
                CricketAnalyserException.ExceptionType.WRONG_FIle);
    }
}
