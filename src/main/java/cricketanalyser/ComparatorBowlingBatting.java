package cricketanalyser;

import java.util.Comparator;

public class ComparatorBowlingBatting implements Comparator<IPLCSVDTO> {

    @Override
    public int compare(IPLCSVDTO p1, IPLCSVDTO p2) {
        int result = (int) ((int) (p1.battingaverage + p1.bowlingaverage) - (p2.battingaverage + p2.bowlingaverage));
        return result;
    }

}
