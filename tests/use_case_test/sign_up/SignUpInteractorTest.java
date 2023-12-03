package use_case_test.sign_up;

import app.SignupUseCaseFactory;
import data_access.FileUserDataAccessObject;
import interface_adapters.signup.SignupPresenter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.signup.*;
import use_case.signup.SignupInputBoundary;
import entity.CommonUserFactory;
import interface_adapters.login.*;
import interface_adapters.ViewManagerModel;
import interface_adapters.signup.*;
import view.SignupView;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SignUpInteractorTest {
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
    public void testSignUp() throws IOException {
        String username = "Alex";
        String password = "alex";
        CommonUserFactory x = new CommonUserFactory();
        FileUserDataAccessObject accessObject = new FileUserDataAccessObject("./usersTest.csv", x);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();

        SignupOutputBoundary presenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        SignupInputBoundary interactor = new SignupInteractor(accessObject, presenter, x);
        SignupController signupController = new SignupController(interactor);
        signupController.execute(username, password, password, false);

        SignupView view = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, accessObject);
        assertEquals(username, loginViewModel.getState().getUsername());
    }

    @Test
    public void testSignUpReturning() throws IOException {
        String username = "Alex";
        String password = "alex";
        CommonUserFactory x = new CommonUserFactory();
        FileUserDataAccessObject accessObject = new FileUserDataAccessObject("./usersTest.csv", x);
        SignupOutputBoundary presenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail();
            }

            @Override
            public void prepareReturningView(){}

            @Override
            public void prepareFailView(String error){
                fail();}
        };

        SignupInputData inputData = new SignupInputData(username, password, password, true);
        SignupInputBoundary interactor = new SignupInteractor(accessObject, presenter, x);
        interactor.execute(inputData); // Will send output data to presenter to be checked
    }

    @Test
    public void testSignUpFail() throws IOException {
        String username = "Alex";
        String password = "alex";
        CommonUserFactory x = new CommonUserFactory();
        FileUserDataAccessObject accessObject = new FileUserDataAccessObject("./usersTest.csv", x);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();

        SignupOutputBoundary presenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        SignupInputBoundary interactor = new SignupInteractor(accessObject, presenter, x);
        SignupController signupController = new SignupController(interactor);
        signupController.execute(username, password, "wrongPassword", false);

        assertEquals("Passwords don't match.", signupViewModel.getState().getUsernameError());
    }

    @After
    public void tearDown() throws IOException {
        // Restore the original file content
        Files.write(Paths.get(testFilePath), originalFileContent);
    }
}
