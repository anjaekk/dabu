package b172.challenging.gathering.dto.request;

import jakarta.validation.constraints.NotBlank;

public record GatheringSavingLogRequestDto(
        @NotBlank(message = "인증한 금액을 입력 해야 합니다.")
        Long amount,

        @NotBlank(message = "img Url 을 입력 해야 합니다.")
        String imgUrl
)
{ }
