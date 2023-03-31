package com.lec.lw.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.lw.dao.MainDao;
import com.lec.lw.dao.NboardDao;
import com.lec.lw.dto.FboardDto;
import com.lec.lw.dto.GboardDto;
import com.lec.lw.dto.NboardDto;
import com.lec.lw.dto.VboardDto;

public class MainPageService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MainDao mainDao = MainDao.getInstance();
		ArrayList<NboardDto> nboardList = mainDao.mainNboard();
		request.setAttribute("nboardList", nboardList);
		
		ArrayList<GboardDto> gboardList = mainDao.mainGboard();
		request.setAttribute("gboardList", gboardList);
		
		ArrayList<FboardDto> fboardList = mainDao.mainFboard();
		request.setAttribute("fboardList", fboardList);
		
		ArrayList<VboardDto> vboardList = mainDao.mainVboard();
		request.setAttribute("vboardList", vboardList);
	}

}
