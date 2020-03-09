package cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class IPLWicketsCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double SR;
}
