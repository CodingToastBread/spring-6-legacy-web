package coding.toast.springweblegacy.user.data.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record UserInfoRequest(
	@Min(1)
	Long id,
	@NotEmpty
	String phoneNumber
	) {
}
