package cricketanalyser;

import java.util.Comparator;

public class Comparator4sAnd6s implements Comparator<IPLCSVDTO> {

    @Override
    public int compare(IPLCSVDTO p1, IPLCSVDTO p2) {
        int result = (p1.sixS+p1.fourS)-(p2.sixS+p2.fourS);
        return result;
    }
}
