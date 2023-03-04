package com.dungnguyen.quartzdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

// Lombok annotations for getters, setters and constructor
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedulerJobInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String jobId;

    private String jobName;
    private String jobGroup;
    private String jobStatus;
    private String jobClass;
    private String cronExpression;
    private String desc;
    private String interfaceName;
    private Long repeatTime;
    private Boolean cronJob;

}
