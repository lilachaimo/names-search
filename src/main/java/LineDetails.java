public class LineDetails {

    private final String line;
    private  final int lineIndex;

    public LineDetails(String line, int lineIndex) {
        this.line = line;
        this.lineIndex = lineIndex;
    }

    public String getLine() {
        return line;
    }

    public int getLineIndex() {
        return lineIndex;
    }
}
