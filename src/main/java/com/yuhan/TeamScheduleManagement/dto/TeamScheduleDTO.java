package com.yuhan.TeamScheduleManagement.dto;

import lombok.Data;

@Data
public class TeamScheduleDTO {
    private int teamNum;
    private String teamScheduleContent;
    private String teamScheduleMemberId;
    private String teamScheduleStartDate;
    private String teamScheduleEndDate;
    private Boolean allDay;
    private String scheduleState; // Enum으로 변환 필요
}
