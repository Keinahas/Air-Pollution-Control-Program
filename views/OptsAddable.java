package views;

import java.awt.event.ActionListener;

// interface
public interface OptsAddable{
    public abstract void addOption(String str);
    public abstract void addOption(String str, int mnemonic);
    public abstract void addOption(String str, ActionListener l);
    public abstract void addOption(String str, int mnemonic, ActionListener l);
}