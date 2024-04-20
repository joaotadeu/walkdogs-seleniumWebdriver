package br.sp.joaotadeu.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/br/com/joaotadeu/features/Cadastro_parceiro_walkdog.feature",
        glue = "br.com.joaotadeu.steps",
        plugin = {"pretty", "html: target/report-html", "json: target/report.json"},
        tags = "cadastro_com_sucesso"
)
public class Runner {

}