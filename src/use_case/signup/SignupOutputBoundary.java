package use_case.signup;

public interface SignupOutputBoundary {
    void prepareSuccessView(SignupOutputData user);

    void prepareReturningView();

    void prepareFailView(String error);
}