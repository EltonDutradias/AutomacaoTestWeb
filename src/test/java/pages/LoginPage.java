package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver navegador;

    public LoginPage(WebDriver navegador){
        this.navegador = navegador;
    }

    public LoginPage informaOUsuario(String usuario){
        // Fazer login
        navegador.findElement(By.cssSelector("label[for=\"usuario\"]")).click();

        /*Abaixo estamos informando que após clicar no campo usuário digitar o nome do usuário"*/
        navegador.findElement(By.id("usuario")).sendKeys(usuario);

        //Aqui estamos informando que estamos retornando para esta mesma página
        return this;
    }

    public LoginPage informarASenha (String senha){
        navegador.findElement(By.cssSelector("label[for=\"senha\"]")).click();

        navegador.findElement(By.id("senha")).sendKeys(senha);

        //Aqui estamos informando que estamos retornando para esta mesma página
        return this;
    }

    public ListaDeProdutosPage submeterFormularioDeLogin(){
        navegador.findElement(By.cssSelector("button[type=\"submit\"]")).click();

        return new ListaDeProdutosPage(navegador);
    }
}
