package controllers;

import models.User;
import models.UserValidator;
import repository.UserRepository;
import services.PDFExporter;
import tablemodels.UserTableModel;
import views.UserFormDialog;
import views.UsersView;

import javax.swing.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UserController {
    private UsersView view;
    private UserRepository repo;
    private UserTableModel model;
    private PDFExporter pdfExporter;

    public UserController(UsersView view) {
        this.view = view;
        repo = new UserRepository();
        pdfExporter = new PDFExporter();

        view.getBtnAdd().addActionListener(e -> openForm(null));

        view.getBtnEdit().addActionListener(e -> {
            int row = view.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(view, "Select a user first.");
                return;
            }
            openForm(model.getUserAt(row));
        });

        view.getBtnDelete().addActionListener(e -> deleteUser());
        view.getBtnPdf().addActionListener(e -> generatePdf());
    }

    public void loadUsers() {
        try {
            List<User> users = repo.getUsers();

            if (model == null) {
                model = new UserTableModel(users);
                view.setTableModel(model);
            } else {
                model.setUsers(users);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    private void openForm(User user) {
        UserFormDialog dialog = new UserFormDialog(
                (JFrame) SwingUtilities.getWindowAncestor(view), user
        );

        dialog.setRegisterListener(e -> {
            User formUser = dialog.getUserFromForm();
            Map<String, String> errors = UserValidator.validate(formUser);

            if (!errors.isEmpty()) {
                dialog.showErrors(errors);
                return;
            }

            try {
                if (user == null) {
                    repo.save(formUser);
                } else {
                    repo.update(view.getSelectedRow(), formUser);
                }
                loadUsers();
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
        });

        dialog.setReturnListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    private void deleteUser() {
        int row = view.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(view, "Select a user first.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                view,
                "Are you sure?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            List<User> users = repo.getUsers();
            users.remove(row);
            repo.updateAll(users);
            loadUsers();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    private void generatePdf() {
        File file = view.selectPdfFile();

        if (file == null) return;

        try {
            pdfExporter.exportUsers(repo.getUsers(), file);

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error exporting PDF");
        }
    }
}