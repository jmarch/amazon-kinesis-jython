/**
 * A simple interface for a python-based processor that handles 'data' from Kinesis Records.
 */
public interface IPythonProcessor {
    public String process(String data);
}

