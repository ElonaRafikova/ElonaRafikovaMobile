package setup;

import enums.PropertiesFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class TestProperties {

    private Properties currentProps = new Properties();
    private String propertiesFile;

    //set needed type of properties for web or native
    protected void setPropertiesFile(PropertiesFile file) {
        propertiesFile = file.getType();
    }

    private Properties getCurrentProps() throws IOException {

        File properties = new File(propertiesFile);
        FileInputStream in = new FileInputStream(properties.getAbsolutePath());
        currentProps.load(in);
        in.close();
        return currentProps;
    }

    protected String getProp(String propKey) throws IOException {
        if (!currentProps.containsKey(propKey))
            currentProps = getCurrentProps();
        // "default" form used to handle the absence of parameter
        return currentProps.getProperty(propKey, null);
    }
}
