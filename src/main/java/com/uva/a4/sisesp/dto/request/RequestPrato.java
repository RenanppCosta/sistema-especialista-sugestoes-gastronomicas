package com.uva.a4.sisesp.dto.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAutoDetect
public class RequestPrato implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private List<Long> ingredientes = new ArrayList<>();
}
