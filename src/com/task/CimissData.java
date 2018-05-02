/**
 * 
 */
package com.task;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.entity.CimissCount;
import com.entity.Precipitation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.service.CimissCountService;
import com.service.PrecipitationService;

/**
 * @author 分裂状态。
 *
 */
@Component("CimissData")
public class CimissData {

	@Autowired
	private PrecipitationService precipitationService;

	@Autowired
	private CimissCountService countService;

	@Scheduled(cron = "0 0 1 * * ?")
	// @Scheduled(cron = "0/5 * * * * ?")
	public void getPreData() throws ParseException {
		Integer[] staId = { 58461, 58463, 58361, 58367, 58370, 58366, 58462, 58365, 58362, 58460, 58369 };
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 1; i < 6; i++) {
			int count = 0;
			Date date = null;
			for (Integer s : staId) {
				date = new Date();
				date.setDate(date.getDate() - i);
				String dateDate = sdf.format(date);
				if ((precipitationService.countPre(dateDate, s) > 0)) {
					continue;
				}
				count = sendSms(new Integer[] { s.intValue() }, date, count);
			}
			if (count > 0) {
				CimissCount cimisscount = new CimissCount();
				cimisscount.setDateDate(date);
				cimisscount.setCount(count);
				cimisscount.setActive(false);
				countService.saveCimissCount(cimisscount);
			}
		}

	}

	public int sendSms(Integer[] staId, Date date, int count) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd000000");
		String dateDay = sdf.format(date);
		HttpClient httpclient = new DefaultHttpClient();
		String url = "http://10.228.89.55/cimiss-web/api?userId=BCSH_SMMC_PwscSys&pwd=smmc@pwsc1.8sys&interfaceId=getSurfEleByTimeRangeAndStaIDRange&elements=Station_Name,Station_Id_d,Year,Mon,Day,TEM_Avg,TEM_Max,TEM_Min,PRE_Time_2008,PRE_Time_0820,PRE_Time_2020,PRE_Time_0808&timeRange="
				+ "[%s,%s]&dataCode=SURF_CHN_MUL_DAY&dataFormat=json&maxStaId=%s&minStaId=%s";
		Integer result = null;
		for (Integer in : staId) {
			result = in;
		}
		url = String.format(url, dateDay, dateDay, result, result);
		HttpPost httppost = new HttpPost(url);
		try {
			HttpResponse response = httpclient.execute(httppost);
			if (response.getStatusLine().getStatusCode() == 200) {
				String conResult = EntityUtils.toString(response.getEntity());
				String s2 = conResult.replaceAll(".*\"DS\"", "").replace("]}", "]");
				s2 = s2.substring(1);
				if (s2.contains("\"\"")) {
					count++;
				}
				s2 = s2.replace("\"\"", "null");
				List<Cimiss> s = new Gson().fromJson(s2, new TypeToken<List<Cimiss>>() {
				}.getType());
				SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd");
				for (int i = 0; i < s.size(); i++) {
					Precipitation pre = new Precipitation();
					pre.setStationName(s.get(i).getStation_Name());
					pre.setStationIdD(s.get(i).getStation_Id_d());
					pre.setDateYear(s.get(i).getYear());
					pre.setDateMon(s.get(i).getMon());
					pre.setDateDay(s.get(i).getDay());
					Integer year = s.get(i).getYear();
					Integer mon = s.get(i).getMon();
					Integer day = s.get(i).getDay();
					String yue = "";
					String ri = "";
					if (mon < 10) {
						yue = "0" + mon.toString();
					} else {
						yue = mon.toString();
					}
					if (day < 10) {
						ri = "0" + day.toString();
					} else {
						ri = day.toString();
					}
					String dateDate = year + "-" + yue + "-" + ri;
					pre.setDateDate(sdfTime.parse(dateDate));
					pre.setTemAvg(s.get(i).getTEM_Avg());
					pre.setTemMax(s.get(i).getTEM_Max());
					pre.setTemMin(s.get(i).getTEM_Min());
					pre.setPreTime0808(s.get(i).getPRE_Time_0808());
					pre.setPreTime0820(s.get(i).getPRE_Time_0820());
					pre.setPreTime2008(s.get(i).getPRE_Time_2008());
					pre.setPreTime2020(s.get(i).getPRE_Time_2020());
					String dateYearMonDay = 2016 + "-" + yue + "-" + ri;
					pre.setDateYearMonDay(sdfTime.parse(dateYearMonDay));
					if (!(precipitationService.countPre(dateDate, result) > 0)) {
						precipitationService.savePrecipitation(pre);
					} else {
						continue;
					}
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return count;
	}

	// @Scheduled(cron = "0/5 * * * * ?")
	public void resultCimiss() {
		HttpClient httpclient = new DefaultHttpClient();
		String url = "http://10.228.89.55/cimiss-web/api?userId=BCSH_SMMC_PwscSys&pwd=smmc@pwsc1.8sys&interfaceId=getSurfEleByTimeRangeAndStaIDRange&elements=Station_Name,Station_Id_d,Year,Mon,Day,TEM_Avg,TEM_Max,TEM_Min,PRE_Time_2008,PRE_Time_0820,PRE_Time_2020,PRE_Time_0808&timeRange="
				+ "[%s,%s]&dataCode=SURF_CHN_MUL_DAY&dataFormat=json&maxStaId=%s&minStaId=%s";
		url = String.format(url, "20171031000000", "20171031000000", 58461, 58461);
		HttpPost httppost = new HttpPost(url);
		try {
			HttpResponse response = httpclient.execute(httppost);
			if (response.getStatusLine().getStatusCode() == 200) {
				String conResult = EntityUtils.toString(response.getEntity());
				// conResult = new Gson().toJson(conResult);
				String s2 = conResult.replaceAll(".*\"DS\"", "").replace("]}", "]");
				s2 = s2.substring(1);
				System.out.println(s2);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
