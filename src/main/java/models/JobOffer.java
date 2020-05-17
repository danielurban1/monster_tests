package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobOffer {
    private String jobName;
    private String companyName;
    private String location;
    private String saveDate;
}
