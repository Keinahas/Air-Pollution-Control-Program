package controls;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import controls.csvio.CSVIO;
import controls.db.dbActions;
import controls.triggers.DrawAction;
import controls.triggers.csvOpen;
import controls.triggers.csvSave;
import controls.triggers.dbUpload;

public class CTRL{
    
    ///-------------------------------------------- PRIVATE
    private static CSVIO CSV_IO = new CSVIO();
    private static dbActions DB = new dbActions();
    // private static List<String> fileNameList = new ArrayList<>();
    private static String fileName = null;
    private static List<String> header = null;
    private static List<List<String>> contents = null;

    ///-------------------------------------------- PUBLIC
	public static ActionListener CSV_Save = new csvSave(CSV_IO);
    public static ActionListener CSV_Open = new csvOpen(CSV_IO);
	public static ActionListener DRAW = new DrawAction();
    public static ActionListener DB_Insert = new dbUpload(DB);
    public static ActionListener DB_Show;
    public static ActionListener DB_Drop;
    public static ActionListener DB_;
    
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
}