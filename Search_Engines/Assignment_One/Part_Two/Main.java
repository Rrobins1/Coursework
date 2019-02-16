import index.*;

import java.io.IOException;
import java.util.LinkedList;

public class Main {
  private static final String DEFAULT_PATH = "files/documents.txt";
  private static final String DEFAULT_WRITE_PATH = "files/index.txt";
  public static void main(String[] args) throws IOException {
    String readPath = DEFAULT_PATH;
    String writePath = DEFAULT_WRITE_PATH;
    if (args.length == 2) {
      readPath = args[0];
      writePath = args[1];
    }

    Index index = new Index();

    index = IndexDriver.processFile(readPath);
    System.out.println(index);
    IndexLogger.writeToFile(index, writePath);
  }
}
