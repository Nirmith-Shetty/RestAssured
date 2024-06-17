package models;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfile {
    private String name;
    private String job;
}
