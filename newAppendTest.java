
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;

public class newAppendTest
{
    public static void main(String[] args) throws IOException {
        try {
        Writer output;
        output = new BufferedWriter(new FileWriter("AppendTest.txt", true));
        output.append("This is a test! sdfsdfsdf 1");
        output.close();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}
