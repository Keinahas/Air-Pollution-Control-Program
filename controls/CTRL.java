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
import views.GraphPanel;
import views.MainFrame;
import views.MyMenuBar;
import views.MyToolBar;
import views.SideBar;

public class CTRL{
    
    ///-------------------------------------------- PRIVATE
    // private static MainFrame frame;
    private static CSVIO CSV_IO = new CSVIO();
    private static dbActions DB = new dbActions();
    // private static List<String> fileNameList = new ArrayList<>();
    private static String fileName = null;
    private static List<String> total = null;
    private static List<String> header = null;
    private static List<String> locs = null;
    private static List<List<String>> average = null;
    private static List<List<String>> contents = null;

    ///-------------------------------------------- Vars
    // public static String[] colOpts = {"측정일시(월)", "측정소명", "이산화질소농도(ppm)", "오존농도(ppm)", "이산화탄소농도(ppm)", "아황산가스(ppm)","미세먼지(㎍/㎥)","초미세먼지(㎍/㎥)"};
    public static String[] locOpts = {"강남구","강남대로","강동구","강북구","강서구","공항대로","관악구","광진구","구로구","금천구","노원구","도봉구","도산대로","동대문구","동작구","동작대로","마포구","서대문구","서초구","성동구","성북구","송파구","신촌로","양천구","영등포구","영등포로","용산구","은평구","정릉로","종로","종로구","중구","중랑구","천호대로","청계천로","한강대로","홍릉로","화랑로"};
    public static MainFrame frame = new MainFrame(1500, 800);
    public static MyMenuBar menuBar = new MyMenuBar();
    public static MyToolBar toolBar = new MyToolBar();
    public static SideBar sideBar = new SideBar();
    public static GraphPanel gPanel = new GraphPanel();
    public static int BARGRAPH = 0;
    public static int LINEGRAPH = 1;
        

    ///-------------------------------------------- PUBLIC
	public static ActionListener CSV_Save = new csvSave(CSV_IO);
    public static ActionListener CSV_Open = new csvOpen(CSV_IO);
    public static ActionListener DB_Insert = new dbUpload(DB);
    public static ActionListener DB_Show;
    public static ActionListener DB_Drop;
    public static ActionListener DB_;
    public static ActionListener T_New_Show = new tableNew();
    public static ActionListener T_Blw_Show = new tableBelow();
    public static ActionListener SelectOpts = new opts(DB);
    public static ActionListener DrawBarGraph = new DrawAction(CTRL.gPanel, CTRL.BARGRAPH);
    public static ActionListener DrawLineGraph = new DrawAction(CTRL.gPanel, CTRL.LINEGRAPH);

    //public static ActionListener BTN_When = ;
    //public static ActionListener BTB_Where = ;


    ///-------------------------------------------- METHODS

    public static List<String> getHeader(){
        return header;
    }

    public static void setHeader(List<String> list){
        header = list;
    }

    public static List<List<String>> getAverage(){
        return average;
    }

    public static List<String> getTotal(){
        return total;
    }


    ////////
    public static void setAverageNTotal(){
        int contentsNum = 0;
        int lenHeader = header.size();
        double[] total = new double[lenHeader];
        total[0] = 0.0;
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
        // CTRL.average = list;
    }

    public static void setAverage(List<List<String>> list){
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