package com.dungnguyen.jpaspecificationnativequerysearching.dto.request;

import lombok.Data;
import java.util.Date;

@Data
public class PeopleSearchingRequestDTO {
    private Long id;
    private String name;
    private Long age;
    private Date dateOfBirth;
}
