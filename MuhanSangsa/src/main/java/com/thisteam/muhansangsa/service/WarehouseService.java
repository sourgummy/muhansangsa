package com.thisteam.muhansangsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thisteam.muhansangsa.mapper.WarehouseMapper;
import com.thisteam.muhansangsa.vo.WarehouseVO;

@Service
public class WarehouseService {
	
	@Autowired
	private WarehouseMapper mapper;
	
	// 창고 등록
	public int registerWarehouse(WarehouseVO warehouse) {
		return mapper.insertWarehouse(warehouse);
	}
	
	// 창고 조회
	public List<WarehouseVO> getWarehouseList() {
		return mapper.selectWarehouseList();
	}
	
	// 창고 권한
	public String getWhPrivilege(String sId) {
		return mapper.selectWhPrivilege(sId);
	}
	
	// 창고 상세정보
	public List<WarehouseVO> getWarehouseDetail(String wh_cd) {
		return mapper.selectWarehouseDetail(wh_cd);
	}
	
	// 창고 수정
	public List<WarehouseVO> modifyWarehouse(WarehouseVO warehouse) {
		return mapper.updateWarehouse(warehouse);
	}

}















