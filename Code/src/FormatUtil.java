import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.index.Term;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class FormatUtil {
 
    /**
     * 去除停用词
     * @param oldString：原中文文本
     * @return 去除停用词之后的中文文本
     * @throws IOException
     */
    public static String RemovalOfStopWords(String oldString) throws IOException {
        String newString = oldString;
 
        // 中文 停用词 .txt 文件路径
        String filePath = "F:\\主文件夹\\知识图谱\\工具资源\\停用词.txt";
        File file = new File(filePath);
 
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<String> stopWords = new ArrayList<>();
        String temp = null;
        while ((temp = bufferedReader.readLine()) != null) {
            //System.out.println("*" + temp+ "*");
            stopWords.add(temp.trim());
        }
 
        List<String> termStringList = new ArrayList<>();
        for(Term term:termList) {
            termStringList.add(term.word);
            //System.out.println("*" + term.word + "*");
        }
 
        termStringList.removeAll(stopWords);
 
        newString = "";
        for (String string:termStringList) {
            newString += string;
        }
 
        return newString;
    }
 
}