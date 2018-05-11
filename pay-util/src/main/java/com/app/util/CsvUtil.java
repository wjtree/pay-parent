package com.app.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>功 能：CSV文件操作工具类</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月29日 上午9:54:45</p>
 *
 * @author 王建
 * @version 1.0
 */
public class CsvUtil {
    private static final Logger LOG = LogManager.getLogger(CsvUtil.class);

    /**
     * 将数据写入CSV
     *
     * @param fileName
     * @param data
     */
    public static void writeCSV(final String fileName, final List<String[]> data) {
        CSVWriter writer = null;
        try {
            // 校验文件后缀名
            String csvName = FileUtil.checkExtension(fileName, "csv");
            // 创建文件所在目录
            File file = new File(FilenameUtils.getFullPath(csvName));
            if (!file.exists())
                file.mkdirs();

            writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(csvName), StandardCharsets.UTF_8.name()), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            writer.writeAll(data);
        } catch (Exception e) {
            LOG.error("将数据写入CSV出错", e);
        } finally {
            if (null != writer) {
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    LOG.error("关闭文件输出流出错", e);
                }
            }
        }
    }

    /**
     * 读取CSV数据
     *
     * @param fileName
     * @return
     */
    public static List<String[]> readCSV(final String fileName) {
        List<String[]> list = null;
        CSVReader reader = null;
        try {
            String csvName = FileUtil.checkExtension(fileName, "csv");

            reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(csvName), StandardCharsets.UTF_8.name())).build();

            list = reader.readAll();
        } catch (Exception e) {
            LOG.error("读取CSV数据出错", e);
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOG.error("关闭文件输入流出错", e);
                }
            }
        }
        return list;
    }
}