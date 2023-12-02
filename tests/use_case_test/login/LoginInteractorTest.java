package use_case_test.login;

import data_access.FileUserDataAccessObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import use_case.login.*;
import use_case.login.LoginInputBoundary;
import entity.CommonUserFactory;
import  entity.User;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LoginInteractorTest {
    private final String testFilePath = "./usersTest.csv";
    private List<String> originalFileContent;
    @Before
    public void setUp() throws IOException {
        // Save the original file content
        originalFileContent = Files.readAllLines(Paths.get(testFilePath));
    }

    //Tests if a rating is properly updated. A userRating is first initialized to have a rating of 3.
    //The interactor is called and updates the database, the new rating should be updated to 3.
    @Test
    public void testLogin() throws IOException {
        String username = "Alex";
        String password = "alex";
        CommonUserFactory x = new CommonUserFactory();
        User alex = x.create(username, password);
        FileUserDataAccessObject accessObject = new FileUserDataAccessObject("./usersTest.csv", x);
        accessObject.save(alex);
        LoginOutputBoundary presenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {

                String u = user.getUsername();
                assertEquals(u, username);
            }

            @Override
            public void prepareFailView(String error){
                fail();
            }
        };

        LoginInputData inputData = new LoginInputData(username, password);
        LoginInputBoundary interactor = new LoginInteractor(accessObject, presenter);
        interactor.execute(inputData); // Will send output data to presenter to be checked
    }

    @Test
    public void testLoginFailOne() throws IOException {
        CommonUserFactory x = new CommonUserFactory();
        FileUserDataAccessObject accessObject = new FileUserDataAccessObject("./usersTest.csv", x);
        LoginOutputBoundary presenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail();
            }

            @Override
            public void prepareFailView(String error){
            }
        };

        LoginInputData inputData = new LoginInputData("Alex", "alex");
        LoginInputBoundary interactor = new LoginInteractor(accessObject, presenter);
        interactor.execute(inputData); // Will send output data to presenter to be checked
    }

    @Test
    public void testLoginFailTwo() throws IOException {
        String username = "Alex";
        String password = "alex";
        CommonUserFactory x = new CommonUserFactory();
        User alex = x.create(username, password);
        FileUserDataAccessObject accessObject = new FileUserDataAccessObject("./usersTest.csv", x);
        accessObject.save(alex);
        LoginOutputBoundary presenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail();
            }

            @Override
            public void prepareFailView(String error){
            }
        };

        LoginInputData inputData = new LoginInputData(username, "wrongPassword");
        LoginInputBoundary interactor = new LoginInteractor(accessObject, presenter);
        interactor.execute(inputData); // Will send output data to presenter to be checked
    }

    @After
    public void tearDown() throws IOException {
        // Restore the original file content
        Files.write(Paths.get(testFilePath), originalFileContent);
    }

}
