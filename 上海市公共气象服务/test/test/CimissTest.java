/**
 * 
 */
package test;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * @author 分裂状态。
 *
 */
public class CimissTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Integer[] staId = { 58461, 58463 };
		// String s =
		// "[{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'1','TEM_Avg':'26.4','TEM_Max':'32.1','TEM_Min':'20.8','PRE_Time_2008':'999990','PRE_Time_0820':'1.3','PRE_Time_2020':'1.3','PRE_Time_0808':'1.3'}]";
		// if (s.contains("''")) {
		// System.out.println("有空");
		// } else {
		// System.out.println("没空");
		// }
		Integer[] staId = { 58461, 58463, 58361, 58367, 58370, 58366, 58462, 58365, 58362, 58460, 58369 };
		int count = 0;
		for (Integer s : staId) {
			count = jieguo(new Integer[] { s.intValue() }, count);
		}
		if (count > 0) {
			System.out.println(count);
		}
	}

	public static int jieguo(Integer[] staId, int count) {
		for (Integer s : staId) {
			if (s > 50000) {
				count++;
			}
		}
		return count;
	}

	public void result() {
		// 10.228.89.55/cimiss-web/api?userId=BCSH_SMMC_PwscSys&pwd=smmc@pwsc1.8sys&interfaceId=getSurfEleByTimeRangeAndStaIDRange&elements=Station_Name,Station_Id_d,Year,Mon,Day,TEM_Avg,TEM_Max,TEM_Min,PRE_Time_2008,PRE_Time_0820,PRE_Time_2020,PRE_Time_0808&timeRange=[20171101000000,20171101000000]&dataCode=SURF_CHN_MUL_DAY&dataFormat=JSON&maxStaId=58461&minStaId=58461
		org.apache.commons.httpclient.HttpClient httpclient = new org.apache.commons.httpclient.HttpClient();
		String url = "10.228.89.55/cimiss-web/api?userId=BCSH_SMMC_PwscSys&pwd=smmc@pwsc1.8sys&interfaceId=getSurfEleByTimeRangeAndStaIDRange&elements=Station_Name,Station_Id_d,Year,Mon,Day,TEM_Avg,TEM_Max,TEM_Min,PRE_Time_2008,PRE_Time_0820,PRE_Time_2020,PRE_Time_0808&timeRange=[20171101000000,20171101000000]&dataCode=SURF_CHN_MUL_DAY&dataFormat=JSON&maxStaId=58461&minStaId=58461";
		PostMethod post = new PostMethod(url);
		post.addRequestHeader("Content-type", "application/x-www-form-urlencoded");
		try {
			int status = httpclient.executeMethod(post);
			if (status == 200) {
				byte[] arr = post.getResponseBody();
				String conResult = new String(arr, "UTF-8");
				// conResult = new Gson().toJson(conResult);
				String s2 = conResult.replaceAll(".*\"DS\"", "").replace("]}", "]");
				System.out.println(s2);
			}

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
