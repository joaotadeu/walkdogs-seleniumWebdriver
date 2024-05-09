package br.com.joaotadeu.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/br/com/joaotadeu/features/Login_qax.feature",
        glue = "br.com.joaotadeu.steps.steps_login_qax",
        plugin = {"pretty", "html: logs/report-html", "json: logs/report.json"},
        tags = "@login_qax"
)
public class Runner {

}