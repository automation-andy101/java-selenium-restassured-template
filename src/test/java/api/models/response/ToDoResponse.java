package api.models.response;

import lombok.Data;

@Data
public class ToDoResponse {
    private int id;
    private String name;
    private String isComplete;
    private String dateDue;
}
