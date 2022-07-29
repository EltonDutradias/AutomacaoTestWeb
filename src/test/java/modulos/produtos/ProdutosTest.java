package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pages.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web Modulo de Produtos")
public class ProdutosTest {
    private WebDriver navegador;

    @BeforeEach
    public void beforeEach(){
        //Abrir navegador
        /*Abaixo informamos o caminho onde armazenamos o driver do browser, setando um propriedade para isso*/
        System.setProperty("webdriver.edge.driver","C:\\Drivers\\edgedriver_win64\\msedgedriver.exe");

        /*Criando navegador*/
        this.navegador = new EdgeDriver();

        //Maximizar a tela
        this.navegador.manage().window().maximize();

        // Vou definir um tempo de espera padrão de 5 segundos, para que se alguma demora ocorra.
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Navegar para a página da lojinha web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");

    }
    @Test
    @DisplayName("Nao e permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {
        //Fazer Login
        String mensagemApresentada = new LoginPage(navegador)
                .informaOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioNovoProduto()
                .informarNomeDoProduto("Xbox")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("Preto,Branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();



        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

    }

    @Test
    @DisplayName("Nao permitido registrar produto com valor acima de R$7.000,00")
    public void testeNaoPermitidoRegistrarProdutoComValorAcimaDeSeteMil (){
        String mensagemApresentada = new LoginPage(navegador)
                .informaOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioNovoProduto()
                .informarNomeDoProduto("Iphone 11")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("Branco,Prata")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Adicionando produtos com valores entre R$0,01 a R$7.000,00")
    public void testeAdicaoDeProdutosComValoresPermitidos(){
       String mensagemApresentada = new LoginPage(navegador)
                .informaOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioNovoProduto()
                .informarNomeDoProduto("Dell Inspiron")
                .informarValorDoProduto("650000")
                .informarCoresDoProduto("Prata")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

                Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Adicionando produtos com valor limite de R$7.000,00")
    public void testeAdicaoDeProdutosComValorDeSeteMil() {
        String mensagemApresentada = new LoginPage(navegador)
                .informaOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioNovoProduto()
                .informarNomeDoProduto("Acer Gamer")
                .informarValorDoProduto("700000")
                .informarCoresDoProduto("Prata")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }
    @AfterEach
    public void afterEach(){
        // Vou fechar o navegador
        navegador.quit();
    }
}
