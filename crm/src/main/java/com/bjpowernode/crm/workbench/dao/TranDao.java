package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.Tran;

import java.util.List;
import java.util.Map;

public interface TranDao {

    int getTotal();

    int save(Tran t);

    Tran detail(String id);

    int changeStage(Tran t);

    List<Map<String, Object>> getCharts();
}
