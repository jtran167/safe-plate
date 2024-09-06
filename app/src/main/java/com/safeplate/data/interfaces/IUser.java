package com.safeplate.data.interfaces;

public interface IUser {

    com.safeplate.data.models.User login(String username, String password);

    com.safeplate.data.requests.User.CreateAccountResponse createAccount(String username, String password, String firstName, String lastName,
                                                                                  String email, String phone);

    void editProfile(int id, String firstName, String lastName, String phone);

    com.safeplate.data.models.User fetchByUserName(String username);
}
