package cricketanalyser;

import java.util.Map;

public class IPLWicketsAdapter extends IPLAdapter {

    @Override
    public Map<String, IPLCSVDTO> loadCensusData(String... csvFilePath) {
        Map<String, IPLCSVDTO> IPLMap=super.loadCensusData(IPLWicketsCSV.class,csvFilePath[0]);
        return IPLMap;

    }

}
