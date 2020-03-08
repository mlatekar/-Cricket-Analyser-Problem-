package cricketanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CricketAnalyserTest {

    private static CricketAnalyser cricketAnalyser;
    private static final String IPL_RUNS_CENSUS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @BeforeClass
    public static void beforeClass() {
         cricketAnalyser = new CricketAnalyser();
    }

    @Test
    public void givenIPLRunsCSVFileReturnsCorrectRecords() {
        try {
            int numOfRecords = cricketAnalyser.loadIPLRunsCSVData(IPL_RUNS_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
          System.out.println(numOfRecords);
        } catch (CricketAnalyserException e) {
        }
    }

    @Test
    public void givenIPLRunsCSVFile_ShouldReturns_MostRuns() {
        try {
             cricketAnalyser.loadIPLRunsCSVData(IPL_RUNS_CENSUS_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getSortedData(SortField.AVERAGE);
            IPLRunsCSV[] mostRuns=new Gson().fromJson(sortedRunsData, IPLRunsCSV[].class);
            Assert.assertEquals(83.2, mostRuns[0].average,0.0d);
            System.out.println(mostRuns);
        } catch (CricketAnalyserException e) {
        }
    }

    @Test
    public void givenIPLRunsCSVFile_ShouldReturn_TopStrikeRate() {
        cricketAnalyser.loadIPLRunsCSVData(IPL_RUNS_CENSUS_CSV_FILE_PATH);
        String sortedStrikeRate = cricketAnalyser.getSortedData(SortField.STRIKE_RATE);
        IPLRunsCSV[] topStrikeRate = new Gson().fromJson(sortedStrikeRate, IPLRunsCSV[].class);
        Assert.assertEquals(333.33,topStrikeRate[0].sr,0.0d);
    }
}






