package com.streams.javastreams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//import lombok.extern.slf4j.Slf4j;

//@Slf4j

@AllArgsConstructor
@ToString
@Getter
@Setter
public class Person {
    private String name;
    private Integer age;
    private Gender gender;

}
