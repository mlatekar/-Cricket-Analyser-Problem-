package cricketanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketAnalyserTest {

    CricketAnalyser cricketAnalyser = new CricketAnalyser();
    private static final String IPL_RUNS_CENSUS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_WICKETS_CENSUS_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";


    @Test
    public void givenIPLRunsCSVFileReturnsCorrectRecords() {
        try {
            int numOfRecords = cricketAnalyser.loadCensusData(CricketAnalyser.IPLCsvFile.IPLRuns, IPL_RUNS_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
            System.out.println(numOfRecords);
        } catch (CricketAnalyserException e) {
        }
    }

    @Test
    public void givenIPLRunsCSVFile_ShouldReturns_MostRuns() {
        try {
            cricketAnalyser.loadCensusData(CricketAnalyser.IPLCsvFile.IPLRuns, IPL_RUNS_CENSUS_CSV_FILE_PATH);
            String sortedRunsData = cricketAnalyser.getSortedData(SortField.AVERAGE);
            IPLRunsCSV[] mostRuns = new Gson().fromJson(sortedRunsData, IPLRunsCSV[].class);
            Assert.assertEquals("MS Dhoni", mostRuns[0].player);
        } catch (CricketAnalyserException e) {
        }
    }

    @Test
    public void givenIPLRunsCSVFile_ShouldReturn_TopStrikeRate() {
        cricketAnalyser.loadCensusData(CricketAnalyser.IPLCsvFile.IPLRuns, IPL_RUNS_CENSUS_CSV_FILE_PATH);
        String sortedStrikeRate = cricketAnalyser.getSortedData(SortField.STRIKE_RATE);
        IPLRunsCSV[] topStrikeRate = new Gson().fromJson(sortedStrikeRate, IPLRunsCSV[].class);
        Assert.assertEquals("Ishant Sharma", topStrikeRate[0].player);
    }

    @Test
    public void givenIPLRunsCsvFile_ShouldReturn_WhoHits_Maximum4sAnd6s() {
        cricketAnalyser.loadCensusData(CricketAnalyser.IPLCsvFile.IPLRuns, IPL_RUNS_CENSUS_CSV_FILE_PATH);
        String sortMax4sAnd6s = cricketAnalyser.getSortedData(SortField.TOTALSIXANDFOR);
        IPLRunsCSV[] topMax4sAnd6s = new Gson().fromJson(sortMax4sAnd6s, IPLRunsCSV[].class);
        Assert.assertEquals("Andre Russell", topMax4sAnd6s[0].player);
    }

    @Test
    public void givenIPLRunCsvFile_WhenSorted_ShouldReturn_TopStrikeRate() {
        cricketAnalyser.loadCensusData(CricketAnalyser.IPLCsvFile.IPLRuns, IPL_RUNS_CENSUS_CSV_FILE_PATH);
        String sortStrikeRateBy4sAnd6s = cricketAnalyser.getSortedData(SortField.MAX6SAND4S);
        IPLRunsCSV[] topStrikeRateBy4sAnd6s = new Gson().fromJson(sortStrikeRateBy4sAnd6s, IPLRunsCSV[].class);
        Assert.assertEquals("Andre Russell", topStrikeRateBy4sAnd6s[0].player);
    }

    @Test
    public void givenMostRunsData_WhenSorted_ShouldReturn_TopAveragesWithStrikeRates() {
        cricketAnalyser.loadCensusData(CricketAnalyser.IPLCsvFile.IPLRuns, IPL_RUNS_CENSUS_CSV_FILE_PATH);
        String sortAverageWithStrikesRates = cricketAnalyser.getSortedData(SortField.AVERAGEWITHSTRKIERATE);
        IPLRunsCSV[] topAverageWithStrikesRates = new Gson().fromJson(sortAverageWithStrikesRates, IPLRunsCSV[].class);
        Assert.assertEquals("MS Dhoni", topAverageWithStrikesRates[0].player);
    }

    @Test
    public void givenMostRunsData_WhenSorted_ShouldReturn_MaximumRunsWithBestAverage() {
        cricketAnalyser.loadCensusData(CricketAnalyser.IPLCsvFile.IPLRuns, IPL_RUNS_CENSUS_CSV_FILE_PATH);
        String sortAverageWithStrikesRates = cricketAnalyser.getSortedData(SortField.RUNS);
        IPLRunsCSV[] topAverageWithStrikesRates = new Gson().fromJson(sortAverageWithStrikesRates, IPLRunsCSV[].class);
        Assert.assertEquals("David Warner ", topAverageWithStrikesRates[0].player);
    }

    @Test
    public void givenMostWicketsData_WhenSorted_ShouldReturn_TopBowlingAverage() {
        cricketAnalyser.loadCensusData(CricketAnalyser.IPLCsvFile.IPLWicket, IPL_WICKETS_CENSUS_CSV_FILE_PATH);
        String sortedData = cricketAnalyser.getSortedData(SortField.AVERAGE);
        IPLWicketsCSV[] topBowlingAverage = new Gson().fromJson(sortedData, IPLWicketsCSV[].class);
        Assert.assertEquals("Krishnappa Gowtham", topBowlingAverage[0].player);
    }

    @Test
    public void givenMostWicketsData_WhenSorted_ShouldReturn_TopStrikeRatesOfBowlers() {
        cricketAnalyser.loadCensusData(CricketAnalyser.IPLCsvFile.IPLWicket, IPL_WICKETS_CENSUS_CSV_FILE_PATH);
        String sortedData = cricketAnalyser.getSortedData(SortField.STRIKE_RATE);
        IPLWicketsCSV[] topBowlingAverage = new Gson().fromJson(sortedData, IPLWicketsCSV[].class);
        Assert.assertEquals("Krishnappa Gowtham", topBowlingAverage[0].player);
    }

    @Test
    public void givenMostWicketsData_WhenSorted_ShouldReturn_BestEconomyRate() {
        cricketAnalyser.loadCensusData(CricketAnalyser.IPLCsvFile.IPLWicket, IPL_WICKETS_CENSUS_CSV_FILE_PATH);
        String sortedData = cricketAnalyser.getSortedData(SortField.EconomyRate);
        IPLWicketsCSV[] topBowlingAverage = new Gson().fromJson(sortedData, IPLWicketsCSV[].class);
        Assert.assertEquals("Ben Cutting", topBowlingAverage[0].player);
    }

    @Test
    public void givenMostWicketsData_WhenSorted_ShouldReturn_BestStrikeRater_With4wAnd5w() {
        cricketAnalyser.loadCensusData(CricketAnalyser.IPLCsvFile.IPLWicket, IPL_WICKETS_CENSUS_CSV_FILE_PATH);
        String sortedData = cricketAnalyser.getSortedData(SortField.TOTAL4W5W);
        IPLWicketsCSV[] topBowlingAverage = new Gson().fromJson(sortedData, IPLWicketsCSV[].class);
        Assert.assertEquals("Lasith Malinga", topBowlingAverage[0].player);
    }

    @Test
    public void givenMostWicketsData_WhenSorted_ShouldReturn_GreatBowlingAverage_BestStrikeRate() {
        cricketAnalyser.loadCensusData(CricketAnalyser.IPLCsvFile.IPLWicket, IPL_WICKETS_CENSUS_CSV_FILE_PATH);
        String sortedData = cricketAnalyser.getSortedData(SortField.BESTAVERAGE);
        IPLWicketsCSV[] topBowlingAverage = new Gson().fromJson(sortedData, IPLWicketsCSV[].class);
        Assert.assertEquals("Krishnappa Gowtham", topBowlingAverage[0].player);
    }

    @Test
    public void givenMostWicketsData_WhenSorted_ShouldReturn_MaximumWickets_withBestAverage() {
        cricketAnalyser.loadCensusData(CricketAnalyser.IPLCsvFile.IPLWicket, IPL_WICKETS_CENSUS_CSV_FILE_PATH);
        String sortedData = cricketAnalyser.getSortedData(SortField.MAXIMUMWICKETWITHAVERAGE);
        IPLWicketsCSV[] topBowlingAverage = new Gson().fromJson(sortedData, IPLWicketsCSV[].class);
        Assert.assertEquals("Imran Tahir", topBowlingAverage[0].player);
    }

    @Test
    public void givenIPLData_RunsAndWickets_WhenSorted_ShouldReturn_BestBowlingAnd_BestBattingAverage() {
        cricketAnalyser.loadCensusData(CricketAnalyser.IPLCsvFile.AllRounderPlayer, IPL_RUNS_CENSUS_CSV_FILE_PATH, IPL_WICKETS_CENSUS_CSV_FILE_PATH);
        String runsAverage = cricketAnalyser.getSortedData(SortField.MAX_AVERAGE);
        System.out.println(runsAverage);
        IPLCSVDTO[] iplRunsCSV = new Gson().fromJson(runsAverage, IPLCSVDTO[].class);
        Assert.assertEquals("Marcus Stoinis", iplRunsCSV[0].player);
    }

    @Test
    public void givenIPLData_RunsAndWickets_WhenSorted_ShouldReturn_BestBowlingAnd_BestAllRounderPlayer() {
        cricketAnalyser.loadCensusData(CricketAnalyser.IPLCsvFile.AllRounder, IPL_RUNS_CENSUS_CSV_FILE_PATH, IPL_WICKETS_CENSUS_CSV_FILE_PATH);
        String runsAverage = cricketAnalyser.getSortedData(SortField.ALLROUNDER);
        System.out.println(runsAverage);
        IPLCSVDTO[] iplRunsCSV = new Gson().fromJson(runsAverage, IPLCSVDTO[].class);
        Assert.assertEquals("Hardik Pandya", iplRunsCSV[0].player);
    }

}






