package com.example.keymanageapi.dao;


import com.example.keymanageapi.model.GoodsManage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsManageRepository extends JpaRepository<GoodsManage,Integer> {
      public List<GoodsManage> findByMac(String mac);
      public List<GoodsManage> findByMacOrderByCellNo(String mac);
      public List<GoodsManage> findByMacAndCellNo(String mac, Integer cellNo);


}
