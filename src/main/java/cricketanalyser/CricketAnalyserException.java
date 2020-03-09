package cricketanalyser;

public class CricketAnalyserException extends RuntimeException {
    enum ExceptionType {
        NO_CENSUS_DATA, IPL_CENSUS_FILE_NOT_FOUND, IPL_CENSUS_FILE_PROBLEM
    }
    ExceptionType type;
    public CricketAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type=type;
    }
    public CricketAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
