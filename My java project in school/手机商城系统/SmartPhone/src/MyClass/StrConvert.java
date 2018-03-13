package MyClass;

public class StrConvert {
	public String chStr(String str, String charSet) {
		if (str == null){
			str = "";
		}
		else{
			try{
				str = (new String(str.getBytes("ISO-8859-1"), charSet));
				str = str.trim();
			}catch(Exception e){
				System.out.println("chStr: " + e.getMessage());
			}
		}		
		
		return str;
	}
}
