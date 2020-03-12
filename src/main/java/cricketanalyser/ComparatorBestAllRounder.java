package cricketanalyser;

import java.util.Comparator;

public class ComparatorBestAllRounder implements Comparator<IPLCSVDTO> {
    @Override
    public int compare(IPLCSVDTO p1, IPLCSVDTO p2) {
        int i=0;
        if(p1.wickets!=0) {
            i = (int) ((p1.wickets * p1.Runs)-(p2.wickets*p2.Runs));
        }return i;
    }
}
