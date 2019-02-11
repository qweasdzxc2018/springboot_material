package com.glodon.materialdb.mybatis.controller;

import com.glodon.materialdb.mybatis.models.MaterialRequirementPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.glodon.materialdb.mybatis.service.PlanService;

/**
 * Created by hui on 2019/2/11.
 */
@RestController
public class PlanRestController {

    @Autowired
    private PlanService planService;


    @RequestMapping(value = "/api/plan/{id}", method = RequestMethod.GET)
    public MaterialRequirementPlan findOneCity(@PathVariable("id") Long id) {
        return planService.findPlanById(id);
    }

    @RequestMapping(value = "/api/plan", method = RequestMethod.POST)
    public void createCity(@RequestBody MaterialRequirementPlan plan) {
        planService.savePlan(plan);
    }

    @RequestMapping(value = "/api/plan", method = RequestMethod.PUT)
    public void modifyCity(@RequestBody MaterialRequirementPlan plan) {
        planService.updatePlan(plan);
    }

    @RequestMapping(value = "/api/plan/{id}", method = RequestMethod.DELETE)
    public void modifyCity(@PathVariable("id") Long id) {
        planService.deletePlan(id);
    }
}
