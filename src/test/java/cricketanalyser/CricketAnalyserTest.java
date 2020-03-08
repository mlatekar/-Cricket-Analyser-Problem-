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
            IPLRunsCSV[] mostRuns = new Gson().fromJson(sortedRunsData, IPLRunsCSV[].class);
            Assert.assertEquals(83.2, mostRuns[0].average, 0.0d);
            System.out.println(mostRuns);
        } catch (CricketAnalyserException e) {
        }
    }

    @Test
    public void givenIPLRunsCSVFile_ShouldReturn_TopStrikeRate() {
        cricketAnalyser.loadIPLRunsCSVData(IPL_RUNS_CENSUS_CSV_FILE_PATH);
        String sortedStrikeRate = cricketAnalyser.getSortedData(SortField.STRIKE_RATE);
        IPLRunsCSV[] topStrikeRate = new Gson().fromJson(sortedStrikeRate, IPLRunsCSV[].class);
        Assert.assertEquals(333.33, topStrikeRate[0].sr, 0.0d);
    }

    @Test
    public void givenIPLRunsCsvFile_ShouldReturn_WhoHits_Maximum4sAnd6s() {
        cricketAnalyser.loadIPLRunsCSVData(IPL_RUNS_CENSUS_CSV_FILE_PATH);
        String sortMax4sAnd6s = cricketAnalyser.getSortedData(SortField.MAX6SAND4S);
        IPLRunsCSV[] topMax4sAnd6s = new Gson().fromJson(sortMax4sAnd6s, IPLRunsCSV[].class);
        Assert.assertEquals(83, topMax4sAnd6s[0].fourS + topMax4sAnd6s[0].sixS);
    }

    @Test
    public void givenIPLRunnCsvFile_WhenSorted_ShouldReturn_TopStrikeRate() {
        cricketAnalyser.loadIPLRunsCSVData(IPL_RUNS_CENSUS_CSV_FILE_PATH);
        String sortStrikeRateBy4sAnd6s = cricketAnalyser.getSortedData(SortField.MAX6SAND4S);
        IPLRunsCSV[] topStrikeRateBy4sAnd6s = new Gson().fromJson(sortStrikeRateBy4sAnd6s, IPLRunsCSV[].class);
        Assert.assertEquals(204.81,topStrikeRateBy4sAnd6s[0].sr,0.0d);
    }

    @Test
    public void givenMostRunsData_WhenSorted_ShouldReturn_TopAveragesWithStrikeRates() {
        cricketAnalyser.loadIPLRunsCSVData(IPL_RUNS_CENSUS_CSV_FILE_PATH);
        String sortAverageWithStrikesRates=cricketAnalyser.getSortedData(SortField.AVERAGE);
        IPLRunsCSV[] topAverageWithStrikesRates=new Gson().fromJson(sortAverageWithStrikesRates,IPLRunsCSV[].class);
        Assert.assertEquals(134.62,topAverageWithStrikesRates[0].sr,0.0);
    }

    @Test
    public void givenMostRunsData_WhenSorted_ShouldReturn_MaximumRunsWithBestAverage() {
        cricketAnalyser.loadIPLRunsCSVData(IPL_RUNS_CENSUS_CSV_FILE_PATH);
        String sortAverageWithStrikesRates=cricketAnalyser.getSortedData(SortField.AVERAGE);
        IPLRunsCSV[] topAverageWithStrikesRates=new Gson().fromJson(sortAverageWithStrikesRates,IPLRunsCSV[].class);
        Assert.assertEquals(416.0,topAverageWithStrikesRates[0].Runs,0.00d);
    }

}






