package cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class IPLRunsCSV {

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double sr;

    @CsvBindByName(column = "4s", required = true)
    public int fourS;

    @CsvBindByName(column = "6s", required = true)
    public int sixS;

}
