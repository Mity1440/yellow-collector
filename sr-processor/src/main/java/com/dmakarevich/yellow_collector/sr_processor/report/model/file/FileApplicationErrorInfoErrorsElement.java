package com.dmakarevich.yellow_collector.sr_processor.report.model.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileApplicationErrorInfoErrorsElement {

    private String errorDescription;

    private List<String> errorCategories;

}
