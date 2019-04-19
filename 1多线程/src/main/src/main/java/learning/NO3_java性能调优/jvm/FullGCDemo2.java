package learning.NO3_java性能调优.jvm;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import java.io.File;

// 使用server模式运行 开启GC日志
// -Xmx512m -server -verbose:gc -XX:+PrintGCDetails
// 很多人都会建议的规避System.gc带来的fullgc风险  -XX:+DisableExplicitGC 禁止程序显示调用gc方法
public class FullGCDemo2 {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1000; i++) {
            WorkbookSettings workbookSettings = new WorkbookSettings();
            // 关闭显示GC
            //workbookSettings.setGCDisabled(true);
            // new File 路径中不能有空格
            File file = new File(FullGCDemo2.class.getClassLoader().getResource("files/FullGCDemo2.xls").getFile());
            Workbook book = Workbook.getWorkbook(file, workbookSettings);
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            // 得到第一列第一行的单元格
            Cell cell1 = sheet.getCell(0, 0);
            String result = cell1.getContents();
            System.out.println(result);
            book.close(); // 第三方依赖包，内部可能适用了system.gc()
            Thread.sleep(2000L);
        }
    }
}
