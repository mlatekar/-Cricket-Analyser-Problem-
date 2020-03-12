package cricketanalyser;

import java.util.HashMap;

import java.util.Map;


public class IPLRunsAdapter extends IPLAdapter {
    Map<String, IPLCSVDTO> IPLMap = new HashMap<>();

    @Override
    public Map<String, IPLCSVDTO> loadCensusData(String... csvFilePath) {
        Map<String, IPLCSVDTO> IPLMap=super.loadCensusData(IPLRunsCSV.class,csvFilePath[0]);
       // if (csvFilePath.length > 1)
       // this.loadBowlingData(IPLMap,csvFilePath[1]);
        return IPLMap;
    }

}

