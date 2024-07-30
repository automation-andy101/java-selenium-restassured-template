package api.testdata;

import api.models.requests.ToDoRequest;

public class ToDoTestData {
    public static ToDoRequest createToDo(String name, boolean isComplete, String dateDue) {
        ToDoRequest toDoRequest = new ToDoRequest();
        toDoRequest.setName(name);
        toDoRequest.setComplete(isComplete);
        toDoRequest.setDateDue(dateDue);

        return toDoRequest;
    }
}
