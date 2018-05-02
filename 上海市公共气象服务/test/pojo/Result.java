/**
 * 
 */
package pojo;

/**
 * @author 分裂状态。
 *
 */
public class Result {

	public Integer id;
	public String mon;
	public String year;

	public Result() {
	}

	public Result(String year) {
		this.year = year;
	}

	public Result(String mon, String year) {
		this.mon = mon;
		this.year = year;
	}

	public Result(Integer id, String mon, String year) {
		this.id = id;
		this.mon = mon;
		this.year = year;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
