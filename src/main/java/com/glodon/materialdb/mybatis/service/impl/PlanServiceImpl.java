package com.glodon.materialdb.mybatis.service.impl;

import com.glodon.materialdb.mybatis.mappers.MaterialRequirementPlanMapper;
import com.glodon.materialdb.mybatis.models.MaterialRequirementPlan;
import com.glodon.materialdb.mybatis.service.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by hui on 2019/2/11.
 */
@Service
public class PlanServiceImpl implements PlanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanServiceImpl.class);

    @Autowired
    private MaterialRequirementPlanMapper materialRequirementPlanMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取材料需用计划逻辑：
     * 如果缓存存在，从缓存中获取材料需用计划信息
     * 如果缓存不存在，从 DB 中获取材料需用计划信息，然后插入缓存
     */
    public MaterialRequirementPlan findPlanById(Long id) {
        // 从缓存中获取材料需用计划信息
        String key = "materialRequirementPlan_" + id;
        ValueOperations<String, MaterialRequirementPlan> operations = redisTemplate.opsForValue();

        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            MaterialRequirementPlan plan = operations.get(key);

            LOGGER.info("PlanServiceImpl.findPlanById() : 从缓存中获取了材料需用计划 >> " + plan.toString());
            return plan;
        }

        // 从 DB 中获取材料需用计划信息
        MaterialRequirementPlan plan = materialRequirementPlanMapper.findById(id);

        // 插入缓存
        operations.set(key, plan, 10, TimeUnit.SECONDS);
        LOGGER.info("PlanServiceImpl.findPlanById() : 材料需用计划插入缓存 >> " + plan.toString());

        return plan;
    }

    @Override
    public Long savePlan(MaterialRequirementPlan plan) {
        return materialRequirementPlanMapper.savePlan(plan);
    }

    /**
     * 更新材料需用计划逻辑：
     * 如果缓存存在，删除
     * 如果缓存不存在，不操作
     */
    @Override
    public Long updatePlan(MaterialRequirementPlan plan) {
        Long ret = materialRequirementPlanMapper.updatePlan(plan);

        // 缓存存在，删除缓存
        String key = "materialRequirementPlan_" + plan.getId();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("PlanServiceImpl.updatePlan() : 从缓存中删除材料需用计划 >> " + plan.toString());
        }

        return ret;
    }

    @Override
    public Long deletePlan(Long id) {

        Long ret = materialRequirementPlanMapper.deletePlan(id);

        // 缓存存在，删除缓存
        String key = "materialRequirementPlan_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("PlanServiceImpl.deletePlan() : 从缓存中删除材料需用计划 ID >> " + id);
        }
        return ret;
    }

}
