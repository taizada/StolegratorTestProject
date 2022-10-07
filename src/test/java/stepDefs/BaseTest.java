package stepDefs;

import API.asserts.AssertClass;
import UI.pageObjects.IndexPage;
import UI.pageObjects.LoginPage;
import UI.pageObjects.PlayersPage;

public class BaseTest {

    public AssertClass assertClass = new AssertClass();
    public LoginPage loginPage = new LoginPage();
    public IndexPage indexPage = new IndexPage();
    public PlayersPage playersPage = new PlayersPage();
}

