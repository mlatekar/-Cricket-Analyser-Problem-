package cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class IPLCSVDTO {

    public double average;
    public  double SR;
    public  int fourS;
    public  int sixS;
    public  int Runs;
    public String player;
    public double econ;

    public IPLCSVDTO(IPLRunsCSV iplRunsCSV) {

        player=iplRunsCSV.player;
        average = iplRunsCSV.average;
        SR = iplRunsCSV.SR;
        fourS = iplRunsCSV.fourS;
        sixS = iplRunsCSV.sixS;
        Runs = iplRunsCSV.Runs;
    }
    public IPLCSVDTO(IPLWicketsCSV iplWicketsCSV) {

        player=iplWicketsCSV.player;
        average = iplWicketsCSV.average;
        SR = iplWicketsCSV.SR;
        econ = iplWicketsCSV.econ;
    }
}
