package com.glodon.materialdb.mybatis.mappers;

import com.glodon.materialdb.mybatis.models.MaterialRequirementPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaterialRequirementPlanMapper {
    /**
     * 获取计划信息列表
     *
     * @return
     */
    List<MaterialRequirementPlan> findAllPlan();

    /**
     * 根据城市 ID，获取计划信息
     *
     * @param id
     * @return
     */
    MaterialRequirementPlan findById(@Param("id") Long id);

    Long savePlan(MaterialRequirementPlan plan);

    Long updatePlan(MaterialRequirementPlan plan);

    Long deletePlan(Long id);
}