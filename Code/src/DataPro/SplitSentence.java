package DataPro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.SystemUtils;
import org.hamcrest.core.StringStartsWith;

import java.lang.Character;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import com.lingjoin.divideWords.DelTagsUtil;
import com.lingjoin.divideWords.NlpirMethod;

import cn.com.cjf.CJFBeanFactory;
import cn.com.cjf.ChineseJF;

import edu.stanford.nlp.ling.CoreAnnotations.IsDateRangeAnnotation;

import kevin.zhang.NLPIR;
import liuyang.nlp.lda.com.MapSort;
import liuyang.nlp.lda.com.Stopwords;
import liuyang.nlp.lda.com.Text;
import liuzhi.nlp.lda.docs.Documents;
import liuzhi.nlp.lda.test.TestNLPIR;
import util.OrderedDocument;
import com.lingjoin.divideWords.DelTagsUtil;
import com.lingjoin.divideWords.NlpirMethod;
public class SplitSentence {
	/**  
	* @Title: initCoursesTxts  
	* @Description: TODO(splitSentence forum(*xls) for BLDA)  
	* @param @param label
	* @param @param source *xls
	* @param @param oldSentiDir
	* @param @param documents
	* @param @return
	* @param @throws IOException
	* @param @throws BiffException    参数  
	* @return ArrayList<String>    返回类型  
	* @throws  
	*/  
	//source D:\study\20180630_CCNU_ResearchGroup\data\201402PsychologicalBasis\forum
	//savePath D:\study\20180630_CCNU_ResearchGroup\Project\BLDA\Code\data\test\TMinput
	//fileList D:\study\20180630_CCNU_ResearchGroup\Project\BLDA\Code\data\test\.filelist-test.txt
	public static void initCoursesTxts(String savePath, String fileList, String source) throws IOException, BiffException, ParseException{
			  String userpath = "Data/ext.dic";// user dic
			  
			  List<Text> allText = new ArrayList<Text>(); //all document
			  File fileDir = new File(source);
			  File[] files = fileDir.listFiles();
			  System.out.println(files.length);
			  for(int fileNo = 0; fileNo < files.length; fileNo++){
				  System.out.println("file No. "+files[fileNo].getAbsoluteFile());
				  NlpirMethod.Nlpir_init();
				  NlpirMethod.NLPIR_ImportUserDict(userpath);
				  jxl.Workbook readwb1 = null;
				  InputStream instream1 = new FileInputStream(files[fileNo].getAbsoluteFile());   
			      //获取指定单元格的对象引用
			      readwb1 = Workbook.getWorkbook(instream1);//, workbookSettings);   
			      //Sheet的下标是从0开始   
			      //获取第一张Sheet表   
			      Sheet readsheet1 = readwb1.getSheet(0);   
			      //获取Sheet表中所包含的总行数   
			      int rsRows1 = readsheet1.getRows();
			      for (int r = 2; r < rsRows1; r++){
	//		    	  System.out.println("rsRow: "+r);
			          Cell postCell = readsheet1.getCell(2, r);
			          // reading post content ,the initial line is 0
			          String post = postCell.getContents();
	//		          System.out.println(post);
					  
			          // read Author
			          Text tx = new Text();
					  tx.setAuthor(readsheet1.getCell(0, r).getContents());
					  
					  // read time
					  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss w");// record date indexString formatTime = sdf.format(Long.parseLong(res+"000"));
					  String formatTime = readsheet1.getCell(1, r).getContents();
					  System.out.println(formatTime);
					  formatTime = formatTime.replaceAll("/", "-");
					  // formatTime = formatTime + "00";
					  tx.setTimeStr(formatTime);
					  
					 // read congnitiveBehavior
					  String cbString = "";
					  String cBString = "";
					  cBString = "#PB#";
					  cbString = readsheet1.getCell(4, r).getContents()
							  + readsheet1.getCell(5, r).getContents()
							  + readsheet1.getCell(6, r).getContents();
//					  System.out.println("1cbString: " + cbString);
					  if(Integer.parseInt(cbString)!= 0)
						  cBString = cBString+"AC" ;
					  cbString = readsheet1.getCell(7, r).getContents()
							  + readsheet1.getCell(8, r).getContents()
							  + readsheet1.getCell(9, r).getContents();
//					  System.out.println("2cbString: " + cbString);
//					  System.out.println("2cBString: " + cBString);
					  
					  if(Integer.parseInt(cbString)!= 0)
						  cBString = cBString + "CO"  ;
					  cbString = readsheet1.getCell(10, r).getContents()
							  + readsheet1.getCell(11, r).getContents()
							  + readsheet1.getCell(12, r).getContents();
					  System.out.println("3cbString: " + cbString);
					  
					  if(Integer.parseInt(cbString)!= 0)
						  cBString = cBString + "IN" ;
					  tx.setCognitiveBehavior(cBString);
					  
					  
					  String msg = post;
					  msg = DelTagsUtil.delHtmlTags(msg);
						  String regEx="(((http|ftp|https|file)://)|((?<!((http|ftp|https|file)://))www\\.)).*?(?=(&nbsp;|\\s|　|<br />|$|[<>]))";  
						  Pattern p =Pattern.compile(regEx,Pattern.CASE_INSENSITIVE);
						  Matcher matcher = p.matcher(msg);
						  msg = matcher.replaceAll(" ");
						  msg = msg.replaceAll("“", "")
						  .replaceAll("”", "")
						  .replaceAll("\"", "").replaceAll("/", "").replaceAll("‘", "").replaceAll("’", "").replaceAll("'", "").
						  replace("[quote]", "").replace("\\[/quote]", "").replace("[table]", "").
						  replace("[tr]", "").replace("[td]", "").replace("\\[/table]", "").replace("\\[/tr]", "").replace("\\[/td]", "").
						  replace("[img]", "").replace("[i]", "").replace("[b]", "").replace("\\[/img]", "").replace("\\[/i]", "").replace("\\[/b]", "").
						  replace("<p>", "").replace("</p>", "").replace("&nbsp", "").replace("<br/>", "").replace("<br>", "").replace("<li/>", "").replace("<li>", "")
						  .replace("<span/>", "").replace("<span>", "").replace("<ol/>", "").replace("<ol>", "").replace("<strong/>", "").replace("<strong>", "")
						  .replace(";", "").replace("?", "问号").replace("<img", "").replace("src= >", "").replace("<strong/>", "").replace("<strong>", "").replace("宋体", "")
						  ;
		
							  msg = msg.replace("alignleft", "").replace("align", "").replaceAll("left", "").replaceAll("right", "");
						  System.out.println("分词前："+msg);
							  String wordstr = NlpirMethod.NLPIR_ParagraphProcess(msg, 0);

							  ChineseJF chinesdJF = CJFBeanFactory.getChineseJF();
							  wordstr = chinesdJF.chineseFan2Jan(wordstr);
						wordstr = wordstr.replaceAll( "[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , "");   //
						wordstr = wordstr.replaceAll( "　" , "");
						wordstr = wordstr.replaceAll("[a-zA-Z]","" );
						wordstr = wordstr.replaceAll("\\d+", "");
						
// delete excess space 
						Pattern pat = Pattern.compile("\\s+");
						Matcher mat = pat.matcher(wordstr);
						wordstr= mat.replaceAll(" ");
						wordstr = wordstr.trim();
						
						  System.out.println("分词后："+wordstr);

						  tx.setMessage(wordstr);
						  allText.add(tx);
	//					  count++;
			      }
			  }
			  // save StudentID fileList
			      
			  Vector<String> studentID = new Vector<String>() ;
			  for(int i = 0;i < allText.size();i++) {
				  if(studentID.indexOf(allText.get(i).getAuthor()) == -1)
					  studentID.add(allText.get(i).getAuthor());
			  }
			  System.out.println(studentID.size());
			  
			  File fileStudentID=new File(fileList);
			  BufferedWriter fileStudentIDw=new BufferedWriter(new FileWriter(fileStudentID));
			  for(int j = 0; j < studentID.size(); j++) {
				  fileStudentIDw.write(studentID.get(j)+".txt");
				  fileStudentIDw.newLine();
			  }
			  fileStudentIDw.close();
	/*		  for(int i = 0;i < allText.size();i++) {
				  System.out.println(allText.get(i).getAuthor());
				  System.out.println(allText.get(i).getTimeStr());
				  System.out.println(allText.get(i).getMessage());
				  System.out.println(allText.get(i).getCognitiveBehavior());
			  }
			  */
			  
			  File all=new File(savePath+ "AllPost" +".txt");
			  BufferedWriter All=new BufferedWriter(new FileWriter(all));
			  for(int i = 0; i < allText.size(); i++) {
				  All.write(
						  allText.get(i).getCognitiveBehavior()
						  + "M"
						  + allText.get(i).getMessage()
							+"\r\n"
						  );
			  }
			  All.close(); 
			  
	//save all sentence,source is forum document path
			  for(int j = 0; j < studentID.size(); j++) {
				  File f=new File(savePath+studentID.get(j)+".txt");
				  BufferedWriter bw=new BufferedWriter(new FileWriter(f));
				  int studentPostNumber = 0;
				  int student = Integer.parseInt(studentID.get(j));
				  int student2 = 0;
				  for(int i = 0;i < allText.size();i++) {
					  // System.out.println(i);
					  student2 = Integer.parseInt(allText.get(i).getAuthor());
					  			if((student2 == student) && allText.get(i).getMessage().length() > 2)
					  				{
					  				
					  				bw.write(
					  							allText.get(i).getTimeStr()
					  							+ ":"
					  							+"00: "
					  							+ allText.get(i).getMessage()
					  							+ "	"
					  							+ allText.get(i).getCognitiveBehavior()
					  							+"\r\n"
					  							);
					  					bw.flush();
					  					//bw.newLine();
					  					studentPostNumber ++;
					  				}
					  					
		        }
				  System.out.println("这个人总共发言得条数："+studentPostNumber);
		         bw.close(); 
			  }
		      
			  
		//	  System.out.println("total number of documents: "+allText.size());
	}

}
