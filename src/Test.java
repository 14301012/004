import java.io.FileNotFoundException;
import java.io.PrintWriter;


/**
 * 测试类，程序入口
 * 
 * @author Jerome
 *
 */
public class Test {

	private final static String INPUTPATH = "jsp/";
	private final static String OUTPUTPATH = "output/";

	public static void main(String[] args) {
		String filename = "index.jsp";
		Test test = new Test();
		test.start(filename);
	}

	private void start(String filename) {
		// 获取文件名
		int index = filename.indexOf(".jsp");
		if (index == -1) {
			System.out.println("不是jsp文件！ ");
			return;
		}
		String fName = filename.substring(0, index);
		/* 读取文件 */
		String bigStr = "";
		bigStr = read.readFromFile(INPUTPATH + filename);
		/* 转换 */
		changeJSPtoJAVA convertor = new changeJSPtoJAVA(fName);
		String convertedString = "";
		try {
			convertedString = convertor.chage(bigStr);
		} catch (MyException e) {
			switch (e.a) {
			case 0:
				System.err.println("JSP格式错误");
				break;
			default:
				System.err.println("未知错误");
				break;
			}
			System.exit(0);
		}
		System.out.print("编译后： \n" + convertedString);
		/* 写入文件 */
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(OUTPUTPATH + fName + ".java");
			pw.write(convertedString);
			pw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}

}