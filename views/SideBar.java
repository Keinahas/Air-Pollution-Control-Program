package views;

import javax.swing.*;
import javax.swing.border.LineBorder;

import views.MyButton;
//import views.resource;

import java.awt.*;
import java.awt.event.*;


import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;

public class SideBar extends JPanel {

    public SideBar() {
        //setSize(300,400);
        JTree tree = createTree();
        tree.setToggleClickCount(0);
        tree.setCellRenderer(new StateRenderer());
        tree.setCellEditor(new StateEditor());
        tree.setEditable(true);

        
        this.add(new JScrollPane(tree));

        this.setVisible(true);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                new SideBar();
            }
        });
    }

    private JTree createTree() {
        int children = 3;
        int grandChildren = 12;
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new State("옵션", false));
        DefaultMutableTreeNode node;

        String[] cat = {"측정 일시","측정 장소","대기 상태"};
        String[][] 옵션 = {
            {"1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"},
            {"중량구", "강남구","","","","","","","","","",""},
            {"이산화질소농도(ppm)", "오존농도(ppm)", "이산화탄소농도(ppm)", "아황산가스(ppm)","미세먼지(㎍/㎥)","문세먼지(㎍/㎥)","","","","","",""}
        };
        for (int j = 0; j < children; j++) {
            node = new DefaultMutableTreeNode(new State(cat[j], false));
            root.add(node);
            for (int k = 0; k < grandChildren; k++) {
                node.add(new DefaultMutableTreeNode(new State(옵션[j][k], false)));
            }
        }
        DefaultTreeModel model = new DefaultTreeModel(root);
        return new JTree(model);
    }

    public class State {

        private String text;
        private boolean selected;

        public State(String text, boolean selected) {
            this.text = text;
            this.selected = selected;
        }

        public String getText() {
            return text;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

    }

    public class StateEditor extends AbstractCellEditor implements TreeCellEditor {

        //JPanel panel;
        private JCheckBox checkBox;

        private State editorValue;

        public StateEditor() {
            checkBox = new JCheckBox();
            checkBox.setOpaque(false);
        }

        @Override
        public Object getCellEditorValue() {
            editorValue.setSelected(checkBox.isSelected());
            return editorValue;
        }

        @Override
        public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {

            System.out.println("...");

            if (value instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                State state = (State) node.getUserObject();
                editorValue = state;
                checkBox.setText(state.getText());
                checkBox.setSelected(state.isSelected());
            } else {
                checkBox.setText("??");
                checkBox.setSelected(false);
            }

            return checkBox;

        }

    }

    public class StateRenderer implements TreeCellRenderer {

        private JCheckBox checkBox;

        public StateRenderer() {
            checkBox = new JCheckBox();
            checkBox.setOpaque(false);
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                        boolean leaf, int row, boolean hasFocus) {

            if (value instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                State state = (State) node.getUserObject();
                checkBox.setText(state.getText());
                checkBox.setSelected(state.isSelected());
            } else {
                checkBox.setText("??");
                checkBox.setSelected(false);
            }

            if (selected) {
                checkBox.setBackground(UIManager.getColor("Tree.selectionBackground"));
                checkBox.setForeground(UIManager.getColor("Tree.selectionForeground"));
            } else {
                checkBox.setForeground(tree.getForeground());
            }

            checkBox.setOpaque(selected);

            return checkBox;
        }
    }
}