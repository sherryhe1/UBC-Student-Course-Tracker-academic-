package persistence;

import org.json.JSONObject;

// This design is based on JsonSerializationDemo that was copied and pasted
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
