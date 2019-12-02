package controls;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controls.csvio.CSVIO;
import controls.db.dbActions;
import controls.listeners.DrawAction;
import controls.listeners.csvOpen;
import controls.listeners.csvSave;
import controls.listeners.dbUpload;
import controls.listeners.opts;
import controls.listeners.tableBelow;
import controls.listeners.tableNew;
import views.DrawingPanel;
import views.MainFrame;

public class CTRL{
    
    ///-------------------------------------------- PRIVATE
    private static MainFrame frame;
    private static CSVIO CSV_IO = new CSVIO();
    private static dbActions DB = new dbActions();
    // private static List<String> fileNameList = new ArrayList<>();
    private static String fileName = null;
    private static List<String> total = null;
    private static List<String> header = null;
    private static List<String> average = null;
    private static List<List<String>> contents = null;

    ///-------------------------------------------- PUBLIC
	public static ActionListener CSV_Save = new csvSave(CSV_IO);
    public static ActionListener CSV_Open = new csvOpen(CSV_IO);
	public static ActionListener DRAW = new DrawAction();
    public static ActionListener DB_Insert = new dbUpload(DB);
    public static ActionListener DB_Show;
    public static ActionListener DB_Drop;
    public static ActionListener DB_;
    public static ActionListener T_New_Show = new tableNew();
    public static ActionListener T_Blw_Show = new tableBelow();
    public static ActionListener SelectOpts = new opts(DB);

    //public static ActionListener BTN_When = ;
    //public static ActionListener BTB_Where = ;

    
    ///-------------------------------------------- METHODS

    // public static List<List<String>> getList(){
    //     return strLists;
    // }
    
    // public static void setList(List<List<String>> tempList){
    //     strLists = tempList;
    // }

    // public static List<String> getFileNameList(){
    //     return fileNameList;
    // }

    // public static void addFileName(String str){
    //     fileNameList.add(str);
    // }

    public static List<String> getHeader(){
        return header;
    }

    public static void setHeader(List<String> list){
        header = list;
    }

    public static List<String> getAverage(){
        return average;
    }

    public static List<String> getTotal(){
        return total;
    }

    public static void setAverageNTotal(){
        int contentsNum = 0;
        int lenHeader = header.size();
        double[] total = new double[lenHeader];
        total[0] = total[1] = 0.0;
        for(List<String> content : contents){
            int lenContent = content.size();
            for(int i=2;i<lenHeader;i++){
                if(lenContent < lenHeader){
                    break;
                }
                String temp = content.get(i);
                if(temp.isEmpty()){
                    continue;
                }
                contentsNum++;
                total[i] += Double.parseDouble(temp);
            }
        }
        String str = ",,";
        for(int i=2;i<total.length-1;i++){
            str += total[i] + ",";
        }
        str += total[total.length-1];
        List<String> list = Arrays.asList(str.split(","));
        System.out.println(list);
        CTRL.total = list;

        str = ",,";
        for(int i=2;i<total.length-1;i++){
            str += total[i]/contentsNum + ",";
        }
        str += total[total.length-1]/contentsNum;
        list = Arrays.asList(str.split(","));
        System.out.println(list);
        CTRL.average = list;
    }

    public static void setAverage(List<String> list){
        average = list;
    }

    public static List<List<String>> getContents(){
        return contents;
    }

    public static void setContents(List<List<String>> list){
        contents = list;
    }

    public static String getFileName(){
        return fileName;
    }

    public static void setFileName(String str){
        fileName = str;
    }

    public static MainFrame getFrame(){
        return frame;
    }

    public static void setFrame(MainFrame f){
        frame = f;
    }

    public static ActionListener getGraphAction(DrawingPanel pane, int gType){
        ((DrawAction)CTRL.DRAW).init(pane, gType);
        return CTRL.DRAW;
    }

    public static String parse(String str){
        String brackets = "()<>{}[]";
        String specials =  "`~!@#$%^&*-=+ :;'.,/|\\";

        for (int i = 0; i < specials.length(); i++) {
            int n = -1;
            char c = specials.charAt(i);
            while((n = str.indexOf(c)) != -1){
                str = str.replace(c, '_');
            }
        }

        for (int i = 0; i < brackets.length()-1; i+=2) {
            int t1 = -1, t2 = -1;
            char c1 = brackets.charAt(i), c2 = brackets.charAt(i+1);
            while(((t1 = str.indexOf(c1)) != -1) && ((t2 = str.indexOf(c2)) != -1)){
                str = str.substring(0, t1) + str.substring(t2+1);
            }
        }

        return str;
    }
}