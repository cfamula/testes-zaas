package org.zowe.apiml.zaasclient.application;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

//import org.json.simple.JSONObject;

import org.zowe.apiml.zaasclient.config.ConfigProperties;
import org.zowe.apiml.zaasclient.service.ZaasClient;
import org.zowe.apiml.zaasclient.service.internal.ZaasClientImpl;
import org.zowe.apiml.zaasclient.exception.*;

public class GetToken {

	private static final String CONFIG_FILE_PATH = "src/main/resources/configFile.properties";
	private String token;
	private String passticket;

	public String login(String username, String password) throws IOException {
		try {
			ZaasClient zaasClient = new ZaasClientImpl(getConfigProperties());
			String zaasClientToken = zaasClient.login(username, password);
			// Use this token in subsequent calls
			return zaasClientToken;
		} catch (ZaasClientException | ZaasConfigurationException exception) {
			exception.printStackTrace();
		}
		return token;
	}
	
	public String passticket(String token, String appid) throws IOException {
        try {
            ZaasClient zaasClient = new ZaasClientImpl(getConfigProperties());
            String zaasPasstkt = zaasClient.passTicket(token, appid);
            //Use this token  in subsequent calls
            return zaasPasstkt;
        } catch (ZaasClientException | ZaasConfigurationException exception) {
        	exception.printStackTrace();
        }
        return passticket;
    }

	private ConfigProperties getConfigProperties() throws IOException {
		// Load the values for configuration properties
		String absoluteFilePath = new File(CONFIG_FILE_PATH).getAbsolutePath();
		ConfigProperties configProperties = new ConfigProperties();
		Properties configProp = new Properties();
		try {
			if (Paths.get(absoluteFilePath).toFile().exists()) {
				configProp.load(new FileReader(absoluteFilePath));

				configProperties.setApimlHost(configProp.getProperty("APIML_HOST"));
				configProperties.setApimlPort(configProp.getProperty("APIML_PORT"));
				configProperties.setApimlBaseUrl(configProp.getProperty("APIML_BASE_URL"));
				configProperties.setKeyStorePath(configProp.getProperty("KEYSTOREPATH"));
				String keyStorePassword = configProp.getProperty("KEYSTOREPASSWORD");
				configProperties.setKeyStorePassword(keyStorePassword == null ? null : keyStorePassword.toCharArray());
				configProperties.setKeyStoreType(configProp.getProperty("KEYSTORETYPE"));
				configProperties.setTrustStorePath(configProp.getProperty("TRUSTSTOREPATH"));
				String trustStorePassword = configProp.getProperty("TRUSTSTOREPASSWORD");
				configProperties
						.setTrustStorePassword(trustStorePassword == null ? null : trustStorePassword.toCharArray());
				configProperties.setTrustStoreType(configProp.getProperty("TRUSTSTORETYPE"));
			}
		} catch (IOException e) {
			throw new IOException();
		}
		return configProperties;
	}

	public GetToken() {
       
    }

	public GetToken(String userid, String passwd) {
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "GetToken [token=" + token + "]";
	}
}
