/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public  class StatusCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            // Get the status value from the last column (index 8)
            String status = (String) table.getValueAt(row, 8);

            // Change background color based on the status
            if ("Approved".equalsIgnoreCase(status)) {
                cell.setBackground(Color.GREEN);
                cell.setForeground(Color.BLACK);
            } else if ("Denied".equalsIgnoreCase(status)) {
                cell.setBackground(Color.RED);
                cell.setForeground(Color.WHITE);
            } else {
                cell.setBackground(Color.WHITE); // Default background for other statuses
                cell.setForeground(Color.BLACK);
            }

            // Handle selection color
            if (isSelected) {
                cell.setBackground(Color.BLUE); // Example selected color
                cell.setForeground(Color.WHITE);
            }

            return cell;
        }
    }