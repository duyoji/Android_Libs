import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class StringUtil {

	// API専用
	public static String parameters2String(ArrayList<String> parameters) {
		StringBuffer stringBuffer = new StringBuffer("?");
		for (String parameter : parameters) {
			if (stringBuffer.length() > 1) {
				stringBuffer.append("&");
			}
			stringBuffer.append(parameter);
		}
		return stringBuffer.toString();
	}

	public static boolean isNull(String string) {
		if (string == null || string.length() == 0 || string.equals(""))
			return true;
		else
			return false;
	}

	public static boolean isNotNull(String string) {
		if (string != null && string.length() > 0 && !string.equals(""))
			return true;
		else
			return false;
	}

	public static String list2String(ArrayList<String> list) {
		if (list == null || list.size() == 0) {
			return "";
		} else {
			String string = "";
			for (String s : list) {
				string = string + s + ",";
			}
			return string.substring(0, string.length() - 1);
		}
	}
	
	public static ArrayList<String> string2List(String string){
		if (string == null || string.length() == 0 || string.equals("")) {
			return null;
		}
		else {
			ArrayList<String> list = new ArrayList<String>();
			String [] temp  =  string.split(",");
			for (int i = 0; i < temp.length; i++) {
				list.add(temp[i]);
			}
			return list;
		}
	}
	
	
	public static String getAddCommaScoreString(String stringScore){
		StringBuffer _score = new StringBuffer(stringScore);				
		
		int j = 1;
		
		for (int i = 0; i < _score.length(); i++) {
			if(0 < _score.length()-(3*(j)+i)){
				_score.insert(_score.length()-(3*(j)+i),",");
				j++;
			}
			else
				break;
		}
		
		
		return _score.toString();
	}

	
	public static String jsonObject2String(JSONObject jsonObj){
		String string = jsonObj.toString();		
		return string;
	}
	
	
	public static JSONObject string2JsonObject(String jsonString){
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject(jsonString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObj;
	}
	

}