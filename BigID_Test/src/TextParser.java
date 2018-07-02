import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TextParser {

    public static final int MAX_AMOUNT_OF_THREADS = 5;
    public static final int LINES_TO_READ = 1000;

    private Aggregator aggregator;
    private Scanner scanner;
    private final String[] wordsToFind;

    public TextParser(URL url, String[] wordsToFind) throws Exception {
        this.aggregator = new Aggregator(wordsToFind);
        this.scanner = new Scanner(url.openStream());
        this.wordsToFind = wordsToFind;
    }

    public void runWordFinder() {
        StringBuffer stringBuffer;
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(MAX_AMOUNT_OF_THREADS);
        int bulkCounter = 0;

        while (scanner.hasNext()) {
            stringBuffer = buildStringBuffer();
            Matcher m = new Matcher(stringBuffer, bulkCounter * LINES_TO_READ, wordsToFind);
            bulkCounter++;
            executor.execute(m);
            aggregator.AddMatcher(m);
        }

        executor.shutdown();
        aggregator.populateMatchersArray();
        System.out.println(aggregator);
    }

    private StringBuffer buildStringBuffer () {
        String currentLine;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < LINES_TO_READ; i++) {
            if (!scanner.hasNext())
                break;
            currentLine = scanner.nextLine();
            stringBuffer.append(currentLine);
        }
        return stringBuffer;
    }
}