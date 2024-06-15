package coding.toast.springweblegacy.user.model.request;

import jakarta.validation.constraints.NotEmpty;

public record UserInfoRequest(
	@NotEmpty
	String id) {
}
