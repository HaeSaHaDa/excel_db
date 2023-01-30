package solomon.ex.xslx;

import solomon.ex.xslx.dao.InsertDao;

public class InsertExcute {

	private static final String in_filepath = "C:/okhtest/pre/"; // xlsx 형식
	private static final String in_filename = "20230109_ugo_data_value.xlsx";
	private static final String out_filepath = "C:/okhtest/post/"; // xlsx 형식

	public void Insert() throws Exception {
		String in_filePath = in_filepath + in_filename;
		ExcelFileWrite efw = new ExcelFileWrite();
		InsertDao id = new InsertDao();
		
		for (int i = 1; i <= 4; i++) {
			efw.xlsxReadWrite(in_filePath, out_filepath, i);
			id.excute(out_filepath + i+".csv", i);

		}
	}



}
