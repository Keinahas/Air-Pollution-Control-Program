package views;

import java.awt.event.ActionListener;

public interface OptsAddable{
    public void addOption(String str);
    public void addOption(String str, ActionListener l);
}