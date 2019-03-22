package usermanagement.requests;

import javax.validation.constraints.NotNull;

public class UpdateUserRequest {

    @NotNull
    private String newEmail;

    public UpdateUserRequest() {}

    public UpdateUserRequest(String email) {
        this.newEmail = email;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
}
