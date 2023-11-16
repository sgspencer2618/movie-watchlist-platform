package interface_adapters.login;

import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;


public class LoginPresenter {

    public LoginPresenter() {
    }

    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.
    }

    public void prepareFailView(String error) {

    }
}
