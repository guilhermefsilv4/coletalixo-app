package br.com.fiap.coletalixo.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"br.com.fiap.coletalixo.steps", "br.com.fiap.coletalixo.config"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/report.json"
        },
        tags = "@auth or @agendamento or @admin or @entregas"
)
public class TestRunner {
} 