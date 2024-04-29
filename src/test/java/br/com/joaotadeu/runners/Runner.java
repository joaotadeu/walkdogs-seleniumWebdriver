package br.com.joaotadeu.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/br/com/joaotadeu/features/Walkdog.feature",
        glue = "br.com.joaotadeu.steps",
        plugin = {"pretty", "html: logs/report-html", "json: logs/report.json"},
        tags = "@cadastro_com_sucesso"
)
public class Runner {

}