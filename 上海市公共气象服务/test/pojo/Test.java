/**
 * 
 */
package pojo;

/**
 * @author 分裂状态。
 *
 */
public class Test {

	private Integer score;
	private Integer rankNo;

	public Test() {
	}

	public Test(Integer score) {
		this.score = score;
	}

	public Test(Integer score, Integer rankNo) {
		this.score = score;
		this.rankNo = rankNo;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getRankNo() {
		return rankNo;
	}

	public void setRankNo(Integer rankNo) {
		this.rankNo = rankNo;
	}

}
