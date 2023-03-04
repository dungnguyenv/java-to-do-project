package com.deft.swagger;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Book")
public class Book implements Serializable {

    @NotNull(message = "ID can't be null")
    private String id;

    @NotNull(message = "Name can't be null")
    private String name;

    private String author;

    @Min(value = 0, message = "Min Price can't be positive")
    private Integer price;

}
