package com.app.util;

import com.app.code.Extension;
import com.app.code.Symbol;
import com.app.core.VariableHolder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * <p>功 能：文件与目录操作工具类</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月22日 下午10:33:39</p>
 *
 * @author WangJian
 * @version 1.0
 */
public class FileUtil {
    /**
     * 获取年月日格式的文件路径
     * <p>返回路径示例：${path}/yyyy/mm/dd/</p>
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static String getPathByDate(String path) throws Exception {
        LocalDate local = LocalDate.now();
        StringBuilder sb = VariableHolder.getStringBuilder().append(path).append(local.getYear()).append(File.separator).append(local.getMonthOfYear()).append(File.separator).append(local.getDayOfMonth()).append(File.separator);
        return sb.toString();
    }

    /**
     * 获取目录下指定名称前缀的文件数组
     *
     * @param file   文件目录
     * @param prefix 文件名称前缀
     * @return
     * @throws Exception
     */
    public static File[] listFiles(File file, final String prefix) throws Exception {
        return file.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String preName = StringUtils.substringBeforeLast(name, Symbol.UNDERSCORE.getSymbol());
                if (preName.equalsIgnoreCase(prefix))
                    return true;
                else
                    return false;
            }
        });
    }

    /**
     * 将字符串写入文件
     *
     * @param fileName 文件名全路径
     * @param content  文件内容
     * @throws Exception
     */
    public static void writeStringToFile(String fileName, String content) throws Exception {
        // 因为是追加内容到文件中，所以每次追加的文件内容后都要加换行符
        FileUtils.writeStringToFile(new File(fileName), content + Symbol.LINE_BREAK.getSymbol(), StandardCharsets.UTF_8.name(), true);
    }

    /**
     * 获取文件全路径名
     * <p>返回示例：${prefixPath}/yyyy/MM/dd/${prefixName}_${num}.txt</p>
     *
     * @param prefixPath 路径前缀
     * @param prefixName 文件名前缀
     * @param fileSize   文件大小，单位：MB
     * @return
     * @throws Exception
     */
    public static String getFileName(String prefixPath, String prefixName, Integer fileSize) throws Exception {
        // 获取文件存放路径
        String path = FileUtil.getPathByDate(prefixPath);
        // 返回的日志文件全路径字符串模板
        String nameTemplet = path + prefixName + Symbol.UNDERSCORE.getSymbol() + "#NUM#" + Extension.TXT.getExtension();
        // 如果目录不存在，或者目录存在但不存在文件，均使用该文件路径名
        String logFileName = StringUtils.replace(nameTemplet, "#NUM#", "0");

        File root = new File(path);
        // 检查目录是否存在
        if (root.exists()) {
            File[] files = FileUtil.listFiles(root, prefixName);
            // 检查是否存在文件名符合指定前缀的文件
            if (ArrayUtils.isNotEmpty(files)) {
                // 文件列表升序排列
                Arrays.sort(files);
                // 获取名称中数字最大的文件对象
                File maxFile = files[files.length - 1];
                // 此处转换将MB为字节，如果该文件超过了 LOG_SIZE 限制的大小，则重新生成文件
                long size = fileSize * 1024 * 1024;
                if (maxFile.length() < size) {
                    logFileName = maxFile.getPath();
                } else {
                    // 将文件名称后缀累加1
                    Integer num = Integer.valueOf(StringUtils.substringBetween(maxFile.getName(), prefixName + Symbol.UNDERSCORE.getSymbol(), Symbol.DOT.getSymbol())) + 1;
                    logFileName = StringUtils.replace(nameTemplet, "#NUM#", num.toString());
                }
            }
        }

        return logFileName;
    }

    /**
     * 检查文件类型是否为指定扩展名，如果不是，则按扩展名重新组装文件名
     *
     * @param fileName  文件名
     * @param extension 扩展名
     * @return
     * @throws Exception
     */
    public static String checkExtension(final String fileName, final String extension) throws Exception {
        if (FilenameUtils.isExtension(fileName, extension))
            return fileName;
        else
            return StringUtils.join(FilenameUtils.getFullPath(fileName), FilenameUtils.getBaseName(fileName), Symbol.DOT.getSymbol().concat(extension));
    }
}