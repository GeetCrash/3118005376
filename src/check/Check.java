package check;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Check {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        final String sentence_seperator = "[，。：；！？]";
        String s = scanner.nextLine();
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        File file = new File(s2);
        String a = Read.readTxt(s);//将文本内容读取至字符串中
        String b = Read.readTxt(s1);
        Date start = new Date();
        //将文本进行切割
            List<String> result = new ArrayList<>();
            String []sen = a.split(sentence_seperator);
            for (String s22:sen){
                result.add(s22);
            }
            String[] results = new String[result.size()];
            result.toArray(results);
            List<String> result2 = new ArrayList<>();
            String[] sen2 = b.split(sentence_seperator);
            for (String s3:sen2){
                result2.add(s3);
            }
            String[] results2 = new String[result2.size()];
            result2.toArray(results2);
        float finialResult = (detect(results,results2));
        String ss = String.valueOf(finialResult);
        Date stop = new Date();//结束时间
        float time =stop.getTime() - start.getTime();
        String s3 = String.valueOf(time);
        String string = new String();
        string = ss.concat(s3);
        try {
            FileOutputStream out = new FileOutputStream(file);
            byte buy [] = string.getBytes();
            out.write(buy);
            out.close();
        }catch (Exception e)
        {e.printStackTrace();}
    }
    //将切割好的文本进行对比
    public static float detect(String[] des,String[] src)
    {
        float resultFloat=0.00f;
        for (String s:des)
            resultFloat+=checkSingleLineWithSrcArray(s,src);
        resultFloat/=des.length;
        return resultFloat;
    }
    //将一行和所有可能行进行比较得出最大的相似率
    private static float checkSingleLineWithSrcArray(String line, String []srcArray)
    {
        float result=0.00f;
        for (String s:srcArray)
        {
            //基于找公共子串进行相似度计算
            float temp1=checkSingleLineWithSingleLine(line,s);
            if (temp1>result)
                result=temp1;
            //基于找公共字符数进行相似度计算
            float temp2=checkDuplicationWithMatrix(line,s);
            if (temp2>result)
                result=temp2;
        }
        return result;
    }
    //将一行和一行比较，求出公共的区域，以此得出两字符串的相似度
    private static float checkSingleLineWithSingleLine(String line,String src)
    {
        float result;
        String s1 = line;
        String s2 = src;
        String max = s1.length() >= s2.length()?s1:s2;
        String min = s1.length() >= s2.length()?s2:s1;
        int l = 0;
        String s ="";
        for(int i=0;i<min.length();i++){
            for(int j=i+1;j<=min.length();j++){
                if(max.contains(min.substring(i,j)) && j-i>l){
                    l=j-i;
                    s=min.substring(i,j);
                }
            }
        }
        result=s.length();
        result/=line.length();
        return result;
    }
    //将一行和一行比较，求出相似度
    private static float checkDuplicationWithMatrix(String s1,String s2)
    {
        float result;
        int count=0;
        for (int i=0;i<s1.length();i++)
            for (int j=0;j<s2.length();j++)
                if (s1.charAt(i)==s2.charAt(j))
                    count++;
        result=count;
        result/=((s1.length()+s2.length())/2);
        return result;
    }
}



