import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class TestSimularCredito {

    private WebDriver driver;
    private Wait wait;
    private Long tiempoEspera = new Long(20);
    private Long tiempoIntevalo = new Long(1);

    @Before
    public void iniciarSolicitudCredito() {
        /** obtener el webdriver y asignar a propiedades del sistema */
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/windows/chrome/chromedriver.exe");

        /**crear web driver chrome */
        driver = new ChromeDriver();
        /** mazimizar navegador */
        driver.manage().window().maximize();

        /** manejo explicito de esperas de elementos web*/
        wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(tiempoEspera))
                .pollingEvery(Duration.ofSeconds(tiempoIntevalo))
                .ignoring(NoSuchElementException.class);

        /** url de página **/

        String urlPb = "https://www.bancodeoccidente.com.co/solicitarcredito/#/solicitarLibranza";

        String tituloEsperadoPb = "Simulador de crédito en línea | Banco de Occidente";

        String tituloObtenidoPb = "";

        /** iniciar navegador en url pb**/

        driver.get(urlPb);
        tituloObtenidoPb = driver.getTitle();

        /** comparar titulo obtenido con esperado **/
        if (tituloEsperadoPb.equalsIgnoreCase(tituloObtenidoPb)) {
            System.out.println("Titulo esperado igual al obtenido *" + tituloEsperadoPb + "*");
        } else {
            System.out.println("Titulo diferente al esperado *" + tituloEsperadoPb + "* obtenido *" + tituloObtenidoPb + "*");
        }
        assertEquals(tituloEsperadoPb, tituloObtenidoPb);
    }

    @Test
    public void simularCredito() throws Exception {

        /** digitar convenio*/
        /*Thread.sleep(1000);
        WebElement elementConvenio =
                driver.findElement(By.cssSelector("input[id='nombreEmpresaSimulador']"));
        */
        WebElement elementoConvenioFluent = (WebElement) wait.until(
                new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(By.cssSelector("input[id='nombreEmpresaSimulador']"));
                    }
                }
        );
        elementoConvenioFluent.click();
        elementoConvenioFluent.clear();
        Thread.sleep(1000); //este tiempo permite que procese el cambio de busqueda
        elementoConvenioFluent.sendKeys("ATH");

        /** seleccionar  empresa desplegada en la busqueda*/
        //Thread.sleep(10000);
        WebElement elementoNombreConvenioFluent = (WebElement) wait.until(
                new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(By.cssSelector("a.ng-binding"));
                    }
                }
        );
        elementoNombreConvenioFluent.click();
        /*WebElement nombreConvenioElement = new WebDriverWait(driver, Long.parseLong("10"))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[class='ng-binding']")));
        nombreConvenioElement.click();

         */
        /** Monto a solicitar **/

        //driver.findElement(By.cssSelector("input[name='valorSolicitado'][placeholder='Monto a Solicitar']"));
       // usando el wait deprecado
        WebElement valorSolicitadoInput =
                new WebDriverWait(driver, new Long(tiempoEspera))
        .until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input[name='valorSolicitado'][placeholder='Monto a Solicitar']"))));
        valorSolicitadoInput.sendKeys("10000000");

        /** seleccionar 12 Meses en el combo de meses */

        WebElement plazoSelect = obtenerElementoCssSelector("select[name='plazo']");
        //Crear selected
        Select plazoCombo  = new Select(plazoSelect);
        plazoCombo.selectByVisibleText("24 Meses");
        plazoCombo.selectByVisibleText("12 Meses");

        /** dar clic  y digitar los meses en el combo de meses */
        //driver.findElement(By.cssSelector("select[name='plazo']")).click();
        //driver.findElement(By.cssSelector("select[name='plazo']")).sendKeys("12 Meses");

        /** dar clic en check de seguro */
        //Thread.sleep(5000);
        //driver.findElement(By.cssSelector("label[for='cmn-toggle-1']")).click();
        // css de checkbox input#cmn-toggle-1
        WebElement seguroCheckInput = obtenerElementoCssSelector("input#cmn-toggle-1");
        WebElement seguroCheckLabel = obtenerElementoCssSelector("label[for='cmn-toggle-1']");

        boolean isSelectedSeguroCheck = seguroCheckInput.isSelected();
        if(!isSelectedSeguroCheck)
        {
            seguroCheckLabel.click();
            System.out.println("doy clic en check de seguro");
        }

        String valorCuotaEsperada = "$965.308";
        String valorCuotaEperadaSinSeguro = "$910.410";
        Thread.sleep(1000);
        String valorCuotaObtenida = driver.findElement(By.cssSelector("b[class='ng-binding']")).getText();
        if (valorCuotaEsperada.equalsIgnoreCase(valorCuotaObtenida)) {
            System.out.println("El valor de la cuota es igual a esperado " + valorCuotaObtenida);
        } else {
            System.out.println("El valor de la cuota es diferente Valor Obtenido " + valorCuotaObtenida + " y Valor Esperado " + valorCuotaEsperada);
        }
        tomarFoto("SolitudLibranzaCuotaConSeguro.png");
        assertEquals(valorCuotaEsperada, valorCuotaObtenida);

        isSelectedSeguroCheck = seguroCheckInput.isSelected();
        System.out.println("Resultado de validar check "+isSelectedSeguroCheck);
        if(isSelectedSeguroCheck)
        {
            seguroCheckLabel.click();
            System.out.println("Es unchecked el campo de seguro");

        }
        String valorCuotaObtenidaSinSeguro = driver.findElement(By.cssSelector("b[class='ng-binding']")).getText();
        assertEquals(valorCuotaEperadaSinSeguro, valorCuotaObtenidaSinSeguro);
        tomarFoto("SolicitudLibranzaSinSeguro.png");


    }

    @After
    public void cerrarNavegador() {

        /** cerrar navegador*/
        driver.close();
        driver.quit();
    }

    private void tomarFoto(String nombreImagen) throws Exception {

        /** crear objeto de TakesScreenShot*/
        TakesScreenshot scrShot = ((TakesScreenshot) driver);

        /** obtener screenshot y guardar en archivo de imagen */
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        /** mover imagen a destino **/
        File DestFile = new File("src/test/resources/evidencias/" + nombreImagen);

        /** copiar archivo a destino*/
        FileUtils.copyFile(SrcFile, DestFile);
    }

    private WebElement obtenerElementoCssSelector(String target) {
        WebElement elementoWeb = (WebElement) wait.until(
                new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(By.cssSelector(target));
                    }
                }
        );
        return elementoWeb;
    }
}
