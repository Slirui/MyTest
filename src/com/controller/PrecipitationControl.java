/**
 * 
 */
package com.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.entity.CommonModel;
import com.entity.Precipitation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pages.QueryResult;
import com.service.PrecipitationService;
import com.service.SiteMonthAvgService;
import com.task.Cimiss;
import com.util.Pageutil;

/**
 * @author 分裂状态。
 *
 */
@Controller
public class PrecipitationControl {

	@Autowired
	public PrecipitationService precipitationService;

	@Autowired
	public SiteMonthAvgService siteservice;

	@RequestMapping("listPrecipitation.shtml")
	public ModelAndView listPrecipitation(Model model, @RequestParam(defaultValue = "0") int start,
			@RequestParam(defaultValue = "10") int len, String name, String site) throws IOException {
		List<String> siteNames = siteservice.getSite();
		if (site == null) {
			site = "徐家汇";
		}
		if (name == null) {
			name = "徐家汇";
		}
		int count = precipitationService.getPreCount3(site);
		QueryResult<Precipitation> qr = precipitationService
				.findByName(Pageutil.getPageable(start, len, new Sort(Sort.Direction.DESC, "dateDate")), name);
		HashMap<String, Object> mparm = new HashMap<String, Object>();
		mparm.put("name", name);
		mparm.put("site", site);
		// qr.processPage("listPrecipitation.shtml", mparm, start, len);
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("qr", qr);
		model.addAttribute("siteNames", siteNames);
		model.addAttribute("site", site);
		model.addAttribute("precount", count);
		model.addAttribute("start", start);
		// model.addAttribute("name", name);
		return getModelAndView("precipitation2", mparm, qr, "listPrecipitation.shtml", start, len);
		// return new ModelAndView("precipitation", map);
	}

	@RequestMapping("getPreSiteName.shtml")
	public String getPreSiteName(Model model, String site) {
		List<String> siteNames = siteservice.getSite();
		if (site == null) {
			site = "徐家汇";
		}
		model.addAttribute("siteNames", siteNames);
		model.addAttribute("site", site);
		return "precipitationname";
	}

	@RequestMapping(value = "updatePrecipitation.shtml", method = { RequestMethod.POST })
	public String updatePrecipitation(Model model, CommonModel prelist) {
		List<Precipitation> list = prelist.getPrelist();
		for (Precipitation pre : list) {
			System.out.println("平均：" + pre.getTemAvg() + "最大：" + pre.getTemMax() + "最小：" + pre.getTemMin() + "雨量2008："
					+ pre.getPreTime2008() + "雨量0820：" + pre.getPreTime0820() + "雨量2020：" + pre.getPreTime2020()
					+ "雨量0808：" + pre.getPreTime0808());
			precipitationService.updatePrecipitation(pre.getTemAvg(), pre.getTemMax(), pre.getTemMin(),
					pre.getPreTime2008(), pre.getPreTime0820(), pre.getPreTime2020(), pre.getPreTime0808(),
					pre.getId());
		}
		return "redirect:/listPrecipitation.shtml";
	}

	@RequestMapping("testCimissData.shtml")
	public String testCimissData(String beginDate, String endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date begindate = sdf.parse(beginDate);
			Date enddate = sdf.parse(endDate);
			Integer[] staId = { 58461, 58463, 58361, 58367, 58370, 58366, 58462, 58365, 58362, 58460, 58369 };
			for (Integer s : staId) {
				sendSmsTest(new Integer[] { s.intValue() }, begindate, enddate);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "testData";
	}

	public void sendSmsTest(Integer[] staId, Date beginDate, Date endDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd000000");
		String begindateDay = sdf.format(beginDate);
		String enddateDay = sdf.format(endDate);
		HttpClient httpclient = new DefaultHttpClient();
		String url = "http://10.228.89.55/cimiss-web/api?userId=BCSH_SMMC_PwscSys&pwd=smmc@pwsc1.8sys&interfaceId=getSurfEleByTimeRangeAndStaIDRange&elements=Station_Name,Station_Id_d,Year,Mon,Day,TEM_Avg,TEM_Max,TEM_Min,PRE_Time_2008,PRE_Time_0820,PRE_Time_2020,PRE_Time_0808&timeRange="
				+ "[%s,%s]&dataCode=SURF_CHN_MUL_DAY&dataFormat=json&maxStaId=%s&minStaId=%s";
		Integer result = null;
		for (Integer in : staId) {
			result = in;
		}
		url = String.format(url, begindateDay, enddateDay, result, result);
		HttpPost httppost = new HttpPost(url);
		try {
			HttpResponse response = httpclient.execute(httppost);
			if (response.getStatusLine().getStatusCode() == 200) {
				String conResult = EntityUtils.toString(response.getEntity());
				String s2 = conResult.replaceAll(".*\"DS\"", "").replace("]}", "]");
				s2 = s2.substring(1);
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
	}

	@RequestMapping("listPrecipitation2.shtml")
	public ModelAndView listPrecipitation2(@RequestParam(defaultValue = "0") int start,
			@RequestParam(defaultValue = "10") int len, HttpServletRequest request, String name, Model model)
			throws IOException {
		QueryResult<Precipitation> qr = precipitationService
				.listPrecipitation(Pageutil.getPageable(start, len, new Sort(Sort.Direction.DESC, "dateDate")));
		return getModelAndView("precipitation", qr, "listPrecipitation2.shtml", start, len);
	}

	private ModelAndView getModelAndView(String page, QueryResult qr, String url, int start, int len) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mparm = new HashMap<String, Object>();
		qr.processPage(url, mparm, start, len);
		map.put("qr", qr);
		return new ModelAndView(page, map);
	}

	private ModelAndView getModelAndView(String page, HashMap<String, Object> mapparm, QueryResult qr, String url,
			int start, int len) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		qr.processPage(url, mapparm, start, len);
		map.put("qr", qr);
		return new ModelAndView(page, map);
	}

	public void sendSmsTest2(Integer[] staId) throws ParseException {
		Integer result = null;
		for (Integer in : staId) {
			result = in;
		}
		String json = "[{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'1','TEM_Avg':'26.4','TEM_Max':'32.1','TEM_Min':'20.8','PRE_Time_2008':'999990','PRE_Time_0820':'1.3','PRE_Time_2020':'1.3','PRE_Time_0808':'1.3'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'2','TEM_Avg':'','TEM_Max':'','TEM_Min':'','PRE_Time_2008':'999990','PRE_Time_0820':'7.3','PRE_Time_2020':'7.3','PRE_Time_0808':'7.4'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'3','TEM_Avg':'','TEM_Max':'','TEM_Min':'','PRE_Time_2008':'0.1','PRE_Time_0820':'0','PRE_Time_2020':'0.1','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'4','TEM_Avg':'19.8','TEM_Max':'22.3','TEM_Min':'18.1','PRE_Time_2008':'999990','PRE_Time_0820':'999990','PRE_Time_2020':'0','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'5','TEM_Avg':'','TEM_Max':'','TEM_Min':'','PRE_Time_2008':'0','PRE_Time_0820':'1','PRE_Time_2020':'1','PRE_Time_0808':'1'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'6','TEM_Avg':'19','TEM_Max':'22.8','TEM_Min':'17.2','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'7','TEM_Avg':'','TEM_Max':'','TEM_Min':'','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'8','TEM_Avg':'23','TEM_Max':'28.3','TEM_Min':'18.1','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'','PRE_Time_0808':''},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'9','TEM_Avg':'24.4','TEM_Max':'29.3','TEM_Min':'19.6','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'10','TEM_Avg':'26.8','TEM_Max':'30.8','TEM_Min':'23.3','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'41.5'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'11','TEM_Avg':'21','TEM_Max':'26.8','TEM_Min':'18.5','PRE_Time_2008':'41.5','PRE_Time_0820':'3.5','PRE_Time_2020':'45','PRE_Time_0808':'3.6'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'12','TEM_Avg':'17.6','TEM_Max':'19.1','TEM_Min':'16.2','PRE_Time_2008':'0.1','PRE_Time_0820':'1.2','PRE_Time_2020':'1.3','PRE_Time_0808':'1.6'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'13','TEM_Avg':'18.7','TEM_Max':'20.6','TEM_Min':'16.5','PRE_Time_2008':'0.4','PRE_Time_0820':'0','PRE_Time_2020':'0.4','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'14','TEM_Avg':'19.3','TEM_Max':'22','TEM_Min':'18.1','PRE_Time_2008':'0','PRE_Time_0820':'999990','PRE_Time_2020':'0','PRE_Time_0808':'5.7'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'15','TEM_Avg':'16.9','TEM_Max':'18.7','TEM_Min':'16','PRE_Time_2008':'5.7','PRE_Time_0820':'5.3','PRE_Time_2020':'11','PRE_Time_0808':'18.1'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'16','TEM_Avg':'','TEM_Max':'','TEM_Min':'','PRE_Time_2008':'12.8','PRE_Time_0820':'12.7','PRE_Time_2020':'25.5','PRE_Time_0808':'16.7'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'17','TEM_Avg':'','TEM_Max':'','TEM_Min':'','PRE_Time_2008':'4','PRE_Time_0820':'0','PRE_Time_2020':'4','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'18','TEM_Avg':'19.2','TEM_Max':'22.5','TEM_Min':'16.8','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'19','TEM_Avg':'18.3','TEM_Max':'20.9','TEM_Min':'16.8','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'20','TEM_Avg':'19','TEM_Max':'22.4','TEM_Min':'17.3','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'21','TEM_Avg':'18.3','TEM_Max':'22.3','TEM_Min':'15.2','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'22','TEM_Avg':'17.9','TEM_Max':'22.2','TEM_Min':'14.5','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'23','TEM_Avg':'16.2','TEM_Max':'18.9','TEM_Min':'13.1','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'24','TEM_Avg':'15.3','TEM_Max':'20.5','TEM_Min':'10.5','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'25','TEM_Avg':'15.1','TEM_Max':'21.9','TEM_Min':'8.4','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'26','TEM_Avg':'17.1','TEM_Max':'23','TEM_Min':'11','PRE_Time_2008':'999990','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'27','TEM_Avg':'16.9','TEM_Max':'22','TEM_Min':'12','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'28','TEM_Avg':'17.7','TEM_Max':'22.6','TEM_Min':'13.9','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'29','TEM_Avg':'15.4','TEM_Max':'19','TEM_Min':'13.6','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'30','TEM_Avg':'12.2','TEM_Max':'16.7','TEM_Min':'9','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'0'},{'Station_Name':'青浦','Station_Id_d':'58461','Year':'2017','Mon':'10','Day':'31','TEM_Avg':'','TEM_Max':'','TEM_Min':'','PRE_Time_2008':'0','PRE_Time_0820':'0','PRE_Time_2020':'0','PRE_Time_0808':'0'}]";
		json = json.replace("''", "null");
		List<Cimiss> s = new Gson().fromJson(json, new TypeToken<List<Cimiss>>() {
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
}
