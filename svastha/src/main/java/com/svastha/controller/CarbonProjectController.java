package com.svastha.controller;

import com.svastha.entity.AwdDevice;
import com.svastha.entity.CarbonAwdMapping;
import com.svastha.entity.FarmProjects;
import com.svastha.repository.AwdDeviceRepository;
import com.svastha.repository.CarbonAwdMappingRepository;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.MasterProjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarbonProjectController {

    @Autowired
    private MasterProjectTypeRepository projectTypeDao;

    @Autowired
    private FarmProjectRepository projectDao;

    @Autowired
    private CarbonAwdMappingRepository carbonAwdMappingDao;

    @Autowired
    private AwdDeviceRepository awdDeviceDao;

    private static String PROJECT_TYPE = "CARBON";

    @GetMapping("/carbonProjects")
    public @ResponseBody Page<FarmProjects> getAllProjects(@RequestParam(required = false) Long yearId,
                                                           @RequestParam(required = false) Long seasonId, @RequestParam(required = false) Long cropId,
                                                           @RequestParam(required = false) String key, @RequestParam(required = false) Long userId,
                                                           @RequestParam(required = false) Long varietyId, @RequestParam(required = false) Long ics,
                                                           @RequestParam(required = false) Long districtId, @RequestParam(required = false) Long thalukId,
                                                           @RequestParam(required = false) Long villageId, Pageable pageable) {
        Long projectTypePk1 = projectTypeDao.findByProjectType(PROJECT_TYPE).getPk1();
        Page<FarmProjects> projects = projectDao.findWithFilters(yearId, seasonId, cropId, key, userId, projectTypePk1,
                varietyId, ics, "APPROVED", districtId, thalukId, villageId, pageable);
        return projects;
    }

    @GetMapping("/listCarbonProjects")
    public @ResponseBody Iterable<FarmProjects> getProjectsUserId(@RequestParam Long userId) {
        Long projectTypePk1 = projectTypeDao.findByProjectType(PROJECT_TYPE).getPk1();
        return projectDao.findWithFilters(null, null, null, null, userId, projectTypePk1, null, null, null, null, null);
    }

    @GetMapping("/carbonProjects")
    public @ResponseBody List<CarbonAwdMapping> getCarbonProjects(@RequestParam Long projectId) {
        FarmProjects project = projectDao.findById(projectId).get();
        return carbonAwdMappingDao.findAllByProjects(project);
    }

    @PostMapping("/saveCarbonProject")
    public @ResponseBody CarbonAwdMapping saveCarbonProject(CarbonAwdMapping carbonAwdMapping) {
        return carbonAwdMappingDao.save(carbonAwdMapping);
    }

    @GetMapping("/awdDatas")
    public @ResponseBody List<AwdDevice> getAwdData(@RequestParam Long projectId) {
        FarmProjects project = projectDao.findById(projectId).get();
        List<CarbonAwdMapping> carbonAwdMappingList = carbonAwdMappingDao.findAllByProjects(project);

        List<Long> deviceIds = carbonAwdMappingList.stream()
                .map(mapping -> mapping.getDevice().getPk1())
                .collect(Collectors.toList());

        return awdDeviceDao.findAllById(deviceIds);
    }

}
