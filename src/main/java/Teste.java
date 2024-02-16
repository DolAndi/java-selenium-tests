//     	System.setProperty("webdriver.chrome.driver", "C:\\Webdrivers\\chromedriver-win64\\chromedriver.exe");
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Teste {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Configura o caminho do ChromeDriver
    	System.setProperty("webdriver.chrome.driver", "C:\\Webdrivers\\chromedriver-win64\\chromedriver.exe");
        // Inicializa o driver do Chrome
        driver = new ChromeDriver();
    } 

    @Test
    public void realizarPesquisas() {
        // Abre a página do Google
        driver.get("http://www.google.com");

        // Um array de termos de pesquisa para nosso teste
        String[] termosDePesquisa = {"Juventude", "Java", "Selenium WebDriver"};

        // Usamos um for-each loop para iterar sobre os termos de pesquisa
        for (String termo : termosDePesquisa) {
            // Encontra o campo de texto de pesquisa pelo nome e envia um termo de pesquisa
            WebElement campoDePesquisa = driver.findElement(By.name("q"));
            campoDePesquisa.clear();
            campoDePesquisa.sendKeys(termo);
            campoDePesquisa.submit(); // Envia o formulário de pesquisa

            // Espera de forma implícita (simples exemplo, em produção usar WebDriverWait)
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Validação: verifica se pelo menos um resultado foi mostrado
            List<WebElement> resultadosDePesquisa = driver.findElements(By.cssSelector("div.g"));
            if (!resultadosDePesquisa.isEmpty()) {
                System.out.println("Resultados encontrados para: " + termo);
            } else {
                System.out.println("Nenhum resultado encontrado para: " + termo);
            }

            // Verifica o título da página usando switch-case
            switch (driver.getTitle()) {
                case "Juventude - Pesquisa Google":
                    System.out.println("Título verificado para Juventude.");
                    break;
                case "Java - Pesquisa Google":
                    System.out.println("Título verificado para Java.");
                    break;
                case "Selenium WebDriver - Pesquisa Google":
                    System.out.println("Título verificado para Selenium WebDriver.");
                    break;
                default:
                    System.out.println("Título não reconhecido.");
                    break;
            }
        }
    }

    @After
    public void tearDown() {
        // Fecha o navegador
        driver.quit();
    }
}

