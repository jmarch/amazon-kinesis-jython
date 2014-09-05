/**
 * A factory for instantiating and returning python-based processors that implement IPythonProcessor
 */
import java.util.Properties;
import org.python.util.PythonInterpreter;


public class IPythonProcessorFactory {

    private static String PYTHONPATH = "/Library/Python/2.7/site-packages";

    private static IPythonProcessorFactory instance = null;
    
    public synchronized static IPythonProcessorFactory getInstance() {
        if (instance == null) {
            instance = new IPythonProcessorFactory();
        }
        return instance;
    }
    
    public static Object getPythonObject(String pythonProcessorPath) {
        Object processor = null;

        Properties props = new Properties();
        props.setProperty("python.path", PYTHONPATH);
        PythonInterpreter.initialize(System.getProperties(), props, new String[] {""});

        PythonInterpreter interpreter = new PythonInterpreter();

        interpreter.execfile(pythonProcessorPath);

        String tempName = pythonProcessorPath.substring(pythonProcessorPath.lastIndexOf("/")+1);
        tempName = tempName.substring(0, tempName.indexOf("."));

        String instanceName = tempName.toLowerCase();
        String javaClassName = tempName.substring(0,1).toUpperCase() + tempName.substring(1);

        interpreter.exec(instanceName + " = " + javaClassName + "()");

        try {
             Class JavaInterface = Class.forName("IPythonProcessor");
             processor = interpreter.get(instanceName).__tojava__(JavaInterface);
         } catch (ClassNotFoundException ex) {
             ex.printStackTrace();
         }

        return processor;
    }

}
