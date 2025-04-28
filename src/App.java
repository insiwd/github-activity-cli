import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // receber username como argumento
        System.out.print("Seu username: ");
        String username = sc.nextLine();

        // fazer requisição da API do github
        final String URL_GET = "https://api.github.com/users/" + username + "/events";

        // cliente http
        HttpClient client = HttpClient.newHttpClient();
        // criando a requisição
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .timeout(Duration.ofSeconds(10))
                .uri(URI.create(URL_GET))
                .build();
        // enviando
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
        sc.close();
    }
}
