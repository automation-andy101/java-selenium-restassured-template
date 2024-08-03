package api.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ToDoErrorResponse {
    @JsonProperty("Name")
    private List<String> Name;
}

