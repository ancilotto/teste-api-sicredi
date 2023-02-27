package builders;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:java/features/alterarSimulacao.feature", tags = "@alterarSimulacaoGeral",
        glue = "test.java.steps", monochrome = true, dryRun = false)
public class AlterarSimulacaoRunner {}