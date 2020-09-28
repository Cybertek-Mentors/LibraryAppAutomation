package com.cybertekschool.library.utils.ui;

import com.cybertekschool.library.pages.BooksPage;
import com.cybertekschool.library.pages.DashBoardPage;
import com.cybertekschool.library.pages.LoginPage;
import com.cybertekschool.library.pages.UsersPage;

public class Pages {
    private DashBoardPage dashBoardPage;
    private LoginPage loginPage;
    private UsersPage usersPage;
    private BooksPage booksPage;

    public Pages() {
        this.dashBoardPage = new DashBoardPage();
        this.loginPage = new LoginPage();
        this.usersPage = new UsersPage();
        this.booksPage = new BooksPage();
    }

    public DashBoardPage dashBoardPage() {
        return dashBoardPage;
    }

    public LoginPage loginPage() {
        return loginPage;
    }

    public UsersPage usersPage() {
        return usersPage;
    }

    public BooksPage booksPage() {
        return booksPage;
    }
}
