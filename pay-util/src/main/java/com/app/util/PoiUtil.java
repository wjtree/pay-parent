package com.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * <p>功 能：POI EXCEL文档操作工具类</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月28日 下午5:44:42</p>
 * @author 王建
 * @version 1.0
 */
public class PoiUtil {
	private static final Logger LOG = LogManager.getLogger(PoiUtil.class);

	/**
	 * 将数据写入EXCEL
	 * @param <T>
	 * @param fileName EXCEL文件名称
	 * @param sheetName EXCEL页名
	 * @param data 待写入数据
	 * @throws Exception
	 */
	public static <T> void writeExcel(final String fileName, final String sheetName, final List<T[]> data) {
		OutputStream out = null;
		try {
			// 创建 EXCEL 工作簿
			XSSFWorkbook workBook = new XSSFWorkbook();
			// 创建 EXCEL Sheet 页
			XSSFSheet sheet = workBook.createSheet(sheetName);
			// 声明 EXCEL 行
			XSSFRow row;
			// 声明 EXCEL 单元格
			XSSFCell cell;

			// 迭代设置EXCEL每行数据
			int rowNo = 0; // 行号
			for (T[] objs : data) {
				row = sheet.createRow(rowNo++);
				// 迭代设置EXCEL当前行每个单元格数据
				int cellNo = 0; // 列号
				for (T obj : objs) {
					cell = row.createCell(cellNo++);
					cell.setCellValue(String.valueOf(obj));
				}
			}

			// 设置文件输出流，写入EXCEL数据
			String excelName = FileUtil.checkExtension(fileName, "xlsx");
			// 创建文件所在目录
			File file = new File(FilenameUtils.getFullPath(excelName));
			if (!file.exists())
				file.mkdirs();

			out = new FileOutputStream(excelName);
			// 写入文件流
			workBook.write(out);
			workBook.close();
		} catch (Exception e) {
			LOG.error("将数据写入EXCEL出错", e);
		} finally {
			try {
				if (null != out) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				LOG.error("关闭文件输出流出错", e);
			}
		}
	}

	/**
	 * 读取EXCEL中指定下标页的数据
	 * @param fileName
	 * @param sheetIndex
	 * @return
	 */
	public static List<String[]> readExcel(final String fileName, final Integer sheetIndex) {
		List<String[]> list = null;
		InputStream in = null;
		try {
			// 获取文件输入流
			String excelName = FileUtil.checkExtension(fileName, "xlsx");
			in = new FileInputStream(excelName);
			// 创建 EXCEL 工作簿
			XSSFWorkbook workBook = new XSSFWorkbook(in);
			// 获取 EXCEL Sheet 页
			XSSFSheet sheet = workBook.getSheetAt(sheetIndex);

			list = new ArrayList<String[]>();
			String[] strArr = null;
			// 遍历每行记录
			for (Row row : sheet) {
				strArr = new String[row.getPhysicalNumberOfCells()];
				// 遍历每单元格记录
				for (Cell cell : row) {
					// 根据单元格的类型获取不同数据类型的值
					CellType cellType = cell.getCellTypeEnum();
					if (CellType.NUMERIC.equals(cellType))
						strArr[cell.getColumnIndex()] = String.valueOf(cell.getNumericCellValue());
					else if (CellType.STRING.equals(cellType))
						strArr[cell.getColumnIndex()] = cell.getStringCellValue();
				}
				// 将数据放入集合
				list.add(strArr);
			}

			workBook.close();
		} catch (Exception e) {
			LOG.error("读取EXCEL中指定下标页的数据出错", e);
		} finally {
			try {
				if (null != in)
					in.close();
			} catch (IOException e) {
				LOG.error("关闭文件输入流出错", e);
			}
		}
		return list;
	}
}