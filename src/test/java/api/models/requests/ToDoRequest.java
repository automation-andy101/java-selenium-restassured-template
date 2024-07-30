package api.models.requests;

import lombok.Data;

@Data
public class ToDoRequest {
    private String name;
    private boolean isComplete;
    private String dateDue;
}
