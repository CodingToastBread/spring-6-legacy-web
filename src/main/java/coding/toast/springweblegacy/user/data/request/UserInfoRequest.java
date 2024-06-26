package coding.toast.springweblegacy.user.data.request;

import jakarta.validation.constraints.Min;

public record UserInfoRequest(
	@Min(value = 1, message = "default message")
	Long id) {
}
