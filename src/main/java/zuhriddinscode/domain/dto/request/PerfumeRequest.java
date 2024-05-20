package zuhriddinscode.domain.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import zuhriddinscode.domain.model.ErrorMessage;

@Data
@ToString
public class PerfumeRequest {

    private Long id;

    @NotBlank(message = ErrorMessage.FILL_IN_THE_INPUT_FIELD)
    @Length(max = 255)
    private String perfumeTitle;

    @NotBlank(message = ErrorMessage.FILL_IN_THE_INPUT_FIELD)
    @Length(max = 255)
    private String perfumer;

    @NotNull(message = ErrorMessage.FILL_IN_THE_INPUT_FIELD)
    @Min(value = 4, message = ErrorMessage.FILL_IN_THE_INPUT_FIELD)
    private Integer year;

    @NotBlank(message = ErrorMessage.FILL_IN_THE_INPUT_FIELD)
    @Length(max = 255)
    private String country;

    @NotBlank(message = ErrorMessage.FILL_IN_THE_INPUT_FIELD)
    @Length(max = 255)
    private String perfumeGender;

    @NotBlank(message = ErrorMessage.FILL_IN_THE_INPUT_FIELD)
    @Length(max = 255)
    private String fragranceTopNotes;

    @NotBlank(message = ErrorMessage.FILL_IN_THE_INPUT_FIELD)
    @Length(max = 255)
    private String fragranceMiddleNotes;

    @NotBlank(message = ErrorMessage.FILL_IN_THE_INPUT_FIELD)
    @Length(max = 255)
    private String fragranceBaseNotes;

    private String description;
    private String filename;

    @NotNull(message = ErrorMessage.FILL_IN_THE_INPUT_FIELD)
    @Min(value = 1, message = ErrorMessage.FILL_IN_THE_INPUT_FIELD)
    private Integer price;

    @NotBlank(message = ErrorMessage.FILL_IN_THE_INPUT_FIELD)
    @Length(max = 255)
    private String volume;

    @NotBlank(message = ErrorMessage.FILL_IN_THE_INPUT_FIELD)
    @Length(max = 255)
    private String type;
}