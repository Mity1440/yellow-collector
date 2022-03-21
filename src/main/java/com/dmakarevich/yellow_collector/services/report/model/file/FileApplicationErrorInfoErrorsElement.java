package com.dmakarevich.yellow_collector.services.report.model.file;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileApplicationErrorInfoErrorsElement {

    private String errorDescription;

    private List<String> errorCategories;

}
