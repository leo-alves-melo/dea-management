package br.com.dea.study.deastudy.exeptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiCallError<T> {

    private String message;
    private List<T> details;

}
