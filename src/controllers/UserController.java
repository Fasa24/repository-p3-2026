package controllers;

import models.User;
import models.UserValidator;
import repository.UserRepository;
import tablemodels.UserTableModel;
import views.Dashboard;
import views.UserFormDialog;
import views.UsersView;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UserController {

    private UsersView view;
    private UserRepository repo;
    private UserTableModel model;

    public UserController(UsersView view) {
        this.view = view;
        repo = new UserRepository();

        this.view.getBtnAdd().addActionListener(e -> {
            openForm(null);
        });

        this.view.getBtnEdit().addActionListener(e -> {
            int row = view.getSelectedRow();
            if(row == -1) {
                JOptionPane.showMessageDialog(view, "Select a user first.");
                return;
            }

            openForm(model.getUserAt(row));
        });

        this.view.getBtnDelete().addActionListener(e -> deleteUser());

    }

    public void loadUsers() {
        try {
            List<User> users = repo.getUsers();

            if(model == null) {
                model = new UserTableModel(users);
                view.setTableModel(model);
            }else {
                model.setUsers(users);
            }

        }catch (IOException ex) {
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

            dialog.clearErrors();

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
                "Are you sure you want to delete this user?",
                "Confirm Delete",
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

}