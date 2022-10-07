package stepDefs.apiStepDefs;

import API.HelperMethods.HelperAPI;
import API.POJO.PlayerPojo;
import API.dataProviders.ConfigReader;
import com.google.gson.Gson;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.Method;
import io.restassured.response.Response;
import stepDefs.BaseTest;

public class APIStepdefs  extends BaseTest {

    private String token;

    @Given("get quest token")
    public void getQuestToken() {
        String payload = "{\n" +
                "    \"grant_type\": \"client_credentials\",\n" +
                "    \"scope\": \"guest:default\"\n" +
                "}";
        Response response = HelperAPI.requestWithBasic("/v2/oauth2/token", payload, Method.POST,
                ConfigReader.getProperty("basicToken"));

        assertClass.assertNumberWithNumber(200, response.getStatusCode());
        assertClass.assertTextNotEqualText("", response.jsonPath().getString("access_token"));
        token = response.jsonPath().getString("access_token");
        System.out.println("token : " + token);
    }

    PlayerPojo player1 = new PlayerPojo();
    int playerId;

    @Then("register player")
    public void registerPlayer() {

        Gson gson = new Gson();
        String playerPayload = gson.toJson(player1);

        Response response = HelperAPI.requestWithBearerToken("/v2/players", playerPayload, Method.POST,
                token);

        assertClass.assertNumberWithNumber(201, response.getStatusCode());
        assertClass.assertTextWithText(response.jsonPath().getString("username"), player1.getUsername());
        assertClass.assertTextWithText(response.jsonPath().getString("email"), player1.getEmail());

        playerId = response.jsonPath().getInt("id");
        System.out.println(response.jsonPath().getString("username"));
        System.out.println(response.jsonPath().getString("email"));
        System.out.println("PlayerID : " + playerId);
    }

    @Then("authorize as created player")
    public void authorizeAsCreatedPlayer() {
        String payload = "{\n" +
                "    \"grant_type\": \"password\",\n" +
                "    \"username\": \"" + player1.getUsername() + "\",\n" +
                "    \"password\": \"" + player1.getPassword_change() + "\"\n" +
                "}";
        Response response = HelperAPI.requestWithBasic("/v2/oauth2/token", payload, Method.POST,
                ConfigReader.getProperty("basicToken"));
        token = response.jsonPath().getString("access_token");

        assertClass.assertNumberWithNumber(200, response.getStatusCode());
        assertClass.assertTextNotEqualText("", response.jsonPath().getString("access_token"));
        System.out.println("player created!");
    }

    @And("get details of player")
    public void getDetailsOfPlayer() {
        Response response = HelperAPI.requestWithBearerTokenGetInfo("/v2/players/" + playerId, Method.GET,
                token);

        assertClass.assertNumberWithNumber(200, response.getStatusCode());
        assertClass.assertTextWithText(response.jsonPath().getString("username"), player1.getUsername());
        assertClass.assertTextWithText(response.jsonPath().getString("email"), player1.getEmail());
        System.out.println(response.jsonPath().getString("username"));
        System.out.println(response.jsonPath().getString("email"));
        System.out.println("Player: " + response);
    }

    @And("get details of other player")
    public void getDetailsOfOtherPlayer() {
        String anyPlayerId = "1";
        Response response = HelperAPI.requestWithBearerTokenGetInfo("/v2/players/" + anyPlayerId, Method.GET,
                token);

        assertClass.assertNumberWithNumber(404, response.getStatusCode());

    }
}
