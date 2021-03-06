package com.stamina.stamina.service.serviceImpl.pft;

import com.stamina.stamina.common.util.CommonEnum;
import com.stamina.stamina.dao.pft.ProjectFormRepository;
import com.stamina.stamina.dao.pft.ScoreConfigureRepository;
import com.stamina.stamina.entity.pft.ProjectSettingEntity;
import com.stamina.stamina.entity.pft.ScoreConfigureEntity;
import com.stamina.stamina.pojo.pft.ProjectFormPojo;
import com.stamina.stamina.pojo.pft.ScoreConfigurePojo;
import com.stamina.stamina.service.pft.ScoreConfigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模板编号：
 * 创建者: 壮壮
 * 创建时间: 2019/6/3
 * 修改编号：
 * 描述：
 */
@Service
public class ScoreConfigureServiceImpl implements ScoreConfigureService {

    @Autowired
    private ScoreConfigureRepository scoreConfigureRepository;

    @Autowired
    private ProjectFormRepository projectFormRepository;

    @Override
    public List<ProjectSettingEntity> getScoreListByProjectFormId(Long projectformId) {

        List<ProjectSettingEntity> projectSettingEntityList = new ArrayList<>();

        List<ScoreConfigurePojo> scoreConfigureList = scoreConfigureRepository.findByprojectFormId(projectformId);
        List<ScoreConfigureEntity> list1 = new ArrayList<>();
        for (ScoreConfigurePojo scoreConfigurePojo : scoreConfigureList) {
            ScoreConfigureEntity scoreConfigureEntity = new ScoreConfigureEntity();
            scoreConfigureEntity.setProjectFormId(scoreConfigurePojo.getProjectFormId());
            scoreConfigureEntity.setScoreconfigureFraction((scoreConfigurePojo.getScoreconfigureFraction()/100)+"");
            scoreConfigureEntity.setScoreconfigureHigh((scoreConfigurePojo.getScoreconfigureHigh()/100)+"");
            scoreConfigureEntity.setScoreconfigureLow((scoreConfigurePojo.getScoreconfigureLow()/100)+"");
            scoreConfigureEntity.setScoreconfigureId(scoreConfigurePojo.getScoreconfigureId());
            list1.add(scoreConfigureEntity);
        }
        ProjectFormPojo projectFormPojo = projectFormRepository.findProjectFormPojoByProjectformId(projectformId);

        ProjectSettingEntity entity = new ProjectSettingEntity();
        entity.setProjectformId(projectformId);
        entity.setProjectName(projectFormPojo.getProjectName());
        entity.setScoreConfigurePojos(list1);

        projectSettingEntityList.add(entity);

        return projectSettingEntityList;
    }

    @Override
    public List<Map> getScoreListByProjectFormId2(Long projectformId) {

        List<Map> list = new ArrayList<>();
        List<ScoreConfigurePojo> scoreConfigureList = scoreConfigureRepository.findByprojectFormId(projectformId);
        ProjectFormPojo projectFormPojo = projectFormRepository.findOne(projectformId);

        if (scoreConfigureList.size() != 0) {
            for (ScoreConfigurePojo pojo : scoreConfigureList) {
                Map map = new HashMap();
                map.put("scoreconfigureLow", pojo.getScoreconfigureLow());
                map.put("scoreconfigureHigh", pojo.getScoreconfigureHigh());
                map.put("scoreconfigureFraction", pojo.getScoreconfigureFraction());
                if (projectFormPojo.getProjectCompany() != null) {
                    map.put("projectCompany", CommonEnum.Unit.getName(projectFormPojo.getProjectCompany()));
                }
                list.add(map);
            }
        } else {
            Map map = new HashMap();
            map.put("scoreconfigureLow", null);
            map.put("scoreconfigureHigh", null);
            map.put("scoreconfigureFraction", null);
            if (projectFormPojo.getProjectCompany() != null) {
                map.put("projectCompany", CommonEnum.Unit.getName(projectFormPojo.getProjectCompany()));
            }
            list.add(map);
        }

        return list;
    }
}
