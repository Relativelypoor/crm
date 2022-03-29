package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

public interface ClueDao {

    int save(Clue c);

    int getTotalByCondition(Map<String, Object> map);

    List<Activity> getClueListByCondition(Map<String, Object> map);

    int delete(String[] ids);

    Clue detail(String id);

    Clue getById(String clueId);

    int delete1(String clueId);
}
