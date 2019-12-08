package views;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.EventListenerList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import controls.CTRL;

public class SideBar extends JPanel {

    public SideBar() {
        this.setLayout(new BorderLayout());
        final JCheckBoxTree cbt = new JCheckBoxTree();
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        DefaultMutableTreeNode node;
        
        String[] cat = {"측정일시","측정소명","대기상태"};
        String[][] opts = {
            {"1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"},
            {"강남구","강남대로","강동구","강북구","강서구","공항대로","관악구","광진구","구로구","금천구","노원구","도봉구","도산대로","동대문구","동작구","동작대로","마포구","서대문구","서초구","성동구","성북구","송파구","신촌로","양천구","영등포구","영등포로","용산구","은평구","정릉로","종로","종로구","중구","중랑구","천호대로","청계천로","한강대로","홍릉로","화랑로"},
            {"이산화질소농도(ppm)", "오존농도(ppm)", "이산화탄소농도(ppm)", "아황산가스(ppm)","미세먼지(㎍/㎥)","초미세먼지(㎍/㎥)"}
        };
        for (int j = 0; j < cat.length; j++) {
            node = new DefaultMutableTreeNode(cat[j]);
            root.add(node);
            for (int k = 0; k < opts[j].length; k++) {
                node.add(new DefaultMutableTreeNode(opts[j][k]));
            }
        }
        
        DefaultTreeModel model = new DefaultTreeModel(root);

        cbt.setModel(model);
        cbt.addCheckChangeEventListener(new JCheckBoxTree.CheckChangeEventListener() {
            public void checkStateChanged(JCheckBoxTree.CheckChangeEvent event) {
                System.out.println("event");
                TreePath[] paths = cbt.getCheckedPaths();
                for (TreePath tp : paths) {
                    for (Object pathPart : tp.getPath()) {
                        System.out.print(pathPart + ",");
                    }                   
                    System.out.println();
                }
            }           
        });
        JScrollPane scrollPane = new JScrollPane(cbt);
        scrollPane.setSize(new Dimension(180, 680));
        scrollPane.setPreferredSize(new Dimension(180, 680));
        this.add(scrollPane);
        setVisible(true);
    }
}

class JCheckBoxTree extends JTree {
    JCheckBoxTree selfPointer = this;

    // Defining data structure that will enable to fast check-indicate the state of each node
    // It totally replaces the "selection" mechanism of the JTree
    private class CheckedNode {
        boolean isSelected;
        boolean hasChildren;
        boolean allChildrenSelected;

        public CheckedNode(boolean isSelected_, boolean hasChildren_, boolean allChildrenSelected_) {
            isSelected = isSelected_;
            hasChildren = hasChildren_;
            allChildrenSelected = allChildrenSelected_;
        }
    }
    HashMap<TreePath, CheckedNode> nodesCheckingState;
    HashSet<TreePath> checkedPaths = new HashSet<TreePath>();

    // Defining a new event type for the checking mechanism and preparing event-handling mechanism
    protected EventListenerList listenerList = new EventListenerList();

    public class CheckChangeEvent extends EventObject {     

        public CheckChangeEvent(Object source) {
            super(source);          
        }       
    }   

    public interface CheckChangeEventListener extends EventListener {
        public void checkStateChanged(CheckChangeEvent event);
    }

    public void addCheckChangeEventListener(CheckChangeEventListener listener) {
        listenerList.add(CheckChangeEventListener.class, listener);
    }
    public void removeCheckChangeEventListener(CheckChangeEventListener listener) {
        listenerList.remove(CheckChangeEventListener.class, listener);
    }

    void fireCheckChangeEvent(CheckChangeEvent evt) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i] == CheckChangeEventListener.class) {
                ((CheckChangeEventListener) listeners[i + 1]).checkStateChanged(evt);
            }
        }
    }

    // Override
    public void setModel(TreeModel newModel) {
        super.setModel(newModel);
        resetCheckingState();
    }

    // New method that returns only the checked paths (totally ignores original "selection" mechanism)
    public TreePath[] getCheckedPaths() {
        return checkedPaths.toArray(new TreePath[checkedPaths.size()]);
    }

    // Returns true in case that the node is selected, has children but not all of them are selected
    public boolean isSelectedPartially(TreePath path) {
        CheckedNode cn = nodesCheckingState.get(path);
        return cn.isSelected && cn.hasChildren && !cn.allChildrenSelected;
    }

    private void resetCheckingState() { 
        nodesCheckingState = new HashMap<TreePath, CheckedNode>();
        checkedPaths = new HashSet<TreePath>();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)getModel().getRoot();
        if (node == null) {
            return;
        }
        addSubtreeToCheckingStateTracking(node);
    }

    // Creating data structure of the current model for the checking mechanism
    private void addSubtreeToCheckingStateTracking(DefaultMutableTreeNode node) {
        TreeNode[] path = node.getPath();   
        TreePath tp = new TreePath(path);
        CheckedNode cn = new CheckedNode(false, node.getChildCount() > 0, false);
        nodesCheckingState.put(tp, cn);
        for (int i = 0 ; i < node.getChildCount() ; i++) {              
            addSubtreeToCheckingStateTracking((DefaultMutableTreeNode) tp.pathByAddingChild(node.getChildAt(i)).getLastPathComponent());
        }
    }

    // Overriding cell renderer by a class that ignores the original "selection" mechanism
    // It decides how to show the nodes due to the checking-mechanism
    private class CheckBoxCellRenderer extends JPanel implements TreeCellRenderer {     
        JCheckBox checkBox;     
        public CheckBoxCellRenderer() {
            super();
            this.setLayout(new BorderLayout());
            checkBox = new JCheckBox();
            add(checkBox, BorderLayout.CENTER);
            setOpaque(false);
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean selected, boolean expanded, boolean leaf, int row,
                boolean hasFocus) {
            checkBox.setText(value.toString());
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
            TreePath tp = new TreePath(node.getPath());
            CheckedNode cn = nodesCheckingState.get(tp);
            if (cn == null){
                return this;
            }
            checkBox.setSelected(cn.isSelected);
            checkBox.setOpaque(cn.isSelected && cn.hasChildren && ! cn.allChildrenSelected);
            return this;
        }
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

    public JCheckBoxTree() {
        super();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new State("옵션", false));
        DefaultMutableTreeNode node;

        // Disabling toggling by double-click
        this.setToggleClickCount(0);
        // Overriding cell renderer by new one defined above
        CheckBoxCellRenderer cellRenderer = new CheckBoxCellRenderer();
        this.setCellRenderer(cellRenderer);

        // Overriding selection model by an empty one
        DefaultTreeSelectionModel dtsm = new DefaultTreeSelectionModel() {      
            // Totally disabling the selection mechanism
            public void setSelectionPath(TreePath path) {
            }           
            public void addSelectionPath(TreePath path) {                       
            }           
            public void removeSelectionPath(TreePath path) {
            }
            public void setSelectionPaths(TreePath[] pPaths) {
            }
        };
        // Calling checking mechanism on mouse click
        this.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent arg0) {
                TreePath tp = selfPointer.getPathForLocation(arg0.getX(), arg0.getY());
                if (tp == null) {
                    return;
                }
                boolean checkMode = ! nodesCheckingState.get(tp).isSelected;
                checkSubTree(tp, checkMode);
                updatePredecessorsWithCheckMode(tp, checkMode);
                // Firing the check change event
                Object temp = new Object();
                fireCheckChangeEvent(new CheckChangeEvent(temp));
                // System.out.println(temp);
                // Repainting tree after the data structures were updated
                selfPointer.repaint();                          
            }           
            public void mouseEntered(MouseEvent arg0) {         
            }           
            public void mouseExited(MouseEvent arg0) {              
            }
            public void mousePressed(MouseEvent arg0) {             
            }
            public void mouseReleased(MouseEvent arg0) {
            }           
        });
        this.setSelectionModel(dtsm);
    }

    // When a node is checked/unchecked, updating the states of the predecessors
    protected void updatePredecessorsWithCheckMode(TreePath tp, boolean check) {
        TreePath parentPath = tp.getParentPath();
        // If it is the root, stop the recursive calls and return
        if (parentPath == null) {
            return;
        }       
        CheckedNode parentCheckedNode = nodesCheckingState.get(parentPath);
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) parentPath.getLastPathComponent();     
        parentCheckedNode.allChildrenSelected = true;
        parentCheckedNode.isSelected = false;
        for (int i = 0 ; i < parentNode.getChildCount() ; i++) {                
            TreePath childPath = parentPath.pathByAddingChild(parentNode.getChildAt(i));
            CheckedNode childCheckedNode = nodesCheckingState.get(childPath);
            // It is enough that even one subtree is not fully selected
            // to determine that the parent is not fully selected
            if (! childCheckedNode.allChildrenSelected) {
                parentCheckedNode.allChildrenSelected = false;      
            }
            // If at least one child is selected, selecting also the parent
            if (childCheckedNode.isSelected) {
                parentCheckedNode.isSelected = false;
            }
        }
        if(parentCheckedNode.allChildrenSelected){
            parentCheckedNode.isSelected = true;
        }
        // System.out.println(parentNode+"::parentCheckedNode.allChildrenSelected:"+parentCheckedNode.allChildrenSelected);
        if (parentCheckedNode.isSelected) {
            checkedPaths.add(parentPath);
        } else {
            checkedPaths.remove(parentPath);
        }
        // Go to upper predecessor
        updatePredecessorsWithCheckMode(parentPath, check);
    }

    // Recursively checks/unchecks a subtree
    protected void checkSubTree(TreePath tp, boolean check) {
        CheckedNode cn = nodesCheckingState.get(tp);
        cn.isSelected = check;
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tp.getLastPathComponent();
        for (int i = 0 ; i < node.getChildCount() ; i++) {              
            checkSubTree(tp.pathByAddingChild(node.getChildAt(i)), check);
        }
        cn.allChildrenSelected = check;
        if (check) {
            checkedPaths.add(tp);
        } else {
            checkedPaths.remove(tp);
        }
    }

}

// 출처 : https://stackoverflow.com/questions/21847411/java-swing-need-a-good-quality-developed-jtree-with-checkboxes