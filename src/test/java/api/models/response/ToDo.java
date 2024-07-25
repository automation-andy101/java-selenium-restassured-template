package api.models.response;

import lombok.Data;

@Data
public class ToDo {
    private int id;
    private String name;
    private String isComplete;
    private String dateDue;
}
