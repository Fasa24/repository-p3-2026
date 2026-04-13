package main;

import controllers.*;
import views.*;

import javax.swing.JFrame;

public class AppNavigator {
    public static void openLogin(JFrame current) {
        LoginWindow login = new LoginWindow();
        current.dispose();
    }

    public static void openDashboard(JFrame current) {
        Dashboard d = new Dashboard();
        new DashboardController(d);
        current.dispose();
    }

    public static void openRegister(JFrame current) {
        FormularyRegistry f = new FormularyRegistry();
        new RegisterController(f);
        current.dispose();
    }
}
