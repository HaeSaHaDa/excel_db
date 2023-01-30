package solomon.ex.xslx;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aspose.cells.Workbook;

import solomon.ex.xslx.dao.InsertDao;

public class ExcelFileWrite {

	public void xlsxReadWrite(String in_filePath, String out_filepath, Integer no) throws Exception {
		//원본 엑셀파일 
		File in_file = new File(in_filePath);
		
		//파일 생성 path
		final String out_filename = no.toString() + ".xlsx";
		String out_file = out_filepath + out_filename;
		System.out.println(out_file);
		
		//sheet 번호 생성
		String sheet_no = "rId" + no.toString();

		//no에 따른 엑셀시트 불러오기
		ExcelSheetHandler excelSheetHandler = ExcelSheetHandler.readExcel(in_file, sheet_no);
		//엑셀시트 데이터 불러오기
		List<List<String>> excelDatas = excelSheetHandler.getRows();

		
		//엑셀시트별로 엑셀파일 생성
		XSSFWorkbook xwb = new XSSFWorkbook();
		XSSFSheet xsheet = xwb.createSheet();
		XSSFRow xrow = null;
		XSSFCell xcell = null;

		for (int i = 0; i < excelDatas.size(); i++) {
			xrow = xsheet.createRow(i);

			List<String> dataRow = excelDatas.get(i);
			for (int j = 0; j < dataRow.size(); j++) {
				xcell = xrow.createCell(j);
				xcell.setCellValue(dataRow.get(j));
			}

		}

		File file = new File(out_file);
		FileOutputStream fos = new FileOutputStream(file);
		xwb.write(fos);

		if (fos != null) {
			fos.flush();
			fos.close();
		}
			
		System.out.println(no + ".xlsx 파일 생성!");


		//생성된 엑셀파일별로 CSV파일 생성
		String csv_file = out_filepath + no + ".csv";
		csvFileWrite(out_filepath + no + ".xlsx", out_filepath, no);



	}

	public void csvFileWrite(String in_file, String out_filepath, Integer no) throws Exception {

		// no 엑셀파일 불러오기
		File excel = new File(in_file);
		ExcelSheetHandler excelSheetHandler = ExcelSheetHandler.readExcel(excel);
		List<List<String>> excelDatas = excelSheetHandler.getRows();

		String out_filename = out_filepath + no.toString() + ".csv";
		File csv = null;
		BufferedWriter bw = null;

		try {
			csv = new File(out_filename);
			bw= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csv),"UTF8"));
			for (int i = 0; i < excelDatas.size(); i++) {
				List<String> dataRow = excelDatas.get(i);
				for (int j = 0; j < dataRow.size(); j++) {
					bw.write(dataRow.get(j).trim()+";");
				}
				bw.newLine();
				
			}
		} catch (Exception e) {
			System.out.println("csv 파일 생성 실패!");
			e.printStackTrace();
		}finally {
			if(bw != null){
				bw.flush();
                bw.close();
            }
		}

		System.out.println(no + ".csv 파일생성 성공!!!!");

	}
}
