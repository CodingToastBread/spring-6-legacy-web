package coding.toast.springweblegacy.user.data.request;

import jakarta.validation.constraints.Min;

public record UserInfoRequest(
	@Min(value = 1, message = "{userinfo.not.bigger.then.one}") Long id) { }
