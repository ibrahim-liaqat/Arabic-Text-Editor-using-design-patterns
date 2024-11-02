package PL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import BL.filemaininterface;
import DTO.Page;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class searchResults extends JFrame {
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private filemaininterface files;
    List<Page> searchResults = new ArrayList<>();

    public searchResults(filemaininterface filesfrombusiness, String name) {
        files = filesfrombusiness;
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Search Results");
        this.setLayout(new BorderLayout());
        searchResults = files.searchwords(name);

        String[] columnNames = {"Page ID", "Document ID", "Search Results"};

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };

        for (Page page : searchResults) {
            int pageId = page.getPageId();
            String documentId = page.getDocumentId();
            String contentLine = page.getSearchResult();
            int index = contentLine.toLowerCase().indexOf(name.toLowerCase());
            if (index != -1) {
                int start = Math.max(0, index - 15);
                int end = Math.min(contentLine.length(), index + name.length() + 15);
                String snippet = contentLine.substring(start, end) + "...";
                tableModel.addRow(new Object[]{pageId, documentId, snippet});
            }
        }

        resultsTable = new JTable(tableModel);
        resultsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = resultsTable.getSelectedRow();
                int selectedColumn = resultsTable.getSelectedColumn();

                // Check if double-clicked and in the "Document ID" column (index 1)
                if (e.getClickCount() == 2 && selectedRow != -1 && selectedColumn == 1) {
                    Page selectedPage = searchResults.get(selectedRow);
                    new open(files, selectedPage.getDocumentId()); // Assuming open takes document_id content
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(resultsTable);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
