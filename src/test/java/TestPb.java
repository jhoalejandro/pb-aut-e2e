import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class TestPb {

    private WebDriver driver;
    private Long timeout =Long.parseLong("10");

    @Before
    public void iniciarPb() {
        /** obtener el webdriver y asignar a propiedades del sistema */
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/windows/chrome/chromedriver.exe");

        /**crear web driver chrome */
        driver = new ChromeDriver();

        /** url de página **/

        String urlPb = "https://www.bancodeoccidente.com.co/portaltransaccional/#/login";

        String tituloEsperadoPb = "Ingreso al Portal Transaccional | Banco de Occidente ";

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
    }

    @Test
    public void autenticarUsuario() throws Exception {
        /** cerrar popup*/
        //Thread.sleep(5000);
        //driver.findElement(By.cssSelector("a.popup-close")).click();
        /** dar clic en boton Transacciones */
        //driver.findElement(By.cssSelector("div.button.button-primary")).click();
        /** dar clic en el portal pb */
        //driver.findElement(By.xpath("//a[text()='Portal Transaccional']")).click();
        //Thread.sleep(20000);
        /** dar clic en opcion para desplegar tipos de identificacion */
        /*int size = driver.findElements(By.tagName("iframe")).size();
        System.out.println("frames igual a " + size);
        buscar elemento en frames
        String xpathCedula = "//div[@class='ng-input']";
        int total = driver.findElements(By.xpath(xpathCedula)).size();
        System.out.println("Se encontro en el frame actual  esta cantidad de elementos " + total);
        driver.switchTo().parentFrame();
        total = driver.findElements(By.xpath(xpathCedula)).size();
        System.out.println("Se encontro en el frame padre  esta cantidad de elementos " + total);
        size = driver.findElements(By.tagName("iframe")).size();
        System.out.println("frames en el parent igual a " + size);

        for (int i = 0; i < size; i++) {
            driver.switchTo().frame(i);
            total = driver.findElements(By.xpath(xpathCedula)).size();
            System.out.println("Se encontro en el iframe " + i + " esta cantidad de elementos " + total);
            driver.switchTo().defaultContent();
        }
        for (int i = 0; i < size; i++) {
            driver.switchTo().frame(i);
            total = driver.findElements(By.tagName("iframe")).size();
            System.out.println("Se encontro en el iframe " + i + " esta cantidad de frames " + total);
            driver.switchTo().defaultContent();
        }
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath(xpathCedula)).click();
        Thread.sleep(5000);
        */
        //"span.ng-arrow-wrapper"
        /** dar clic en cedula de ciudadania */
        WebElement flechaTiposIdentificacion =driver.findElement(By.cssSelector("div[class='ng-value-container']"));
        //tiposIdentificacion.click();
        //Thread.sleep(5000);
        //tiposIdentificacion.sendKeys("Cédula de Ciudadanía");
        //Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(flechaTiposIdentificacion));

        wait.until(ExpectedConditions.elementToBeClickable(flechaTiposIdentificacion));

        flechaTiposIdentificacion.click();
        //Select tiposIdentificacionSelect = new Select(driver.findElement(By.xpath("//span[@class='ng-option-label ng-star-inserted']")));
        //tiposIdentificacionSelect.selectByVisibleText("Cédula de Ciudadanía");
        System.out.println("deberia ya haber mostrado los tipos de identificacion");

//        WebElement cedulaCiudadaniaElement = driver.findElement(By.cssSelector("div[class='ng-dropdown-panel-items scroll-host'] :nth-child(2) :nth-child(1) :nth-child(1)"));
        WebElement cedulaCiudadaniaElement = driver.findElement(By.xpath("//span[contains(@class, 'ng-option')][text()='Cédula de Ciudadanía']"));


        wait.until(ExpectedConditions.visibilityOf(cedulaCiudadaniaElement));
        wait.until(ExpectedConditions.elementToBeClickable(cedulaCiudadaniaElement));
        cedulaCiudadaniaElement.click();
        System.out.println("deberia ya haber desaparecido el tipo de identificacion");

        /** digitar numero de cedula input#document-number*/
        WebElement numeroDocumento = driver.findElement(By.id("document-number"));
        wait.until(ExpectedConditions.visibilityOf(numeroDocumento));
        wait.until(ExpectedConditions.elementToBeClickable(numeroDocumento));
        numeroDocumento.click();
        numeroDocumento.sendKeys("123456");

        /** digitar clave input#password*/
        WebElement password = driver.findElement(By.cssSelector("input#password"));
        wait.until(ExpectedConditions.visibilityOf(password));
        wait.until(ExpectedConditions.elementToBeClickable(password));
        password.click();
        password.clear();
        password.sendKeys("123456*");

        /** mostrar clave em[class='showPass ng-star-inserted icon-eye-hidden']**/
        Thread.sleep(1000);
        WebElement mostrarClave = driver.findElement(By.cssSelector("em.showPass.ng-star-inserted.icon-eye-hidden"));
        wait.until(ExpectedConditions.visibilityOf(mostrarClave));
        wait.until(ExpectedConditions.elementToBeClickable(mostrarClave));
        mostrarClave.click();
        /** tomar foto */
        this.tomarFoto("IngresoLoginYPassword.png");
        /** dar clic en boton de ingresar button.btn.ng-star-inserted[type='submit']
         * xpath //span[text()='Ingresar']*/
        WebElement aceptarBoton = driver.findElement(By.cssSelector("button.btn.ng-star-inserted[type='submit']"));
        wait.until(ExpectedConditions.visibilityOf(aceptarBoton));
        wait.until(ExpectedConditions.elementToBeClickable(aceptarBoton));
        aceptarBoton.click();
        Thread.sleep(10000);

        /** validar que aparezca JHOAN en la página principal */
        String nombreObtenido = "";
        String nombreEsperado = "JHOAN";
        WebElement nombreObtenidoLabel = driver.findElement(By.cssSelector("h2.user-info__full.h5-b-left.m-2xs-bottom"));
        wait.until(ExpectedConditions.visibilityOf(nombreObtenidoLabel));
        nombreObtenido = nombreObtenidoLabel.getText();
        if (nombreEsperado.equalsIgnoreCase(nombreObtenido)) {
            System.out.println("El nombre esperado es igual al obtenido " + nombreEsperado);
        } else {
            System.out.println("El nombre obtenido es distinto al esperado " + nombreObtenido);
        }
        tomarFoto("paginaPrincipal.png");
        /** dar clic en logout */
        driver.findElement(By.cssSelector("span.icon-log-out")).click();
        tomarFoto("paginaInicioLogout.png");
        Thread.sleep(20000);
        tomarFoto("paginaInicioLogout2.png");

        /** validar salida */
        String textoSalidaEsperada = "INGRESA A TU PORTAL TRANSACCIONAL";
        String textoSalidaObtenido = "";
        textoSalidaObtenido = driver.findElement(By.cssSelector("h2[class^='s2-sm-center m-3s-bottom ng-tns-c72']")).getText();

        if (textoSalidaEsperada.equalsIgnoreCase(textoSalidaObtenido)) {
            System.out.println("Label salida esperado es igual al obtenido " + textoSalidaEsperada);
        } else {
            System.out.println("El Label obtenido es distinto al esperado " + textoSalidaObtenido);
        }
        Thread.sleep(20000);

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
        File DestFile = new File("src/test/resources/evidencias/"+nombreImagen);

        /** copiar archivo a destino*/
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
