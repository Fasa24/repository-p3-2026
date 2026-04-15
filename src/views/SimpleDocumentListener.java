package views;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SimpleDocumentListener implements DocumentListener {
    private Runnable r;

    public SimpleDocumentListener(Runnable r) { this.r = r; }

    public void insertUpdate(DocumentEvent e) { r.run(); }
    public void removeUpdate(DocumentEvent e) { r.run(); }
    public void changedUpdate(DocumentEvent e) { r.run(); }
}
