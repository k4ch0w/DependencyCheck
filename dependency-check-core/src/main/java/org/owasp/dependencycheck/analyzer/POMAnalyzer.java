package org.owasp.dependencycheck.analyzer;

import org.apache.commons.io.FileUtils;
import org.owasp.dependencycheck.Engine;
import org.owasp.dependencycheck.analyzer.exception.AnalysisException;
import org.owasp.dependencycheck.dependency.Confidence;
import org.owasp.dependencycheck.dependency.Dependency;
import org.owasp.dependencycheck.dependency.EvidenceCollection;
import org.owasp.dependencycheck.exception.InitializationException;
import org.owasp.dependencycheck.utils.FileFilterBuilder;
import org.owasp.dependencycheck.utils.Settings;
import org.owasp.dependencycheck.xml.pom.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import static org.owasp.dependencycheck.xml.pom.PomUtils.analyzePOM;
import static org.owasp.dependencycheck.xml.pom.PomUtils.readPom;

/**
 * Created by k4ch0w on 3/22/17.
 */
public class POMAnalyzer extends AbstractFileTypeAnalyzer{

    private static final Logger LOGGER = LoggerFactory.getLogger(POMAnalyzer.class);

    private static final FileFilter FILTER = FileFilterBuilder.newInstance()
            .addFilenames("pom.xml").build();

    @Override
    public String getName() {
        return "POM Analyzer";
    }

    @Override
    public AnalysisPhase getAnalysisPhase() {
        return AnalysisPhase.INFORMATION_COLLECTION;
    }

    @Override
    protected String getAnalyzerEnabledSettingKey() {
        return Settings.KEYS.ANALYZER_POM_ENABLED;
    }

    @Override
    protected void analyzeDependency(Dependency dependency, Engine engine) throws AnalysisException {
        final File file = dependency.getActualFile();
        System.out.println("yo");
        analyzePOM(dependency,file);
        Model mod = readPom(file);
        LOGGER.debug(mod.getProjectURL());

    }

    @Override
    protected FileFilter getFileFilter() {
        return FILTER;
    }

    @Override
    protected void initializeFileTypeAnalyzer() throws InitializationException {
 //NA
    }

    private void getSecurityIssuesGithub(){
        /*try (JsonReader jsonReader = Json.createReader(FileUtils.openInputStream(file))) {
            final JsonObject json = jsonReader.readObject();
            final EvidenceCollection productEvidence = dependency.getProductEvidence();
            final EvidenceCollection vendorEvidence = dependency.getVendorEvidence();
            if (json.containsKey("name")) {
                final Object value = json.get("name");
                if (value instanceof JsonString) {
                    final String valueString = ((JsonString) value).getString();
                    productEvidence.addEvidence(PACKAGE_JSON, "name", valueString, Confidence.HIGHEST);
                    vendorEvidence.addEvidence(PACKAGE_JSON, "name_project", String.format("%s_project", valueString), Confidence.LOW);
                } else {
                    LOGGER.warn("JSON value not string as expected: {}", value);
                }
            }
            addToEvidence(json, productEvidence, "description");
            addToEvidence(json, vendorEvidence, "author");
            addToEvidence(json, dependency.getVersionEvidence(), "version");
            dependency.setDisplayFileName(String.format("%s/%s", file.getParentFile().getName(), file.getName()));
        } catch (JsonException e) {
            LOGGER.warn("Failed to parse package.json file.", e);
        } catch (IOException e) {
            throw new AnalysisException("Problem occurred while reading dependency file.", e);
        }
        private void sendGet() throws Exception {

		String url = "http://www.google.com/search?q=mkyong";

        String url = "GET /repos/:owner/:repo/issues"
        ///https://api.github.com/repos/k4ch0w/PwnDelorean/issues
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
        */
    }
}
