package PL;

import javax.swing.*;
import BL.filemaininterface;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class navigatepages {
    private ArrayList<JFrame> pages;
    private int currentPageIndex;
    private filemaininterface obj;  
    private ArrayList<String> a;   
    public navigatepages(filemaininterface o, String name) {
        obj = o;
        a = obj.navigatepages(name); 

        
        if (a == null || a.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No pages found for the given file name.");
            return;
        }

        pages = new ArrayList<>();
        createPages();
        currentPageIndex = 0;
        showPage(currentPageIndex);
        pages.get(currentPageIndex).setVisible(true); 
    }

    private void createPages() {
        for (int i = 0; i < a.size(); i++) {  
            JFrame page = new JFrame("Page " + (i + 1));
            page.setSize(400, 300);
            page.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            page.setLocationRelativeTo(null);

            // Displaying page content
            JTextArea contentArea = new JTextArea(a.get(i));
            contentArea.setFont(new Font("Arial", Font.PLAIN, 16));
            contentArea.setLineWrap(true);
            contentArea.setWrapStyleWord(true);
            contentArea.setEditable(false);
            page.add(new JScrollPane(contentArea), BorderLayout.CENTER);

            // Navigation panel
            JPanel navPanel = new JPanel();
            JButton backButton = new JButton("<");
            JButton forwardButton = new JButton(">");
            backButton.addActionListener(this::goBack);
            forwardButton.addActionListener(this::goForward);

            navPanel.add(backButton);
            navPanel.add(forwardButton);
            page.add(navPanel, BorderLayout.SOUTH);

            pages.add(page);  
        }
    }

    private void showPage(int index) {
        for (int i = 0; i < pages.size(); i++) {
            pages.get(i).setVisible(i == index);
        }
    }

    private void goBack(ActionEvent e) {
        if (currentPageIndex > 0) {
            currentPageIndex--;
            showPage(currentPageIndex);
        } else {
            JOptionPane.showMessageDialog(null, "Already at the first page!");
        }
    }

    private void goForward(ActionEvent e) {
        if (currentPageIndex < pages.size() - 1) {
            currentPageIndex++;
            showPage(currentPageIndex);
        } else {
            JOptionPane.showMessageDialog(null, "Already at the last page!");
        }
    }
}
