package com.engTecOutSouPlat.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engTecOutSouPlat.entity.*;
import com.engTecOutSouPlat.dao.*;
import com.engTecOutSouPlat.service.*;

@Service
public class ProOutSouServiceImpl implements ProOutSouService {

	@Autowired
	ProOutSouMapper proOutSouDao;
	
	@Override
	public int add(ProOutSou pos) {
		// TODO Auto-generated method stub
		return proOutSouDao.add(pos);
	}

}
