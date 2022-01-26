/**
 * Created by abuzer.alaca on 19/01/2022
 **/


package project.demo.dto;

import lombok.*;

@Data
@Getter
@Setter
public class ClientDto {
    private Long id;
    private String name;
    private String clientType;
    private String username;
}
