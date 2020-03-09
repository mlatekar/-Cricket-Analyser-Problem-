package cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class IPLWicketsCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double SR;

    @CsvBindByName(column = "Econ", required = true)
    public double econ;

    @CsvBindByName(column = "4w", required = true)
    public int fourW;

    @CsvBindByName(column = "5w", required = true)
    public int fiveW;

}
