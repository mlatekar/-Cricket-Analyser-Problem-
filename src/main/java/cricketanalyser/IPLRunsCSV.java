package cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class IPLRunsCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double SR;

    @CsvBindByName(column = "4s", required = true)
    public int fourS;

    @CsvBindByName(column = "6s", required = true)
    public int sixS;

    @CsvBindByName(column = "Runs", required = true)
    public int Runs;

}
