package cricketanalyser;

public class CricketAnalyserException extends RuntimeException {
    enum ExceptionType {
        WRONG_FIle, NO_IPL_DATA, IPL_FILE_PROBLEM
    }

    ExceptionType type;

    public CricketAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CricketAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
