package com.glodon.materialdb.mybatis.service;

import com.glodon.materialdb.mybatis.models.MaterialRequirementPlan;

/**
 * Created by hui on 2019/2/11.
 */
public interface PlanService {
    /**
     * 根据城市 ID,查询计划信息
     *
     * @param id
     * @return
     */
    MaterialRequirementPlan findPlanById(Long id);

    /**
     * 新增城市信息
     *
     * @param plan
     * @return
     */
    Long savePlan(MaterialRequirementPlan plan);

    /**
     * 更新城市信息
     *
     * @param plan
     * @return
     */
    Long updatePlan(MaterialRequirementPlan plan);

    /**
     * 根据城市 ID,删除计划信息
     *
     * @param id
     * @return
     */
    Long deletePlan(Long id);

}
