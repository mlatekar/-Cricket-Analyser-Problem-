package cricketanalyser;

import java.util.Map;

public class IPLRunsAdapter extends IPLAdapter {

    @Override
    public Map<String, IPLCSVDTO> loadCensusData(String... csvFilePath) {
        Map<String, IPLCSVDTO> IPLMap=super.loadCensusData(IPLRunsCSV.class,csvFilePath[0]);
        return IPLMap;

    }
}
