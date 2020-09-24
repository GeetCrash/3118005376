package check;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Read {
    public static String readTxt(String s){
        File file = new File(s);
        BufferedReader bufferedReader =null;
        StringBuffer stringBuffer = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            stringBuffer = new StringBuffer();
            String line = null;
            while((line = bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        String string = new String(stringBuffer);
        string = string.replaceAll("[^\u4E00-\u9FA5，。：；“”‘’！？]","");
        return string;
    }
}
