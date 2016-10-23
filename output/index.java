import java.text.*,java.util.*;;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import engine.Printable;
import increate.InCreatedJSP;
public class index extends InCreatedJSP implements Printable {
public index(ServletRequest request, ServletResponse response) {
super(request, response);}
public void print() {     
         SimpleDateFormat format = new SimpleDateFormat(\"yyyy/MM/dd\");     
         String str = format.format(new Date());     
      out.write("     
       + str out.write("     
</body>     
</html> 
");
}}
