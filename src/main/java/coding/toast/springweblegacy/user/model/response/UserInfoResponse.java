package coding.toast.springweblegacy.user.model.response;

import lombok.Builder;

@Builder
public record UserInfoResponse(String id, String username, Integer age) {
}
