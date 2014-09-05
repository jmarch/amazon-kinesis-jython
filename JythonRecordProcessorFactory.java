import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessor;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessorFactory;

/**
 * Used to create new record processors.
 */
public class JythonRecordProcessorFactory implements IRecordProcessorFactory {
    private String pythonModule;
    
    /**
     * Constructor.
     */
    public JythonRecordProcessorFactory(String pythonModule) {
        super();
        this.pythonModule = pythonModule;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IRecordProcessor createProcessor() {
        return new JythonRecordProcessor(this.pythonModule);
    }
}
