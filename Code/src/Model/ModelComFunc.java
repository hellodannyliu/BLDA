package Model;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import Common1.ComUtil;


/**  
* @ClassName: ModelComFunc  
* @Description: TODO(多个写入方法重载)  
* @author danny  
* @date 2019年11月4日  
*    
*/  
public class ModelComFunc {
	public static void writeData(float[] array, ArrayList<String> strings,
			ArrayList<Integer> rankList, BufferedWriter writer, String prefix) {
		PrintWriter writer2 = new PrintWriter(writer);
		writer2.printf("%s%s\n", "backGroundWord,", "prablity");
		for (int row = 0; row < rankList.size(); row++) {
			writer2.printf("%s%s%f\n", strings.get(rankList.get(row)),",",
					array[rankList.get(row)]);
//			writer2.printf(prefix + "\t",
//					strings.get(rankList.get(row)) + "\t" + array[rankList.get(row)] + "\n");
		}
	}
	public static void writeDataList(float[] array, ArrayList<String> strings,
			ArrayList<Integer> rankList, BufferedWriter writer, String prefix) {
		PrintWriter writer2 = new PrintWriter(writer);
		for (int row = 0; row < rankList.size() - 1; row++) {
			writer2.printf("%s%s%f%s", strings.get(rankList.get(row)),"-",
					array[rankList.get(row)],",");
		}
		writer2.printf("%s%s%f%s", strings.get(rankList.size() - 1),"-",
				array[rankList.size() - 1],"\n");
	}
	public static void writeData(float[] vPhiB2, BufferedWriter writer) {
		PrintWriter writer2 = new PrintWriter(writer);
		writer2.printf("%s\n","swift-backgroundWord,switf-topicWords");
		writer2.printf("%f%s", vPhiB2[0],",");
		writer2.printf("%f\n", vPhiB2[1]);
		
	}

//	public static void writeData(ArrayList<Integer>[] cNP2, BufferedWriter writer) {
//		PrintWriter writer2 = new PrintWriter(writer);
//		writer2 = new PrintWriter(writer);
//		for (int i = 0; i < cNP2.length; i++) {
//			// writer2.printf("%d-th topic:\n", i);
//			for (int j = 0; j < cNP2[i].size(); j++) {
//				// writer2.printf("%s,\t", Doc.getNps().get(cNP2[i].get(j)));
//			}
//			writer2.print("\n\n");
//		}
//	}

	public static void writeData(int[][] phi2, PrintWriter writer2) {
		for (int row = 0; row < phi2.length; row++) {
			// writer2.printf("%d", row);
			for (int col = 0; col < phi2[row].length; col++) {
				writer2.printf("%d\t", phi2[row][col]);
//				writer2.printf(phi2[row][col] + "\t");
			}
			writer2.print("\n");
		}
	}

	public static void writeData(double[][] vph2, PrintWriter writer2) {
		for (int row = 0; row < vph2.length; row++) {
			// writer2.printf("%d", row);
			for (int col = 0; col < vph2[row].length; col++) {
				writer2.printf("\t%d", vph2[row][col]);
//				writer2.printf("\t" + vph2[row][col]);
			}
			writer2.print("\n");
		}
	}

	public static void writeData(double[] phi2, PrintWriter writer2) {
		for (int row = 0; row < phi2.length; row++) {
			writer2.printf("\t%d", phi2[row]);
//			writer2.printf("\t" + phi2[row]);
		}
	}
	public static void writeDataPsi(float[][] psi, ArrayList<String> uniItemMap, Sheet sheet5) {
		
		Row row = sheet5.createRow(0);
		row.createCell(0).setCellValue("topic");
		
		for(int item = 0;item < uniItemMap.size();item ++) {
			row.createCell(item + 1).setCellValue((uniItemMap.get(item).replace("#PB#", "")));
		}
			
		System.out.println("psi-topic:"+psi.length +"psi-items:"+psi[0].length );
	
		for (int topic = 0; topic < psi.length; topic++) {
			row = sheet5.createRow(topic + 1);
			row.createCell(0).setCellValue("topic-" + (topic+1));
			for(int item = 0;item < psi[0].length;item ++) {
				row.createCell(item + 1).setCellValue(psi[topic][item]);
			}
			
	
		}
		
		
	}
	public static void writeDataTheta(float[][] theta , Sheet sheet3,ArrayList<String> files) {
		Row row = sheet3.createRow(0);
		row.createCell(0).setCellValue("author");
		row.createCell(1).setCellValue("grade");
		row.createCell(2).setCellValue("postCount");

		for(int topic = 0;topic < theta[0].length; topic++) {
			row.createCell(topic + 3).setCellValue("topic-"+(topic + 1));
		}
		//get all topic cells
		for(int authorNumber = 0,rowNumber = 1;authorNumber < files.size(); authorNumber ++,rowNumber++) {
			row = sheet3.createRow(rowNumber);
			row.createCell(0).setCellValue(files.get(authorNumber).replaceAll(".txt", ""));
			
			//row.createCell(2).setCellValue(docs.);
			for(int topic = 0;topic < theta[0].length;topic ++) {
				row.createCell(3 + topic).setCellValue(theta[authorNumber][topic]);
			}
		}
	
	}
	public static void writeDatavPhiB(float[] vPhiB, ArrayList<String> uniWordMap, Sheet sheet1) {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> rankList = new ArrayList<Integer>();
		ComUtil.getTop(vPhiB, rankList, 30);
		Row row = sheet1.createRow(0);
		row.createCell(0).setCellValue("Word");
		row.createCell(1).setCellValue("prability");
		for(int wordID = 0,row1= 1; wordID < rankList.size(); wordID++,row1++) {
			row = sheet1.createRow(row1);
			row.createCell(0).setCellValue(uniWordMap.get(rankList.get(wordID)));
			row.createCell(1).setCellValue(vPhiB[rankList.get(wordID)]);
		}

		rankList.clear();
	}
	public static void writeDataSwiftPhi(float[] phi, Sheet sheet2) {
		// TODO Auto-generated method stub
		Row row = sheet2.createRow(0);
		row.createCell(0).setCellValue("Word");
		row.createCell(1).setCellValue("prability");
		row = sheet2.createRow(1);
		row.createCell(0).setCellValue(phi[0]);
		row.createCell(1).setCellValue(phi[1]);

	}
	public static void writeDatavPhi(float[][] vPhi, ArrayList<String> uniWordMap,Sheet sheet4) {
		// TODO Auto-generated method stub
		ArrayList<Integer> rankList = new ArrayList<Integer>();
		Row row = sheet4.createRow(0);
		for (int topic = 0; topic < vPhi.length; topic++) {
		row.createCell(0 + topic*2).setCellValue("topic-" + (topic + 1));
		row.createCell(1 + topic*2).setCellValue("prability");
		}
		ComUtil.getTop(vPhi[0], rankList, 30);
		for(int wordID = 0,row1= 1; wordID < rankList.size(); wordID++,row1++) {
			row = sheet4.createRow(row1);
			row.createCell(0).setCellValue(uniWordMap.get(rankList.get(wordID)));
			row.createCell(1).setCellValue(vPhi[0][rankList.get(wordID)]);
		}
			
		
		for (int topic = 1; topic < vPhi.length; topic++) {
			rankList.clear();
			ComUtil.getTop(vPhi[topic], rankList, 30);
			for(int wordID = 0,row1= 1; wordID < rankList.size(); wordID++,row1++) {
				row = sheet4.getRow(row1);
				row.createCell(0 + topic*2).setCellValue(uniWordMap.get(rankList.get(wordID)));
				row.createCell(1 + topic*2).setCellValue(vPhi[topic][rankList.get(wordID)]);
			}
			
			
		}
		rankList.clear();
	}
	public static void writeDataAuthorBehavior(ArrayList<Document> docs, ArrayList<String> uniItemMap, Sheet sheet6, ArrayList<String> files) {
		// TODO Auto-generated method stub
		//creat table head
		Row row = sheet6.createRow(0);
		row.createCell(0).setCellValue("author");
		row.createCell(1).setCellValue("grade");
		for(int item = 0;item < uniItemMap.size(); item ++) {
			row.createCell(item + 2).setCellValue((uniItemMap.get(item).replace("#PB#", "")));
		}	
		//creat author-items element
		for(int authorNumber = 0,rowNumber = 1;authorNumber < files.size(); authorNumber ++,rowNumber++) {
			row = sheet6.createRow(rowNumber);
			row.createCell(0).setCellValue(files.get(authorNumber).replaceAll(".txt", ""));
			// initial author's items
			for(int item = 0; item < uniItemMap.size(); item++) {
				row.createCell(item +2).setCellValue(0);
			}
			
			for(int doc = 0;doc < docs.get(authorNumber).docItems.length;doc ++) {
				for (int k2 = 0; k2 < docs.get(authorNumber).getDocItems()[doc].length; k2++) {
					int temp = (int)row.getCell(docs.get(authorNumber).getDocItems()[doc][k2] + 2).getNumericCellValue();
					row.getCell(docs.get(authorNumber).docItems[doc][k2] +2).setCellValue(temp +1);
				}
			}
		}
	}
}
