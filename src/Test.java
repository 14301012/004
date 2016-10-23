import java.io.FileNotFoundException;
import java.io.PrintWriter;


/**
 * �����࣬�������
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
		// ��ȡ�ļ���
		int index = filename.indexOf(".jsp");
		if (index == -1) {
			System.out.println("����jsp�ļ��� ");
			return;
		}
		String fName = filename.substring(0, index);
		/* ��ȡ�ļ� */
		String bigStr = "";
		bigStr = read.readFromFile(INPUTPATH + filename);
		/* ת�� */
		changeJSPtoJAVA convertor = new changeJSPtoJAVA(fName);
		String convertedString = "";
		try {
			convertedString = convertor.chage(bigStr);
		} catch (MyException e) {
			switch (e.a) {
			case 0:
				System.err.println("JSP��ʽ����");
				break;
			default:
				System.err.println("δ֪����");
				break;
			}
			System.exit(0);
		}
		System.out.print("����� \n" + convertedString);
		/* д���ļ� */
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