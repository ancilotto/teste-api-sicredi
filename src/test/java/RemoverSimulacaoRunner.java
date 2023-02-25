import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:java/features/removerSimulacao.feature", tags = "@Teste",
        glue = "test.java.steps", monochrome = true, dryRun = false)
public class RemoverSimulacaoRunner {}
