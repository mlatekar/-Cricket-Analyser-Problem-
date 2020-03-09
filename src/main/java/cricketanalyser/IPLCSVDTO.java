package cricketanalyser;

import com.opencsv.bean.CsvBindByName;

public class IPLCSVDTO {

    public double average;
    public  double SR;
    public  int fourS;
    public  int sixS;
    public  int Runs;
    public String PLAYER;

    public IPLCSVDTO(IPLRunsCSV iplRunsCSV) {

        PLAYER=iplRunsCSV.player;
        average = iplRunsCSV.average;
        SR = iplRunsCSV.SR;
        fourS = iplRunsCSV.fourS;
        sixS = iplRunsCSV.sixS;
        Runs = iplRunsCSV.Runs;
    }
    public IPLCSVDTO(IPLWicketsCSV iplWicketsCSV) {

        PLAYER=iplWicketsCSV.player;
        average = iplWicketsCSV.average;
        SR = iplWicketsCSV.SR;
    }
}
