/**
 * 
 */
package com.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.ModDao;
import com.entity.Mod;
import com.pages.QueryResult;
import com.service.ModService;

/**
 * @author 分裂状态。
 *
 */
@Service
public class ModServiceImpl implements ModService {

	@Autowired
	private ModDao moddao;

	public List<String> getYearMod() {
		return moddao.getYearMod();
	}

	public String getMaxYearMod() {
		return moddao.getMaxYearMod();
	}

	public List<Mod> getAllMod() {
		return moddao.getAllMod();
	}

	public QueryResult<Mod> listMod(Pageable page) {
		return new QueryResult<Mod>(moddao.findAll(page));
	}

	public void saveMod(Mod mod) {
		moddao.save(mod);
	}

	public Mod getModById(Integer id) {
		return moddao.getModById(id);
	}

	public void updateMod(Integer year, Date modDay, Date meiDay, Integer meiyuLength, Double meiyuAmount, Integer id) {
		moddao.updateMod(year, modDay, meiDay, meiyuLength, meiyuAmount, id);
	}

	public void deleteInfo(Integer id) {
		moddao.delete(id);
	}

}
