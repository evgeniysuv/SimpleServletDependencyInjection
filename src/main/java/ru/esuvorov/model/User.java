package ru.esuvorov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.esuvorov.model.enums.Gender;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String name;
    private Gender gender;
    private String email;
    private String phone;
    private String city;
}
