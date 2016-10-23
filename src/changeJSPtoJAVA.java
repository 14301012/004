import java.util.ArrayList;



/**
 * jsp转java转换器
 * 
 * @author Jerome
 *
 */
public class changeJSPtoJAVA {

	private String head;
	private String fName;

	public changeJSPtoJAVA(String fName) {
		this.fName = fName;
	}

	

	private String add(String str) {
		String header = "";
		header = addHeader();
		String a ="import javax.servlet.ServletRequest;"+"\n"+"import javax.servlet.ServletResponse;"
		+"\n"+"import engine.Printable;"+"\n"+"import increate.InCreatedJSP;"+"\n"+"public class ClassName extends InCreatedJSP implements Printable {"
				+"\n"+"public ClassName(ServletRequest request, ServletResponse response) {"+"\n"
		+"super(request, response);}"+"\n"+"public void print() {";
		a = a.replace("ClassName", fName);
		String b = "\n"+"}}"+"\n";
		return header + a + str + b;
	}

	private String trimStr(String str) {
		int lastIndex = str.lastIndexOf("<%@");
		int nextIndex = str.indexOf("%>", lastIndex);
		head = str.substring(0, nextIndex + 2);
		str = str.substring(nextIndex + 2);
		return str;
	}

	

	private void judgeFormat(String str) throws MyException {
		String[] splited = str.split("<%");
		for (int i = 1; i < splited.length; i++) {
			String string = splited[i];
			if (!string.contains("%>"))
				throw new MyException(0);
		}
	}
	
	
	private String replace(String str) {
		str = str.replace("<%=", " + ");
		str = str.replace("<%", "\");");
		str = str.replace("\"", "\\\"");
		str = str.replace("%>", "out.write(\"");

		int index = str.indexOf("\");");
		str = str.substring(index + 3);
        str += "\");";

		return str;
	}

	// 提取出 <%@ 部分的内容到java文件的顶部
	private String addHeader() {
		String header = "";

		ArrayList<String> listImports = new ArrayList<String>();
		int lastIndex = 0;
		while (true) {
			int index = head.indexOf("import=\"", lastIndex);
			if (index == -1)
				break;
			lastIndex = head.indexOf("\"", index + 8);
			listImports.add(head.substring(index + 8, lastIndex));
		}

		for (String str : listImports) {
			header += "import " + str + ";\r\n";
		}
		header += "\r\n";

		return header;
	}
public String chage(String str) throws MyException {

		judgeFormat(str);
		str = trimStr(str);	
		str = replace(str);
		str = add(str);
		return str;
	}
}