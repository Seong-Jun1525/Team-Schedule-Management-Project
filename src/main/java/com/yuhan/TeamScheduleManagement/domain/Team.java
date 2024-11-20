package com.yuhan.TeamScheduleManagement.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@Entity
public class Team {
	@Id // Primary Key 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 매핑
    private int teamNum;

    @Column(nullable = false, length = 20)
    private String teamName;
    
    @Column(nullable = false, length = 20)
    private String memberID;

    /*
     * @ManyToOne
     * @JoinColumn(name = "memberID", referencedColumnName = "userID")
     * private User user;
     * 
     * 나중에 user 테이블이 생성되면 위 코드를 활성화하여 외래 키 관계를 설정
     */

    @Enumerated(EnumType.STRING) // Enum을 문자열로 저장
    @Column(nullable = false)
    private TeamPosition teamPosition = TeamPosition.LEADER;

    @Column(nullable = false, length = 30)
    private String teamRole;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private TeamState teamState = TeamState.ACTIVE;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public enum TeamPosition {
        LEADER, MEMBER
    }
    
    public enum TeamState {
        INACTIVE(0), ACTIVE(1);

        private final int value;

        TeamState(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static TeamState fromValue(int value) {
            for (TeamState state : TeamState.values()) {
                if (state.value == value) {
                    return state;
                }
            }
            throw new IllegalArgumentException("Invalid value for TeamState: " + value);
        }
    }
}
