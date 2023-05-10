package com.icia.unity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFileDTO {
    private Long id;
    private String originalFileName;
    private String storedFileName;
    private Long memberId;
}