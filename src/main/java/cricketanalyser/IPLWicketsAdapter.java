package cricketanalyser;

import java.util.Map;

public class IPLWicketsAdapter extends IPLAdapter {

    @Override
    public Map<String, IPLCSVDTO> loadIPLData(String... csvFilePath) {
        Map<String, IPLCSVDTO> IPLMap = super.loadIPLData(IPLWicketsCSV.class, csvFilePath[0]);
        return IPLMap;
    }

}
