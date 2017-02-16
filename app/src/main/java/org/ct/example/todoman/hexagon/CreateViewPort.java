package org.ct.example.todoman.hexagon;


public interface CreateViewPort {
    void showProgress();
    void hideProgress();
    void displayRequiredTitleError();
    void displayInvalidDateError();
    void onItemCreated();
    void onUnexpectedError();
}
