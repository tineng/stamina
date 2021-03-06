package com.stamina.stamina.controller.pft;

import com.stamina.stamina.common.util.CommonResult;
import com.stamina.stamina.common.util.Pagination;
import com.stamina.stamina.common.util.PaginationBean;
import com.stamina.stamina.entity.pft.PhysicalBatchQueryEntity;
import com.stamina.stamina.entity.pft.ProjectRawDataEntity;
import com.stamina.stamina.service.pft.ProjectRawDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 模板编号：
 * 创建者: 壮壮
 * 创建时间: 2019/5/30
 * 修改编号：
 * 描述：
 */
@CrossOrigin
@RestController
@RequestMapping(value = "api/fpt/projectRawData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProjectRawDataController {

    @Autowired
    private ProjectRawDataService projectRawDataService;

    /***
     * 根据批次号，
     * @param rawprojectBatchcode
     * @param rawprojectProplecode
     * @return
     */
    @GetMapping("/getProjectRawData/{rawprojectBatchcode}/{rawprojectProplecode}")
    public List<ProjectRawDataEntity> getProjectRawData(@PathVariable String rawprojectBatchcode, @PathVariable String rawprojectProplecode) {

        return projectRawDataService.getProjectRawData(rawprojectBatchcode, rawprojectProplecode);
    }

    /***
     * 体能批次查询
     * @return
     */
    @GetMapping("/getPhysicalBatchQuery2")
    public List<PhysicalBatchQueryEntity> physicalBatchQuery() {

        return projectRawDataService.physicalBatchQuery();
    }

    /****
     * 体能批次查询分页加条件筛选
     */
    @GetMapping("/getPhysicalBatchQuery")
    public PaginationBean<PhysicalBatchQueryEntity> physicalBatchQuery2(String rawprojectBatchcode, String beginTime, String endTime, Pagination page) throws Exception{

        return projectRawDataService.physicalBatchQuery2(rawprojectBatchcode, beginTime, endTime, page);
    }

}
